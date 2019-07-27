package cn.mrain22.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("userLoginService")
public class UserLoginService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return  new User("1",
                passwordEncoder.encode("1"),
                // 此处的角色需要`ROLE_`前缀。Spring Security配置中的角色不需要`ROLE_` 前缀
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }
}
