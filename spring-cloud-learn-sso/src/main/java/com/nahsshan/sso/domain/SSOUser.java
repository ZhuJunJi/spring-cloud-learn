package com.nahsshan.sso.domain;

import com.nahsshan.user.common.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Administrator
 */
public class SSOUser extends User {


    public SSOUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities){
        super(sysUser.getUsername(),sysUser.getPassword(),authorities);
    }

    public SSOUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SSOUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
