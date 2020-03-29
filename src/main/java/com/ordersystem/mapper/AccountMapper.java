package com.ordersystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ordersystem.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ahxiaoqi
 * @date 2019/12/28 10:43
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
