package com.easthome.liushao.jxc.service;

import com.easthome.liushao.jxc.entity.User;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author 刘庆东
 * @ClassName UserService
 * @description: 用户的业务逻辑类
 * @datetime 2023年 05月 13日 10:42
 * @version: 1.0
 */
public interface UserService {
    /**
     *  根据用户名查找用户实体
     * @param username
     * @return
     */
    public User findByUserName(String username);

    /**
     *  根据id查找用户实体
     * @param id
     * @return
     */
    public User findByUserName(Integer id);

    /**
     *  修改用户信息
     * @param user
     */
    public void save(User user);


    /**
     * 根据条件进行分页查询用户
     * @param user
     * @param page
     * @param pageSize
     * @param direction
     * @param properties
     * @return
     */
    public List<User> list(User user, Integer page, Integer pageSize, Sort.Direction direction,String... properties);

    /**
     *  获取总记录
     * @param user
     * @return
     */
    public Long get(User user);

    /**
     *  根据id删除用户
     * @param id
     */
    public void delete(Integer id);


}
