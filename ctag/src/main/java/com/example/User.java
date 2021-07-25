package com.example;


/**
 * created 7/25/2021 1:26 PM
 *
 * @author bigwen <loovien@163.com>
 */
public class User {

    private String id;

    private String username;

    private Integer age;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "username: " + this.getUsername() + " age: " + this.getAge();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
