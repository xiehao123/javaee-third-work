package cn.edu.bjtu.ming;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
/**
 * 这里设置访问路径权限，相当于客户端url权限拦截，是可以单独独立出来
 */
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/user").hasRole("ADMIN")
                .antMatchers("/v1/coach").hasRole("ADMIN");
    }
}