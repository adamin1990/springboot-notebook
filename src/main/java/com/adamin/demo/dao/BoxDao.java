package com.adamin.demo.dao;

import com.adamin.demo.entity.Box;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname BoxDao
 * @Description TODO
 * @Date 2022/4/10 21:23
 * @Created by Adam(https://www.lixiaopeng.top)
 */
public interface BoxDao {

    @Select("select * from hd_box where id = #{id}")
    @Results({
            @Result(property = "receiverUid",column = "receiver_uid")

    })
    Box getBox(Integer id);
    @Select("select * from hd_box")
    List<Box> getAll();
     //xml方式
    Box getBoxById(Integer id);
    @Insert("insert into hd_box(uid,content,type,receiver_uid) values(#{uid},#{content}" +
            ",#{type},#{receiverUid})")
    public void insert(Box box);
}
