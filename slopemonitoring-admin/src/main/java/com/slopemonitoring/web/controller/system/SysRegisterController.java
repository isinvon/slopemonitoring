package com.slopemonitoring.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.slopemonitoring.common.core.controller.BaseController;
import com.slopemonitoring.common.core.domain.AjaxResult;
import com.slopemonitoring.common.core.domain.model.RegisterBody;
import com.slopemonitoring.common.utils.StringUtils;
import com.slopemonitoring.framework.web.service.SysRegisterService;
import com.slopemonitoring.system.service.ISysConfigService;

/**
 * 注册验证
 * 
 * @author sinvon
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
