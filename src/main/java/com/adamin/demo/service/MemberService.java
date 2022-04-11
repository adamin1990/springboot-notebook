package com.adamin.demo.service;

import com.adamin.demo.dao.MemberDao;
import com.adamin.demo.entity.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Classname MemberService
 * @Description TODO
 * @Date 2022/4/11 16:38
 * @Created by Adam(https://www.lixiaopeng.top)
 */
@Service
public class MemberService {
    @Resource
    private MemberDao userDao;


    public Member getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    public Set<String> getRoles(String username) {
        return userDao.getRoles(username);
    }

    public Set<String> getPermissions(String username) {
        return userDao.getPermissions(username);
    }
}
