package com.push4j.controller;

import com.push4j.entity.SignEntity;
import com.push4j.service.SignService;
import org.fastboot.common.base.BaseController;
import org.fastboot.common.dto.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 注册第三方调用APP控制层
 *
 * @author Laotang
 * @since 1.0
 *
 */
@Controller
@RequestMapping(value = "/sign" )
public class SignController extends BaseController<SignEntity> {

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/index",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public R index(@Validated @RequestBody SignEntity dto) {
         try {
             return R.success(signService.save(dto));
         } catch (Exception e) {
             return R.error(123, e);
         }
    }
}
