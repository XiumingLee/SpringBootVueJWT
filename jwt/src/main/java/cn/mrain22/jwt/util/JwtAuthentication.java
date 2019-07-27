package cn.mrain22.jwt.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

/**
 * @Author: Xiuming Lee
 * @Date: 2019/7/27 10:24
 * @Version 1.0
 * @Describe: {@link org.springframework.security.core.Authentication}及其实现类的大部分属性没有提供setter方法，
 *  所以在通过json转换回Authentication时，没有setter方法的属性就赋值为空，此类是json转换回Authentication的中间类。
 */
public  class JwtAuthentication implements Authentication {

    private Collection<SimpleGrantedAuthority> authorities;
    private Object details;
    private boolean authenticated;
    private Object principal;
    private Object credentials;



    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    @Override
    public String getName() {
        return null;
    }
}
