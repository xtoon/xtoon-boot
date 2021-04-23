package com.xtoon.boot.web.sys;

import com.xtoon.boot.common.AbstractController;
import com.xtoon.boot.common.Result;
import com.xtoon.boot.common.util.log.SysLog;
import com.xtoon.boot.sys.facade.CaptchaFacadeService;
import com.xtoon.boot.sys.facade.TenantFacadeService;
import com.xtoon.boot.util.validator.ValidatorUtils;
import com.xtoon.boot.util.validator.group.AddGroup;
import com.xtoon.boot.web.sys.command.RegisterTenantCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册Controller
 *
 * @author haoxin
 * @date 2021-02-13
 **/
@Api(tags = "注册")
@RestController
public class SysRegisterController extends AbstractController {

    @Autowired
    private TenantFacadeService tenantFacadeService;

    @Autowired
    private CaptchaFacadeService captchaFacadeService;

    /**
     * 注册租户
     */
    @ApiOperation("注册租户")
    @SysLog("注册租户")
    @PostMapping("/sys/registerTenant")
    public Result registerTenantAndUser(@RequestBody RegisterTenantCommand registerTenantCommand) {
        ValidatorUtils.validateEntity(registerTenantCommand, AddGroup.class);
        boolean captcha = captchaFacadeService.validate(registerTenantCommand.getUuid(), registerTenantCommand.getCaptcha());
        if(!captcha){
            return Result.error("验证码不正确");
        }
        tenantFacadeService.registerTenant(registerTenantCommand.getTenantName(),registerTenantCommand.getTenantCode(),registerTenantCommand.getUserName(),
                registerTenantCommand.getMobile(),registerTenantCommand.getPassword());
        return Result.ok();
    }
}
