package com.example.postprocessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * created 7/27/2021 10:22 PM
 *
 * @author bigwen <loovien@163.com>
 */
public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring-beans.xml");
    }
}
