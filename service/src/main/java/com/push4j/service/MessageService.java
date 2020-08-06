package com.push4j.service;

import cn.hutool.core.thread.ThreadUtil;
import com.push4j.dto.MessageDto;
import com.push4j.dto.ReqDataDto;
import org.fastboot.common.utils.LogUtils;
import org.fastboot.db.curd.CurdService;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.db.dto.PageDto;
import org.fastboot.db.dto.SearchDto;
import org.fastboot.db.dto.SearchListDto;
import org.fastboot.db.enums.OperatorEnum;
import org.fastboot.exception.common.ServiceException;
import org.fastboot.exception.nums.ExceptionEnum;
import org.fastboot.exception.nums.IExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.push4j.dao.MessageDao;
import com.push4j.cache.MessageCacheService;
import com.push4j.entity.MessageEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Message
*
* @author zat
* @since 1.0
*
*/
@Service
public class MessageService extends CurdService<MessageEntity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private MessageCacheService messageCacheService;
	@Autowired
	private MessageDao messageDao;


	/**
	 * 查找所有消息
	 * @param userId 用户ID
	 * @return
	 */
	public Map<String, List<MessageDto>> list(String userId, String type, Integer pageNo ,Integer pageSize) {
		if (ToolsKit.isEmpty(userId)) {
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "userId不能为空");
		}
		// 查询条件
		List<SearchDto> searchDtoList = new ArrayList<>();
		SearchDto searchDto = new SearchDto();
		searchDto.setField(MessageEntity.USERID_FIELD);
		searchDto.setOperator(OperatorEnum.EQ.getSkey());
		searchDto.setValue(userId);
		searchDtoList.add(searchDto);
		 if (ToolsKit.isNotEmpty(type)) {
			 searchDto = new SearchDto();
			 searchDto.setField(MessageEntity.TYPE_FIELD);
			 searchDto.setOperator(OperatorEnum.EQ.getSkey());
			 searchDto.setValue(type);
			 searchDtoList.add(searchDto);
		 }
		// 查询字段
		List<String> fieldList = new ArrayList<>();
		fieldList.add(MessageEntity.ID_FIELD);
		// 分页查询
		SearchListDto searchListDto = new SearchListDto();
		searchListDto.setFieldList(fieldList);
		searchListDto.setSearchDtoList(searchDtoList);
		searchListDto.setPageNo(pageNo==0 ? 1: pageNo);
		searchListDto.setPageSize(pageSize);
		PageDto<MessageEntity> pageDto = search(searchListDto);
		List<MessageEntity> messageEntityList = pageDto.getResult();
		Map<String, List<MessageDto>> messageMap = new HashMap<>();
		if (ToolsKit.isEmpty(messageEntityList)) {
			LogUtils.log(LOGGER, "根据["+userId+"]没有查到数据");
			return null;
		}
		for (MessageEntity entity : messageEntityList) {
			Integer id = entity.getId();
			entity = findById(id);
			String entityType = entity.getType();
			List<MessageDto> messageDtoList= messageMap.get(entityType);
			if (ToolsKit.isEmpty(messageDtoList)) {
				messageDtoList = new ArrayList<>();
			}
			MessageDto messageDto = new MessageDto();
			messageDto.setType(entityType);
			messageDto.setTitle(entity.getTitle());
			messageDto.setContent(entity.getContent());
			messageDto.setCreateTime(entity.getSendTime());
			messageDto.setUnRead(entity.getUnRead());
			String reqData = entity.getReqData();
			if (ToolsKit.isNotEmpty(reqData)) {
				messageDto.setReqDataDto(ToolsKit.jsonParseObject(reqData, ReqDataDto.class));
			}
			messageDtoList.add(messageDto);
			messageMap.put(entityType, messageDtoList);
		}
		return messageMap;
	}

	/**
	 * 设置为已读
	 * @param userId 用户ID
	 * @param messageId 消息ID
	 * @return
	 */
	public String read(String userId, String messageId) {
		if (ToolsKit.isEmpty(userId)) {
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "userId不能为空");
		}
		if (ToolsKit.isEmpty(messageId)) {
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "messageId不能为空");
		}
		String sql = "update `message` set `unRead`=? where `id`=?";
		try {
			//更新数据库
			messageDao.executeUpdate(sql, 1, messageId);
			// 删除缓存
			messageCacheService.deleteById(messageId);
			return "success";
		} catch (Exception e) {
			LogUtils.log(LOGGER, "更新消息["+messageId+"]为已读状态时出错: " + e.getMessage(), e);
			return "fail";
		}
	}

	/**
	 * 全部已读
	 * @param userId
	 * @return
	 */
	public String readAll(String userId) {
		if (ToolsKit.isEmpty(userId)) {
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "userId不能为空");
		}
		try {
			// 取出所有未读的消息
			String sql = "select `id` from `message` where `userId`=? and `unRead`=?";
			List<MessageEntity> entityList = messageDao.execute(sql, userId, 0);
			if (ToolsKit.isEmpty(entityList)) {
				return "success";
			}
			//更新为已读
			sql = "update `message` set `unRead`=? where `userId`=? and `unRead`=?";
			messageDao.executeUpdate(sql, 1, userId, 0);
			// 更新缓存
			if (ToolsKit.isNotEmpty(entityList)) {
				ThreadUtil.execAsync(new Runnable() {
					@Override
					public void run() {
						for (MessageEntity entity : entityList) {
							entity = findById(entity.getId());
							entity.setUnRead(1); //已读
							messageCacheService.save(entity);
						}
					}
				});
			}
			return "success";
		} catch (Exception e) {
			LogUtils.log(LOGGER, "设置用户["+userId+"]为所有已读时出错: " + e.getMessage(), e);
			return "fail";
		}
	}

	/**
	 * 取所有推送失败的消息
	 * @return
	 */
	public List<MessageEntity> getRepetPush() {
		// 取出所有推送失败的消息
		String sql = "select `id` from `message` where `sendStatus`=?";
		List<MessageEntity> messageEntityList = messageDao.execute(sql , 1);
		if (ToolsKit.isEmpty(messageEntityList)) {
			LogUtils.log(LOGGER, "没有推送失败的消息");
			return null;
		}
		for (MessageEntity entity : messageEntityList) {
			Integer id = entity.getId();
			entity = findById(id);
			messageEntityList.add(entity);
		}
		return messageEntityList;
	}


}
