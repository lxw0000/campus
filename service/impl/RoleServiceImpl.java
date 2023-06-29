package com.easthome.liushao.jxc.service.impl;

import com.easthome.liushao.jxc.entity.Role;
import com.easthome.liushao.jxc.entity.User;
import com.easthome.liushao.jxc.repository.RoleRepository;
import com.easthome.liushao.jxc.repository.UserRepository;
import com.easthome.liushao.jxc.service.RoleService;
import com.easthome.liushao.jxc.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 刘庆东
 * @ClassName RoleServiceImpl
 * @description: 角色的业务逻辑实现类
 * @datetime 2023年 05月 13日 15:11
 * @version: 1.0
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {


    @Resource
    private RoleRepository roleRepository;


    @Override
    public List<Role> findByUserId(Integer id) {
        return roleRepository.findByUserId(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();  //jpa特别的强大
    }

    @Override
    public List<Role> list(Role role, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        //分页类对象
        Pageable pageable = new PageRequest(page-1,pageSize,direction,properties);
        Page<Role> pageUser = roleRepository.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(role!=null){
                    if(StringUtil.isNotEmpty(role.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+role.getName().trim()+"%"));
                    }
                    predicate.getExpressions().add(cb.notEqual(root.get("id"),1));//管理员除外
                }
                return predicate;
            }
        },pageable);
        return pageUser.getContent();
    }

    @Override
    public Long get(Role role) {
        //分页类对象

       Long count = roleRepository.count(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(role!=null){
                    if(StringUtil.isNotEmpty(role.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+role.getName().trim()+"%"));
                    }
                    predicate.getExpressions().add(cb.notEqual(root.get("id"),1));//管理员除外
                }
                return predicate;
            }
        });
        return count;
    }
    @Override
    public void delete(Integer id) {
        roleRepository.delete(id);
    }

    @Override
    public void save(Role role) {
         roleRepository.save(role);
    }
}
