package com.push4j.controller;

import org.fastboot.common.base.BaseController;
import org.fastboot.common.dto.R;
import com.push4j.service.TemplateService;
import org.fastboot.common.enums.ConstEnums;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
import org.fastboot.exception.nums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.push4j.entity.TemplateEntity;

/**
* 
*
* @author zat
* @since 1.0
*
*/
@Controller
@RequestMapping(value = "/template")
public class TemplateController extends BaseController<TemplateEntity> {

	@Autowired
	private TemplateService templateService;

	/**
	 * 更新模板CODE
	 * @param id 模板id
	 * @param code 更新的code
	 * @return
	 */
//	@RequestMapping(value = "/updateCode/{id}/{code}",  method = RequestMethod.GET)
//	@ResponseBody
//	public R updateCode(@PathVariable(name = "id") String id, @PathVariable(name = "code") String code) {
	@RequestMapping(value = "/updateCode",  method = RequestMethod.GET)
	@ResponseBody
	public R updateCode() {
		try {
			String id = getValue(ConstEnums.BASE_CONTROLLER_PARAM.ID.getValue());
			String code= getValue("code");
			if(ToolsKit.isEmpty(id)){
				throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "id不能为空");
			}
			if ( ToolsKit.isEmpty(code)) {
				throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "code不能为空");
			}
			return R.success(templateService.updateCode(id, code));
		} catch (Exception e) {
			return R.error(123, e);
		}
	}
}
