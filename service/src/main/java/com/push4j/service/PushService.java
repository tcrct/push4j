package com.push4j.service;

import cn.hutool.core.thread.ThreadUtil;
import com.push4j.dto.*;
import com.push4j.entity.LogsEntity;
import com.push4j.entity.StatusEntity;
import com.push4j.entity.TemplateEntity;
import com.push4j.utils.PushKit;
import com.push4j.utils.ToolsKit;
import org.fastboot.common.dto.ValidatorErrorDto;
import org.fastboot.common.utils.LogUtils;
import org.fastboot.exception.common.ServiceException;
import org.fastboot.exception.nums.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

/**
 * Hello world!
 *
 */
@Service
public class PushService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushService.class);

    @Autowired
    private Validator validator;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private LogsService logsService;

    public PushResponseDto push(PushRequestDto pushRequestDto) {
        LogUtils.log(LOGGER, ToolsKit.toJsonString(pushRequestDto));
        Set<ConstraintViolation<PushRequestDto>> violationSet = validator.validate(pushRequestDto);
        if (org.fastboot.common.utils.ToolsKit.isNotEmpty(violationSet)) {
            List<ValidatorErrorDto> validatorErrorDtoList = new ArrayList<>();
            for (ConstraintViolation<PushRequestDto> model : violationSet) {
                validatorErrorDtoList.add(new ValidatorErrorDto(model.getPropertyPath().toString(), model.getMessage()));
            }
            throw new ServiceException(1001, ToolsKit.toJsonString(validatorErrorDtoList));
        }

        String code = pushRequestDto.getTemplateCode();
        Map<String, String> valueMap = pushRequestDto.getValueMap();
        String receiveType = pushRequestDto.getReceiveType();
        List<String> receiveList = pushRequestDto.getReceiveList();

        TemplateEntity  templateEntity = templateService.findByCode(code);
        if (ToolsKit.isEmpty(templateEntity)) {
            throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "根据模板code["+code+"]查找不到模板信息，该模板可能已经停用或删除！");
        }
        if(templateEntity.getEnable() == 1) {
            throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "该模板["+code+"]已经停用！");
        }
        String content = templateEntity.getContent();
        for(Iterator<Map.Entry<String,String>> iterator = valueMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String,String> entry = iterator.next();
            content = content.replace("${"+entry.getKey()+"}", entry.getValue());
        }
        LogUtils.log(LOGGER, "替换后的内容: " + content);

        Map<String, StatusEntity> statusDtoMap = new HashMap<>();
        List<PushDataDto> pushDataDtoList = new ArrayList<>();
        ReqDataDto reqDataDto = new ReqDataDto();
        String openType = templateEntity.getOpenType();
        String route = templateEntity.getRoute();
        if (ToolsKit.isNotEmpty(openType)) {
            reqDataDto.setType(openType);
        }
        if (ToolsKit.isNotEmpty(route)) {
            reqDataDto.setRoute(route);
        }
        Map<String,Object> extMap = pushRequestDto.getExtMap();
        if (ToolsKit.isNotEmpty(extMap)) {
            reqDataDto.setParams(extMap);
        }
        String msgType = templateEntity.getType();
        if (ToolsKit.isNotEmpty(msgType)) {
            reqDataDto.setMsgType(msgType);
        }

        if (ToolsKit.isNotEmpty(receiveList)) {
            for (String account : receiveList) {
                // 手机系统
                String phoneSystem = getPhoneSystem(pushRequestDto);
                // 调用推送
                PushDataDto dataDto =PushKit.duang()
                        .account(account)
                        .phoneSystem(phoneSystem)
                        .alertDto(new AlterDto(pushRequestDto.getTitle(), content))
                        .reqDataDto(reqDataDto)
                        .push();
                pushDataDtoList.add(dataDto);
                // 缓存推送关系，用于定时查询状态
                statusDtoMap.put(account, new StatusEntity(account, phoneSystem, pushRequestDto.getTitle(), content, "success"));
            }
        } else {
            // 全量推送
            PushDataDto dataDto = PushKit.duang()
                    .phoneSystem(getPhoneSystem(pushRequestDto))
                    .alertDto(new AlterDto(pushRequestDto.getTitle(), content))
                    .reqDataDto(reqDataDto)
                    .push();
            pushDataDtoList.add(dataDto);
        }
        // TODO 无论成功与否，都要将该推送记录保存成日志
//        savePushLogs(pushDataDtoList);
        return new PushResponseDto();
//        templateService.pushStatusMapping(statusDtoMap);
    }

    /**
     * 保存推送记录到日志表
     * @param pushDataDtoList
     */
    private void savePushLogs(final List<PushDataDto> pushDataDtoList) {
        ThreadUtil.execAsync(new Runnable() {
            @Override
            public void run() {
                for (PushDataDto pushDataDto : pushDataDtoList) {
                    LogsEntity entity = new LogsEntity();
                    entity.setTitle(pushDataDto.getTitle());
                    entity.setContent(pushDataDto.getContent());
                    entity.setPhoneSystem(pushDataDto.getPhoneSystem());
                    entity.setSendTime(new Date());
                    entity.setSendStatus("已发送");
                    logsService.save(entity);
                }
            }
        });
    }

    private String getPhoneSystem(PushRequestDto pushRequestDto) {
        return "ios";
    }

    public void status() {

    }


}
