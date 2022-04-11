package com.adamin.demo.service;

import com.adamin.demo.entity.Box;

import java.util.List;

/**
 * @Classname BoxService
 * @Description TODO
 * @Date 2022/4/10 21:31
 * @Created by Adam(https://www.lixiaopeng.top)
 */
public interface BoxService {

    public Box getBox(Integer id);

    public List<Box> getAll();

    public Box getBoxById(Integer id);

    public void insert(Box box);
}
