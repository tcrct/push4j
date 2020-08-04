package com.push4j.controller;

import org.fastboot.common.base.BaseController;
import org.fastboot.common.dto.R;
import com.push4j.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.push4j.entity.LogsEntity;

/**
* Logs
*
* @author zat
* @since 1.0
*
*/
@Controller
@RequestMapping(value = "/logs")
public class LogsController extends BaseController<LogsEntity> {

	@Autowired
	private LogsService logsService;

	/**
	 * 将消息状态设置为已读
	 * @return
	 */
	@RequestMapping(value = "/read",  method = RequestMethod.GET)
	@ResponseBody
	public R read() {
		try {
			String userId = getValue("userId");
			String messageId= getValue("messageId");
			return R.success(logsService.read(userId, messageId));
		} catch (Exception e) {
			return R.error(123, e);
		}
	}

	/**
	 * 将消息状态设置全部已读
	 * @return
	 */
	@RequestMapping(value = "/readAll",  method = RequestMethod.GET)
	@ResponseBody
	public R readAll() {
		try {
			String userId = getValue("userId");
			String messageId= getValue("messageId");

			return R.success(logsService.read(userId, messageId));
		} catch (Exception e) {
			return R.error(123, e);
		}
	}
}
