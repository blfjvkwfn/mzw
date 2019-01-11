package com.mzw.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/3 13:46
 */
@Getter
@Setter
public class UserDetail extends User {
    private String userId;

    public UserDetail(String userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }
}
