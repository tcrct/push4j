package com.push4j.service;

import com.push4j.entity.TemplateEntity;
import org.fastboot.db.curd.CurdService;
import org.fastboot.common.utils.ToolsKit;
import org.fastboot.exception.common.ServiceException;
import org.fastboot.exception.nums.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.push4j.dao.TemplateDao;
import com.push4j.cache.TemplateCacheService;

import java.util.List;

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

	/**
	 * 保存模板
	 * @param entity 待持久化的对象
	 * @return 保存成功的条数
	 */
	public Integer save(TemplateEntity entity) {
		if (ToolsKit.isEmpty(entity)) {
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "模板对象不能为空");
		}
		if (ToolsKit.isNotEmpty(entity.getId()) && ToolsKit.isEmpty(entity.getCode())) {
			String crcString = entity.getType() + entity.getTitle() + entity.getName() + System.currentTimeMillis();
			entity.setCode(com.push4j.utils.ToolsKit.getCrc16(crcString));
		}
		return super.save(entity);
	}

	/**
	 * 更新模板code
	 * @param id 模板id
	 * @param code 要更新的模板code
	 * @return
	 */
	public String updateCode(String id, String code) {
		if(ToolsKit.isEmpty(id)){
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "模板id不能为空");
		}
		if ( ToolsKit.isEmpty(code)) {
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "模板code不能为空");
		}
		String sql = "update `template` set `code`=? where `id`=?";
		int count = templateDao.executeUpdate(sql, code, id);
		if (count > 0) {
			templateCacheService.deleteById(id);
		}
		return "success";
	}

	/**
	 * 根据模板code查找对象
	 * @param code 模板code
	 * @return
	 */
	public TemplateEntity findByCode(String code) {
		if(ToolsKit.isEmpty(code)){
			throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "模板code不能为空");
		}
		TemplateEntity templateEntity = templateCacheService.findByCode(code);
		if (ToolsKit.isNotEmpty(templateEntity)) {
			return (templateEntity.getEnable()==0 && templateEntity.getStatus()==0) ? templateEntity : null;
		}
		String sql = "select * from `template` where `code`=? and `enable`=? and `status`=?";
		List<TemplateEntity> templateEntityList = templateDao.execute(sql, code, 0, 0);
		return ToolsKit.isEmpty(templateEntityList) ? null : templateEntityList.get(0);
	}
}
