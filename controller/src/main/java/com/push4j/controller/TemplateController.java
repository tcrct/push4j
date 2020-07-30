package com.push4j.controller;

import org.fastboot.common.base.BaseController;
import org.fastboot.common.dto.R;
import com.push4j.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@RequestMapping(value = "/demo",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public R demo(@Validated @RequestBody TemplateEntity dto) {
		try {
			return R.success(templateService.demo(dto));
		} catch (Exception e) {
			return R.error(123, e);
		}
	}
}
