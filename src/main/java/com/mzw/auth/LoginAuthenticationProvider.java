package com.mzw.auth;

import com.mzw.auth.service.IUserDetailService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/3 12:33
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    public LoginAuthenticationProvider(IUserDetailService userDetailsService, PasswordEncoder passwordEncoder) {
        super();
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(passwordEncoder);
        this.passwordEncoder = passwordEncoder;
    }
    @SuppressWarnings("deprecation")
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        if (userDetails instanceof UserDetail) {
            presentedPassword = ((UserDetail)userDetails).getUserId()+ presentedPassword;
        }


        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }
}
