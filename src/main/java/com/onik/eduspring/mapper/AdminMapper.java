package com.onik.eduspring.mapper;

import com.onik.eduspring.entity.User;
import com.onik.eduspring.vo.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminMapper {
    /**
     * 根据id设置用户角色
     * @param id
     * @param role
     */
    @Update("update user set role = #{role} where id = #{id}")
    void setRole(Long id, Integer role);

    /**
     * 根据名字查询用户
     * @param nickname
     * @return
     */
    @Select("select u.id, u.username, u.nickname, u.email, u.phone, r.role, u.avatar, u.status, u.create_time, u.update_time " +
            "from user u " +
            "left join role r " +
            "on r.id = u.role " +
            "where nickname = #{nickname}")
    UserVo getByNickName(String nickname);

    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void delById(Long id);

    /**
     * 查询所有用户
     * @return
     */
    List<UserVo> getAllUsers();

    /**
     * 设置用户状态
     * @param id
     * @param status
     */
    @Update("update user set status = #{status} where id = #{id}")
    void setStatus(Long id, Integer status);
}
