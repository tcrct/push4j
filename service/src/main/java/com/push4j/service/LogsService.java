package com.push4j.service;

import org.fastboot.common.base.BaseController;
import org.fastboot.common.utils.LogUtils;
import org.fastboot.db.curd.CurdService;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
import org.fastboot.exception.nums.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.push4j.dao.LogsDao;
import com.push4j.cache.LogsCacheService;
import com.push4j.entity.LogsEntity;

/**
* Logs
*
* @author zat
* @since 1.0
*
*/
@Service
public class LogsService extends CurdService<LogsEntity> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogsService.class);

	@Autowired
	private LogsCacheService logsCacheService;
	@Autowired
	private LogsDao logsDao;

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
		String sql = "update `log` set `read`=? where `messageId`=?";
		try {
			//更新数据库
			logsDao.executeUpdate(sql, 0, messageId);
			// 更新缓存
			logsCacheService.reader(userId, messageId);
		} catch (Exception e) {
			LogUtils.log(LOGGER, e.getMessage(), e);
		}
		return ToolsKit.toJsonString(messageId);
	}
}
