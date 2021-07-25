package com.example;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * created 7/25/2021 3:16 PM
 *
 * @author bigwen <loovien@163.com>
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);
        if (element.hasAttribute("id")) {
            builder.addPropertyValue("id", element.getAttribute("id"));
        }
        if (element.hasAttribute("username")) {
            builder.addPropertyValue("username", element.getAttribute("username"));
        }


        if (element.hasAttribute("age")) {
            builder.addPropertyValue("age", element.getAttribute("age"));
        }
    }
}
