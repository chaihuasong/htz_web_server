package cn.htz.chs.shiro.config;

import cn.htz.chs.jwt.JWTFilter;
import cn.htz.chs.shiro.realm.MyShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setProxyTargetClass(true);
        creator.setUsePrefix(true);
        return creator;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 在 Shiro过滤器链上加入 JWTFilter
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filters);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不需要经过 jwt过滤器
        filterChainDefinitionMap.put("/buyao/**", "anon");
        filterChainDefinitionMap.put("/chupai/**", "anon");
        filterChainDefinitionMap.put("/get_hand_card/**", "anon");
        filterChainDefinitionMap.put("/get_current_desk_card/**", "anon");
        filterChainDefinitionMap.put("/get_chair_info/**", "anon");
        filterChainDefinitionMap.put("/request_chair/**", "anon");
        filterChainDefinitionMap.put("/media_stream/**", "anon");
        filterChainDefinitionMap.put("/get_month_gk_info/**", "anon");
        filterChainDefinitionMap.put("/get_gk_info/**", "anon");
        filterChainDefinitionMap.put("/save_gk_info/**", "anon");
        filterChainDefinitionMap.put("/get_wx_userid/**", "anon");
        filterChainDefinitionMap.put("/save_wx_userinfo/**", "anon");
        filterChainDefinitionMap.put("/get_xiaoe_goods_list/**", "anon");
        filterChainDefinitionMap.put("/get_xiaoe_goods_detail/**", "anon");
        filterChainDefinitionMap.put("/get_xiaoe_accesstoken/**", "anon");
        filterChainDefinitionMap.put("/get_wx_qyaccesstoken/**", "anon");
        filterChainDefinitionMap.put("/get_xmly_accesstoken/**", "anon");
        filterChainDefinitionMap.put("/get_zhipai_unionid/**", "anon");
        filterChainDefinitionMap.put("/getaccesstoken/**", "anon");
        filterChainDefinitionMap.put("/sendsms/**", "anon");
        filterChainDefinitionMap.put("/loginsms/**", "anon");
        filterChainDefinitionMap.put("/put/**", "anon");
        filterChainDefinitionMap.put("/get/**", "anon");
        // 其他所有请求默认都要经过 jwt过滤器
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public Realm realm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置 SecurityManager，并注入 shiroRealm
        securityManager.setRealm(realm());
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
