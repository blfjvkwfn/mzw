package com.mzw.security.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @author Jonathan Meng
 * @date 02/04/2019
 */
class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("user","$2a$10$UlHMsewlokdVvNJPbQLh0etaBnjYWuASxViaTrXO/mv9AnT1I7YS.",new ArrayList<>());
    }
}