package com.learn.mqtx.service;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.stereotype.Service;

public class MyBatisService {

    public void mybatisDemo() {
        Environment environment = new Environment("", null, null);
        Configuration configuration = new Configuration();
        configuration.setEnvironment(environment);
        configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.WARNING);
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        SqlSession sqlSession = defaultSqlSessionFactory.openSession();


        sqlSession.close();
    }
}
