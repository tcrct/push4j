package com.push4j.service;

import com.push4j.dto.PushRequestDto;
import com.push4j.entity.TemplateEntity;
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

    public void push(PushRequestDto pushRequestDto) {
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
            throw new ServiceException(ExceptionEnum.PARAM_NULL.getCode(), "根据模板code["+code+"]查找不到模板信息！");
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

        // 调用第三方推送
        //PushKit.duang().push()
    }


}
