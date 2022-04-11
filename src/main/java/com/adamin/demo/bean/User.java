package com.adamin.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname User
 * @Description TODO
 * @Date 2022/4/10 10:58
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@ApiModel("用户模型")
public class User {
    @ApiModelProperty("用户名")
    private String userName;
    private int age;
    @ApiModelProperty("用户id")
    private int id;
    private Integer money;
    private Float withdraw;

    public Float getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Float withdraw) {
        this.withdraw = withdraw;
    }

    public User(String userName, int age, int id) {
        this.userName = userName;
        this.age = age;
        this.id = id;
    }

    public User(String userName, int age, int id, Integer money) {
        this.userName = userName;
        this.age = age;
        this.id = id;
        this.money = money;
    }

    public User(String userName, int age, int id, Integer money, Float withdraw) {
        this.userName = userName;
        this.age = age;
        this.id = id;
        this.money = money;
        this.withdraw = withdraw;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", money=" + money +
                ", withdraw=" + withdraw +
                '}';
    }
}
