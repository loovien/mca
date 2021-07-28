package com.example.postprocessor;

import org.springframework.context.annotation.Configuration;

/**
 * created 7/29/2021 7:09 AM
 *
 * @author bigwen <loovien@163.com>
 */
@Configuration(proxyBeanMethods = false)
public class SshConfigure {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
