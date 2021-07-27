package com.example.ctag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * created 7/25/2021 3:15 PM
 *
 * @author bigwen <loovien@163.com>
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
