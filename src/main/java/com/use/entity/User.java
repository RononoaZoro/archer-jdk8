package com.use.entity;

/**
 * @Description: <br>
 * @Date: 2019/10/31 21:52 <br>
 * @Author: luoxz <br>
 * @Version: 1.0 <br>
 */
public class User {
    private Integer id;
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
