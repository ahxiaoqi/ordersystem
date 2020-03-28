package com.ordersystem.service;


import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2019/12/28 10:47
 */
public interface GeneralService<T> {

    public T selectOneByWrapper(T t);

    public List<T> selectListByWrapper(T t);

    public void save(T t);

    public void deleteById(Integer id);

}
