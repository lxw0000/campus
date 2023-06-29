package com.easthome.liushao.jxc.service;

import com.easthome.liushao.jxc.entity.Menu;

import java.util.List;

/**
 * @author 刘庆东
 * @ClassName MenuService
 * @description: 权限菜单的service
 * @datetime 2023年 05月 13日 15:47
 * @version: 1.0
 */
public interface MenuService {

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    public Menu findById(Integer id);


    /**
     *  根据id获取权限菜单集合
     * @param roleId
     * @return
     */
    public List<Menu> findByRoleId(int roleId);

    /**
     *  根据父节点以及角色的id 查询子节点
     * @param parentId
     * @param roleId
     * @return
     */
    public List<Menu> findByParentIdAndRoleId(int parentId,int roleId);

    /**
     *  根据父节点查找所有的子节点
     * @param parentId
     * @return
     */
    public List<Menu> findByParentId(int parentId);



}
