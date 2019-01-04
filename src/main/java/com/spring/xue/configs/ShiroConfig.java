package com.spring.xue.configs;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;
@Slf4j
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        //创建一个安全工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给这个安全管理的工厂设置一个管理员
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置一个没有权限时的跳转url
        //如果不设置此处会默认的找到web下的根目录下的login.jsp或者login
        shiroFilterFactoryBean.setLoginUrl("notlog");
        //设置拦截器
        shiroFilterFactoryBean.setUnauthorizedUrl();

        //设置权限
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //开放的接口，游客可以访问
        filterChainDefinitionMap.put("/guest/**","anon");
        //需要角色权限【user】
        filterChainDefinitionMap.put("/user/**","roles[user]");
        //需要角色权限【admin】
        filterChainDefinitionMap.put("/admin/**","roles[admin]");
        //开放的权限
        filterChainDefinitionMap.put("/login/**","anon");
        //其余的接口一律拦截 此处是设置需要放在所有的url的后面，不然会导致所有的URL被拦截
        filterChainDefinitionMap.put("/**","authc");
        //把shiro拦截器注入到工厂中去
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //
//        log.i
    }
}
