package com.xtoon.boot.sys.facade.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xtoon.boot.common.util.Page;
import com.xtoon.boot.common.util.mybatis.Query;
import com.xtoon.boot.common.util.TenantContext;
import com.xtoon.boot.sys.application.TenantApplicationService;
import com.xtoon.boot.sys.domain.model.types.*;
import com.xtoon.boot.sys.facade.TenantFacadeService;
import com.xtoon.boot.sys.facade.assembler.PageAssembler;
import com.xtoon.boot.sys.infrastructure.repository.entity.SysTenantDO;
import com.xtoon.boot.sys.infrastructure.repository.mapper.SysTenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 租户FacadeImpl
 *
 * @author haoxin
 * @date 2021-02-14
 **/
@Component
public class TenantFacadeServiceImpl implements TenantFacadeService {

    @Autowired
    private TenantApplicationService tenantApplicationService;

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page queryPage(Map<String, Object> params) {
        IPage<SysTenantDO> page = sysTenantMapper.queryPage(new Query().getPage(params),params);
        return PageAssembler.toPage(page);
    }

    @Override
    public void registerTenant(String tenantName, String tenantCode, String userName,String mobile, String password) {
        tenantApplicationService.registerTenant(new TenantName(tenantName), new TenantCode(tenantCode),new Mobile(mobile), Password.create(password), new UserName(userName));
    }

    @Override
    public void disable(String id) {
        TenantContext.setTenantId(id);
        tenantApplicationService.disable(new TenantId(id));
    }
}