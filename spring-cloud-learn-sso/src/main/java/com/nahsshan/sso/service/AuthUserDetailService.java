package com.nahsshan.sso.service;

import com.alibaba.fastjson.JSON;
import com.nahsshan.sso.domain.SsoUser;
import com.nahsshan.user.common.entity.SysPermission;
import com.nahsshan.user.common.entity.SysUser;
import com.nahsshan.user.service.PermissionService;
import com.nahsshan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
@Slf4j
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Reference(version = "1.0.0")
    private UserService userService;

    @Reference(version = "1.0.0")
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.getByUsername(username);
        if (null == user) {
            log.warn("用户{}不存在", username);
            throw new UsernameNotFoundException(username);
        }
        List<SysPermission> permissionList = permissionService.findByUserId(user.getUserId());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(permissionList)) {
            for (SysPermission permission : permissionList) {
                authorityList.add(new SimpleGrantedAuthority(permission.getCode()));
            }
        }

        SsoUser ssoUser = new SsoUser(user.getUsername(), passwordEncoder.encode(user.getPassword()), authorityList);

        log.info("登录成功！用户: {}", JSON.toJSONString(ssoUser));

        return ssoUser;
    }
}
