package com.push4j.controller;

import org.fastboot.common.base.BaseController;
import org.fastboot.common.dto.R;
import com.push4j.service.MessageService;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
import org.fastboot.exception.nums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.push4j.entity.MessageEntity;

/**
* 消息中心控制层
*
* @author Laotang
* @since 1.0
*
*/
@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController<MessageEntity> {

	@Autowired
	private MessageService messageService;

	/**
	 * 根据用户取出所有消息
	 * @return
	 */
	@RequestMapping(value = "/list",  method = RequestMethod.GET)
	@ResponseBody
	public R list() {
		try {
			String userId = getValue("userId");
			if (ToolsKit.isEmpty(userId)) {
				throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "userId不能为空");
			}
			Integer pageNo = getIntValue("pageNo");
			pageNo = ToolsKit.isEmpty(pageNo) ? 0 : pageNo;

			Integer pageSize = getIntValue("pageSize");
			pageSize = ToolsKit.isEmpty(pageSize) ? 10 : pageSize;

			return R.success(messageService.list(userId, pageNo, pageSize));
		} catch (Exception e) {
			return R.error(123, e);
		}
	}

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
			if (ToolsKit.isEmpty(userId)) {
				throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "userId不能为空");
			}
			if (ToolsKit.isEmpty(messageId)) {
				throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "messageId不能为空");
			}
			return R.success(messageService.read(userId, messageId));
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
			return R.success(messageService.readAll(userId));
		} catch (Exception e) {
			return R.error(123, e);
		}
	}

}
