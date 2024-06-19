package com.orm.configuration;

import com.orm.entities.User;
import com.orm.servies.UserService;
import com.orm.servies.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.orm")
public class SpringConfig {

    @Bean
    public DriverManagerDataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/hibernate");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public Properties getProperties(){
        Properties p = new Properties();
        p.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        p.setProperty("hibernate.hbm2ddl.auto","update");
        p.setProperty("hibernate.show_sql","true");
        return p;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setHibernateProperties(getProperties());
        sessionFactoryBean.setAnnotatedClasses(User.class);
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTemplate getTemplate(){
        HibernateTemplate template = new HibernateTemplate();
        template.setSessionFactory(getSessionFactory().getObject());
        return template;
    }

    @Bean("userService")
    public UserService userService(){
        UserServiceImpl userService = new UserServiceImpl();
        userService.setHibernateTemplate(getTemplate());
        return userService;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

}
