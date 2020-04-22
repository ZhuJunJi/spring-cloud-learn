package com.nahsshan.sso.service;

import com.alibaba.fastjson.JSON;
import com.nahsshan.sso.domain.SSOUser;
import com.nahsshan.user.common.entity.SysPermission;
import com.nahsshan.user.common.entity.SysUser;
import com.nahsshan.user.service.PermissionService;
import com.nahsshan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author J.zhu
 */
@Slf4j
@Service
public class SSOUserDetailService implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UserService userService;

    @Reference(version = "1.0.0")
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.getByUsername(username);
        if (null == user) {
            log.warn("用户 {} 不存在", username);
            throw new UsernameNotFoundException(username);
        }

        List<String> permissionCodeList = permissionService
                .findByUserId(user.getUserId())
                .stream()
                .map(SysPermission::getCode)
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(permissionCodeList)){
            log.warn("用户 {} 还未配置任何权限！", username);
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(String.join(",", permissionCodeList));
        log.info("SSOUserDetailService 获取 用户 {} 权限信息成功，加载权限 {}",username, JSON.toJSONString(authorityList));
        return new SSOUser(user, authorityList);
    }

}
