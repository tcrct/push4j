package com.push4j.service;

import org.fastboot.common.base.BaseController;
import org.fastboot.db.curd.CurdService;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
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
		return ToolsKit.toJsonString(messageId);
	}
}
