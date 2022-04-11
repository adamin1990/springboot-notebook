package com.adamin.demo.service.impl;

import com.adamin.demo.dao.BoxDao;
import com.adamin.demo.entity.Box;
import com.adamin.demo.service.BoxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname BoxServiceImpl
 * @Description TODO
 * @Date 2022/4/10 21:32
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Service
public class BoxServiceImpl implements BoxService {
    @Resource
    private BoxDao boxDao;
    @Override
    public Box getBox(Integer id) {
        return  boxDao.getBox(id);
    }

    @Override
    public List<Box> getAll() {
        return boxDao.getAll();
    }

    @Override
    public Box getBoxById(Integer id) {
        return boxDao.getBoxById(id);
    }

    @Override
    @Transactional
    public void insert(Box box) {
        boxDao.insert(box);


    }
}
