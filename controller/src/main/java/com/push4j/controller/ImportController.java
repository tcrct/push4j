package com.push4j.controller;

import cn.hutool.poi.excel.ExcelFileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.push4j.entity.SignEntity;
import com.push4j.entity.TemplateEntity;
import com.push4j.service.SignService;
import com.push4j.service.TemplateService;
import org.fastboot.common.base.BaseController;
import org.fastboot.common.dto.R;
import org.fastboot.common.utils.ToolsKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.*;

/**
 *导入excel模板
 * @author Laotang
 * @since 1.0
 *
 */
@Controller
@RequestMapping(value = "/excel" )
public class ImportController extends BaseController<Object> {

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/import",  method = RequestMethod.GET)
    @ResponseBody
    public R imports() {
         try {
             String excelPath = "E:\\doc\\推送消息模板.xlsx";
             ExcelReader excelReader = ExcelUtil.getReader(new File(excelPath));
             List<Map<String,Object>> excelList = excelReader.readAll();
             Iterator<Map<String,Object>> iterator = excelList.iterator();
             if (ToolsKit.isEmpty(iterator)) {
                 return R.error(1, "读取excel数据失败");
             }
             TreeMap<String,TemplateEntity> templateEntityMap = new TreeMap<>();
             while (iterator.hasNext()) {
                 Map<String,Object> map = iterator.next();
                 String type = String.valueOf(map.get("消息類型"));
                 String businessType = String.valueOf(map.get("type(类型)"));
                 String code = String.valueOf(map.get("code(模板ID)"));
                 String title = String.valueOf(map.get("title(模板名称)"));
                 String content = String.valueOf(map.get("content(模板内容)"));
                 String desc = String.valueOf(map.get("desc(模板说明)"));
                 String lang = String.valueOf(map.get("语言"));
                 // 模板内容为空，退出本次循环
                 if (ToolsKit.isEmpty(content)) {
                     continue;
                 }
                 TemplateEntity templateEntity = templateEntityMap.get(code);
                 if (ToolsKit.isEmpty(templateEntity) && !"EN".equalsIgnoreCase(lang)) {
                     templateEntity = new TemplateEntity();
                     templateEntity.setType(type);
                     templateEntity.setBusinessType(businessType);
                     templateEntity.setCode(code);
                     templateEntity.setTitle(title);
                     templateEntity.setContent(content);
                     if ( ToolsKit.isNotEmpty(desc) ) {
                         templateEntity.setDesc(desc);
                     }
                 } else if ("EN".equalsIgnoreCase(lang)){
                     templateEntity.setEnTitle(title);
                     templateEntity.setEnContent(content);
                 }
                 templateEntityMap.put(code, templateEntity);
             }

             Collection<TemplateEntity> templateEntityList = (Collection<TemplateEntity>)templateEntityMap.values();
             for (TemplateEntity entity : templateEntityList) {
                 entity.setId(null);
                 entity.setEnable(0);
                 entity.setAppKey("5f20d9cd209bce9e41176fbf");
                 entity.setCreateUserId("5f20d9cd209bce9e41176fbf");
                 entity.setUpdateUserId("5f20d9cd209bce9e41176fbf");
                 templateService.save(entity);
             }

             System.out.println(ToolsKit.toJsonString(templateEntityMap));
             return R.success("success");
         } catch (Exception e) {
             e.printStackTrace();
             return R.error(123, e);
         }
    }
}
