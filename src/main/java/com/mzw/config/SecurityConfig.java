package com.mzw.config;

import com.mzw.auth.LoginAuthenticationProvider;
import com.mzw.auth.service.IUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/11/8 8:51
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final IUserDetailService userDetailService;

    public SecurityConfig(IUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")           // 设置登录页面
                .defaultSuccessUrl("/index")  //登录成功后默认跳转到"list"
                .loginProcessingUrl("/login")  // 自定义的登录接口
                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login","/css/**", "/js/**","/images/**", "/themes/**", "/scripts/**").permitAll()     // 设置所有人都可以访问登录页面
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable()// 关闭csrf防护
                .headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        //设置静态资源不要拦截/themes/easyui/metro/easyui.css
        web.ignoring().antMatchers("/themes/**","/scripts/**","/images/**", "/css/**", "/js/**","/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
        auth.authenticationProvider(new LoginAuthenticationProvider(userDetailService,passwordEncoder()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
