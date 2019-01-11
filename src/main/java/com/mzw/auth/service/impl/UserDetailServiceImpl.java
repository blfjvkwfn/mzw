package com.mzw.auth.service.impl;

import com.mzw.auth.UserDetail;
import com.mzw.auth.service.IUserDetailService;
import com.mzw.util.EmptyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/3 10:54
 */
@Service("userDetailService")
@Slf4j
public class UserDetailServiceImpl implements IUserDetailService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库查密码
        String userId = "userId";
        String password = "111111";

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        UserDetail user = new UserDetail(userId, username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
