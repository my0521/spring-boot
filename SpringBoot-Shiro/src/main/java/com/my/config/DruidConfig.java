package com.my.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author my
 * @description 配置Druid
 */
@Configuration
public class DruidConfig {

    @Bean(destroyMethod="close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    /**
     * @author: my
     * @Description: 配置Druid的监控
     **/
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        // druid后台管理员用户
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        // 是否能够重置数据
        initParams.put("resetEnable", "false");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * @author: my
     * @Description: 配置web监控的过滤器
     **/
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
        // 添加过滤规则
        bean.addUrlPatterns("/*");
        Map<String,String> initParams = new HashMap<>();
        // 忽略过滤格式
        initParams.put("exclusions","*.js, *.css, *.icon, *.png, *.jpg, /druid/*");
        bean.setInitParameters(initParams);
        return  bean;
    }
}
