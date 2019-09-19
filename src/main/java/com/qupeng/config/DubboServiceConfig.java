/**
 * @项目名：zjsProject
 * @创建人： qupeng
 * @创建时间： 2019-09-19
 * @公司： www.bjpowernode.com
 * @描述：TODO
 */

package com.qupeng.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <p>NAME: DubboServiceConfig</p>
 * @author qupeng
 * @date 2019-09-19 09:54:08
 * @version 1.0
 */
@Configuration // == xml
@PropertySource({"classpath:jdbc.properties", "xxx-service.properties"}) //读取一下jdbc.properties配置文件
@EnableDubbo(scanBasePackages = {"com.qupeng.service"}) //扫描dubbo的service注解
@ComponentScan(basePackages = {"com.qupeng.service"}) //扫描spring的service注解
@MapperScan(basePackages = {"com.qupeng.mybatis.mapper"}) //扫描Mapper接口-->MapperFacrotyBean
@EnableTransactionManagement //相当于原来spring.xml中的 <tx:annotation-driven transaction-manager="transactionManager"/>
public class DubboServiceConfig {
    @Value("${jdbc.username}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClassName}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
