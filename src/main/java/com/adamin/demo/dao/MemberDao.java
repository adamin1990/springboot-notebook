package com.adamin.demo.dao;

import com.adamin.demo.entity.Member;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @Classname MemberDao
 * @Description TODO
 * @Date 2022/4/11 16:43
 * @Created by Adam(https://www.lixiaopeng.top)
 */
public interface MemberDao {
    @Select("select * from hd_member where username = #{username}")
    Member getByUsername(String username);

    @Select("select r.rolename from hd_member u,hd_role r " +
            "where u.role_id = r.id and u.username = #{username}")
    Set<String> getRoles(String username);

    @Select("select p.permissionname from hd_member u,hd_role r,hd_permission p " +
            "where u.role_id = r.id and p.role_id = r.id and u.username = #{username}")
    Set<String> getPermissions(String username);
}
