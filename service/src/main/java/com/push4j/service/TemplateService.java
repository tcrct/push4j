package com.push4j.service;

import com.push4j.entity.SignEntity;
import org.fastboot.common.base.BaseController;
import org.fastboot.db.curd.CurdService;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.push4j.dao.TemplateDao;
import com.push4j.cache.TemplateCacheService;
import com.push4j.entity.TemplateEntity;

/**
* 
*
* @author zat
* @since 1.0
*
*/
@Service
public class TemplateService extends CurdService<TemplateEntity> {

	@Autowired
	private TemplateCacheService templateCacheService;
	@Autowired
	private TemplateDao templateDao;

	public String demo(TemplateEntity dto) {
		return ToolsKit.toJsonString(dto);
	}
}
