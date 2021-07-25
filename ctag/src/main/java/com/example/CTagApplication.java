package com.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author luowen <loovien@163.com>
 */
public class CTagApplication {

    public static void main(String[] args) throws IOException {
//        ClassLoader classLoader = CTagApplication.class.getClassLoader();
//        System.out.println(classLoader.getResource("."));
//        System.out.println(classLoader.getParent().getResource("."));
//        System.out.println(classLoader.getParent().getParent());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-beans.xml");

        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        System.out.println(defaultClassLoader.getResource("."));
        Properties properties = PropertiesLoaderUtils.loadAllProperties("META-INF/spring-handlers", defaultClassLoader);
        System.out.println(properties);
        User user = (User) context.getBean("user");
        System.out.println(user);
        User luowen = (User) context.getBean("luowen");
        System.out.println(luowen);
    }
}
