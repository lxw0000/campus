package com.easthome.liushao.jxc.service;

import com.easthome.liushao.jxc.entity.Log;
import com.easthome.liushao.jxc.entity.Role;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author 刘庆东
 * @ClassName LogService
 * @description: 日志的业务逻辑类
 * @datetime 2023年 05月 13日 16:44
 * @version: 1.0
 */
public interface LogService {

    /**
     * 修改或者修改日志信息
     * @param log
     */
    public void save(Log log);

    /**
     * 根据条件进行分页查询日志信息
     * @param log
     * @param page
     * @param pageSize
     * @param direction
     * @param properties
     * @return
     */
    public List<Log> list(Log log, Integer page, Integer pageSize, Sort.Direction direction, String... properties);


    /**
     * 获取日志的记录数
     * @param log
     * @return
     */
    public Long getCount(Log log);
}
