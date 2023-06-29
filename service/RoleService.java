package com.easthome.liushao.jxc.service;

import com.easthome.liushao.jxc.entity.Role;
import com.easthome.liushao.jxc.entity.User;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author 刘庆东
 * @ClassName RoleService
 * @description: 角色
 * @datetime 2023年 05月 13日 15:01
 * @version: 1.0
 */
public interface RoleService {
    /**
     *  根据用户的id查询用户的角色
     * @param id
     * @return
     */
    public List<Role> findByUserId(Integer id);


    /**
     *  根据d查询实体
     * @param id
     * @return
     */
    public Role findById(Integer id);


    /**
     * 获取所有的角色信息
     *
     * @return
     */
    public List<Role> listAll();


    /**
     * 根据条件进行分页查询角色
     * @param role
     * @param page
     * @param pageSize
     * @param direction
     * @param properties
     * @return
     */
    public List<Role> list(Role role, Integer page, Integer pageSize, Sort.Direction direction, String... properties);


    /**
     *  获取总记录
     * @param role
     * @return
     */
    public Long get(Role role);

    /**
     *  根据id删除角色
     * @param id
     */
    public void delete(Integer id);

    /**
     * 添加 或者修改角色
     * @param role
     */
    public void save(Role role);



}
