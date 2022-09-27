package com.ssafy.mgmgproject.config;

import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.auth.JwtAuthenticationFilter;
import com.ssafy.mgmgproject.common.auth.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PERMIT_ALL_URL_ARRAY = {
            "/v2/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",

            "/api/user/login",
            "/api/user/regist",
            "/api/user/autologin",
            "/api/user/findpw",
            "/api/user/findid",
            "/api/user/idcheck",
            "/api/user/email",
            "/api/user/emailcheck",

            "/api/diary/**",
            "/api/diary/speech"
    };

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserService userService;

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(PasswordEncoderConfig.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService))
                .authorizeRequests()
                .antMatchers(PERMIT_ALL_URL_ARRAY).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.httpFirewall(defaultHttpFirwall());
    }

    @Bean
    public HttpFirewall defaultHttpFirwall(){
        return new DefaultHttpFirewall();
    }

}
