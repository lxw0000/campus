package com.easthome.liushao.jxc.service.impl;

import com.easthome.liushao.jxc.entity.User;
import com.easthome.liushao.jxc.repository.UserRepository;
import com.easthome.liushao.jxc.service.UserService;
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
 * @ClassName UserServiceImpl
 * @description: 业务逻辑接口的实现类
 * @datetime 2023年 05月 13日 10:58
 * @version: 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
   private  UserRepository userRepository;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User findByUserName(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public List<User> list(User user, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        //分页类对象
        Pageable pageable = new PageRequest(page-1,pageSize,direction,properties);
       Page<User> pageUser = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(user!=null){
                    if(StringUtil.isNotEmpty(user.getUserName())){
                        predicate.getExpressions().add(cb.like(root.get("userName"),"%"+user.getUserName().trim()+"%"));
                    }
                    predicate.getExpressions().add(cb.notEqual(root.get("id"),1));//管理员除外
                }
                return predicate;
            }
        },pageable);
        return pageUser.getContent();
    }

    @Override
    public Long get(User user) {
       Long count= userRepository.count(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(user!=null){
                    if(StringUtil.isNotEmpty(user.getUserName())){
                        predicate.getExpressions().add(cb.like(root.get("userName"),"%"+user.getUserName().trim()+"%"));
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
              userRepository.delete(id);
    }
}
