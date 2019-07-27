package cn.mrain22.jwt.config;

import cn.mrain22.jwt.authentication.JwtAuthenticationTokenFilter;
import cn.mrain22.jwt.authentication.MyLoginFailureHandler;
import cn.mrain22.jwt.authentication.MyLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Xiuming Lee
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyLoginSuccessHandler myLoginSuccessHandler;
    @Autowired
    private MyLoginFailureHandler myLoginFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /** JWT拦截器*/
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        /** 将JWT拦截器添加到UsernamePasswordAuthenticationFilter之前*/
        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginPage("/loginInfo")
                .loginProcessingUrl("/login")
                .successHandler(myLoginSuccessHandler)
                .failureHandler(myLoginFailureHandler);
        http.authorizeRequests()
                // 此处的角色不需要`ROLE_` 前缀,实现UserDetailsService设置角色时需要`ROLE_` 前缀
                .antMatchers("/needAdminRole").hasRole("ADMIN")
                .antMatchers("/hello","/login","/loginInfo","/logoutSuccess")
                .permitAll()
                .anyRequest()
                .authenticated();
        //访问 /logout 表示用户注销，并清空session
        http.logout().logoutSuccessUrl("/logoutSuccess");
        // 关闭csrf
        http.csrf().disable();
        http.cors();
    }

    /**
     * 密码加盐加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Spring自带的每次会随机生成盐值，即使密码相同，加密后也不同
        return new BCryptPasswordEncoder();
    }
}
