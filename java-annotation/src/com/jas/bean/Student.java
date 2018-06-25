package com.jas.bean;

import com.jas.annotation.Column;
import com.jas.annotation.Table;

/**
 * @author Jas
 * @create 2018-06-25 20:05
 **/

/**
 * 当注解中只有一个 value 属性时，可以直接定义注解属性值
 */
@Table("student")
public class Student {
    @Column("id")
    private int id;
    @Column("user_name")
    private String username;
    @Column("age")
    private int age;
    @Column("city")
    private String city;
    @Column("phone")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
