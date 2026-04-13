package com.onik.eduspring.mapper;

import com.onik.eduspring.dto.UserPasswordDto;
import com.onik.eduspring.entity.User;
import com.onik.eduspring.vo.UserLoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select u.id, u.username,u.password, u.nickname ,u.email,u.phone,r.role_code,u.avatar from user u " +
            "left join role r on u.role = r.id " +
            "where u.username = #{username} and u.status != 0")
    UserLoginVo getByUserName(String username);

    /**
     * 新增用户
     * @param user
     */
    void save(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    void update(User user);

    /**
     *根据账号查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username} and status != 0")
    User getUserByUserName(String username);

    /**
     * 获取所有教师
     * @return
     */
    @Select("select * from user where role = 2")
    List<User> getAllTeacher();

    /**
     * 根据id修改密码
     * @param userPasswordDto
     */
    void updateById(UserPasswordDto userPasswordDto);

    /**
     * 根据id查询密码
     * @param id
     * @return
     */
    @Select("select password from user where id = #{id}")
    String getUserById(Long id);
}
