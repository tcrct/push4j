package com.push4j.service;

import com.push4j.cache.MessageCacheService;
import com.push4j.dao.UserMessageDao;
import com.push4j.entity.MessageEntity;
import com.push4j.entity.UserGroupEntity;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
import org.fastboot.exception.nums.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* UserMessageService
*
* @author Laotang
* @since 1.0
*
*/
@Service
public class UserMessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserMessageService.class);

	@Autowired
	private MessageCacheService messageCacheService;
	@Autowired
	private UserMessageDao userMessageDao;


	/**
	 * 分页取出该用户下的所有消息
	 * @param userId 用户ID
	 * @return
	 */
	public String getMessageId(String userId, Integer pageNo, Integer pageSize) {
		if (ToolsKit.isEmpty(userId)) {
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "userId不能为空");
		}
		String sql = "select `messageId`,`read` from `userMessage` where `userId`=? limit ?,?";
		pageSize = pageNo<=0 ? pageSize : pageNo*pageSize;
		List<UserGroupEntity> entityList = userMessageDao.execute(sql,userId, pageNo, pageSize);
		if (ToolsKit.isEmpty(entityList)) {
			throw new ServiceException(123, "该用户["+userId+"]没有消息");
		}
//		Set<String> unReadMessageSet = messageCacheService.getAllUnReadMessage(userId);
		for (UserGroupEntity entity : entityList) {
			String messageId = entity.getMessageId();
			if (ToolsKit.isEmpty(messageId)) {
				continue;
			}
			MessageEntity messageEntity = messageCacheService.findById(messageId);
			// 是否未读,0是1否
			Integer unRead = entity.getUnRead();


		}

		return "";
	}

}
