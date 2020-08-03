package com.push4j.web;

import cn.hutool.setting.Setting;
import com.push4j.entity.LogsEntity;
import com.push4j.entity.StatusEntity;
import com.push4j.entity.TemplateEntity;
import org.fastboot.generate.GenerateKit;

public class GenerateCode {
    public static void main(String[] args) {
        // 模板CURD
//        Class<?> entityClass = TemplateEntity.class;
        // 推送状态CURD
//        Class<?> entityClass = StatusEntity.class;
        // 日志记录CURD
        Class<?> entityClass = LogsEntity.class;

        Setting setting = new Setting("application.properties");
        String basePackage = setting.get("base.package");
        String baseDir = "E:/workspace/java/push4j";
        GenerateKit.duang().baseDir(baseDir).basePackage(basePackage).clazz(entityClass).run();
    }
}
