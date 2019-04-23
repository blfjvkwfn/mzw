package com.mzw.auth2.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan Meng
 * @date 22/04/2019
 */
@Service
public class UserDetailsService extends InMemoryUserDetailsManager {
    public UserDetailsService() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        password 方案一：明文存储，用于测试，不能用于生产
//        String finalPassword = "123456";
//        password 方案二：用 BCrypt 对密码编码
//        String finalPassword = bCryptPasswordEncoder.encode("123456");
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        String finalPassword = "{bcrypt}"+bCryptPasswordEncoder.encode("123456");
        createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
        createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
    }
}
