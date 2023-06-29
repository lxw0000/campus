package com.easthome.liushao.jxc.service.impl;

import com.easthome.liushao.jxc.entity.Log;
import com.easthome.liushao.jxc.repository.LogRepository;
import com.easthome.liushao.jxc.repository.UserRepository;
import com.easthome.liushao.jxc.service.LogService;
import com.easthome.liushao.jxc.util.StringUtil;
import org.apache.shiro.SecurityUtils;
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
import java.util.Date;
import java.util.List;

/**
 * @author 刘庆东
 * @ClassName LogServiceImpl
 * @description: 日志的业务逻辑实现类
 * @datetime 2023年 05月 13日 16:48
 * @version: 1.0
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Resource
    private LogRepository logRepository;

    @Resource
    private UserRepository userRepository;


    @Override
    public void save(Log log) {
        log.setTime(new Date());//设置操作日期为系统时间
        //涉及到另外一个安全框架    shiro
       log.setUser(userRepository.findByUserName(((String) SecurityUtils.getSubject().getPrincipal())));//谁操作的
        logRepository.save(log);

    }

    @Override
    public List<Log> list(Log log, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {

        Pageable pageable= new PageRequest(page-1,pageSize,direction,properties);
        Page<Log> pageLog = logRepository.findAll(new Specification<Log>() {
            @Override
            public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(log!=null){
                    if(log.getUser()!=null && StringUtil.isNotEmpty(log.getUser().getTrueName())){
                        predicate.getExpressions().add(cb.like(root.get("user").get("trueName"),"%"+log.getUser().getTrueName()+"%"));

                    }
                    if(StringUtil.isNotEmpty(log.getType())){
                        predicate.getExpressions().add(cb.like(root.get("type"),log.getType()));
                    }
                    if(log.getBtime()!=null){
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("time"),log.getBtime()));
                    }
                    if(log.getEtime()!=null){
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("time"),log.getEtime()));
                    }
                }
                return predicate;
            }
        },pageable);
        return pageLog.getContent();
    }

    @Override
    public Long getCount(Log log) {
       Long count = logRepository.count(new Specification<Log>() {
            @Override
            public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(log!=null){
                    if(log.getUser()!=null && StringUtil.isNotEmpty(log.getUser().getTrueName())){
                        predicate.getExpressions().add(cb.like(root.get("user").get("trueName"),"%"+log.getUser().getTrueName()+"%"));

                    }
                    if(StringUtil.isNotEmpty(log.getType())){
                        predicate.getExpressions().add(cb.like(root.get("type"),log.getType()));
                    }
                    if(log.getBtime()!=null){
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("time"),log.getBtime()));
                    }
                    if(log.getEtime()!=null){
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("time"),log.getEtime()));
                    }
                }
                return predicate;
            }
        });
        return count;
    }
}
