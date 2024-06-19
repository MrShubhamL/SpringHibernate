package com.orm;

import com.orm.configuration.SpringConfig;
import com.orm.entities.User;
import com.orm.servies.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        User user = new User();
        user.setUsername("Shubham");
        user.setContact("776898767");
        user.setPassword("123");
        userService.createUser(user);
    }
}
