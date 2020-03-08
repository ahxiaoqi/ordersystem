package com.orderSystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orderSystem.entity.Account;
import com.orderSystem.mapper.AccountMapper;
import com.orderSystem.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2019/12/28 10:52
 */
@Service
public class AccountService implements GeneralService<Account> {

    @Autowired
    @SuppressWarnings("all")
    AccountMapper accountMapper;


    @Override
    public Account selectOneByWrapper(Account account) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(account);
        return accountMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Account> selectListByWrapper(Account account) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(account);
        return accountMapper.selectList(queryWrapper);
    }

    @Override
    public void save(Account account) {
        if (Optional.ofNullable(account.getAccountId()).isPresent()) {
            accountMapper.updateById(account);
        } else {
            accountMapper.insert(account);
        }

    }

    @Override
    public void deleteById(Integer id) {
        accountMapper.deleteById(id);
    }
}
