package com.easthome.liushao.jxc.service.impl;

import com.easthome.liushao.jxc.entity.Menu;
import com.easthome.liushao.jxc.repository.MenuRepository;
import com.easthome.liushao.jxc.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刘庆东
 * @ClassName MenuServiceImpl
 * @description: TODO
 * @datetime 2023年 05月 13日 15:54
 * @version: 1.0
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public Menu findById(Integer id) {
        return menuRepository.findOne(id);
    }

    @Override
    public List<Menu> findByRoleId(int roleId) {
        return menuRepository.findByRoleId(roleId);
    }

    @Override
    public List<Menu> findByParentIdAndRoleId(int parentId, int roleId) {
        return menuRepository.findByParentIdAndRoleId(parentId, roleId);
    }

    @Override
    public List<Menu> findByParentId(int parentId) {
        return menuRepository.findByParentId(parentId);
    }
}
