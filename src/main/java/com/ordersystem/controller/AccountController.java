package com.ordersystem.controller;

import com.ordersystem.data.ReturnData;
import com.ordersystem.entity.Account;
import com.ordersystem.service.impl.AccountService;
import com.ordersystem.util.MConstant.MConstant;
import com.ordersystem.util.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2019/12/28 11:16
 */
@Controller
@RequestMapping(MConstant.LOGIN_PATH_PREV)
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    private final String USER_INDEX = "buyMore/index";

    private final String ADMIN_INDEX = "dev/index";

    private final String CLOGIN = "login/login_page";

    @RequestMapping(value = "/clogin", method = RequestMethod.GET)
    public String clogin(Model model, HttpServletRequest request) {
        return CLOGIN;
    }

    @ResponseBody
    @RequestMapping(value = "/login_check", method = RequestMethod.POST)
    public ReturnData loginCheck(Account account, Model model, HttpServletRequest request, HttpSession session) {
        Account tmp = new Account();
        tmp.setUserName(account.getUserName());
        Account res = accountService.selectOneByWrapper(tmp);
        if (Optional.ofNullable(res).isPresent()) {
            if(!PasswordUtils.saltMd5(account.getPassWord()).equals(res.getPassWord())){
                return ReturnData.returnError(1003,"密码不正确!");
            }
            switch (res.getAccountType()){
                case 1 :{
                    // 管理员
                    // 简单验证,session存用户名
                    session.setAttribute("accountId",account.getUserName());
                    return ReturnData.returnData(MConstant.DEV_INDEX);
                }
                case 2 :{
                    // 商家
                    return  ReturnData.returnData("");
                }
                case 3 :{
                    // 普通用户
                    session.setAttribute("accountId",account.getUserName());
                    return ReturnData.returnData(MConstant.BUY_INDEX);
                }
                default:{
                    return ReturnData.returnError(1002, "用户身份未知!");
                }
            }
        }
        return ReturnData.returnError(1001, "用户不存在");
    }

    @ResponseBody
    @RequestMapping(value = "/name_check", method = RequestMethod.POST)
    public ReturnData nameCheck(Account account, Model model, HttpServletRequest request) {
        if(Optional.ofNullable(accountService.selectOneByWrapper(account)).isPresent()){
            return ReturnData.returnError(1001, "用户名已存在");
        }
        return ReturnData.returnSuccess(null);
    }

    @ResponseBody
    @RequestMapping(value = "/login_register", method = RequestMethod.POST)
    public ReturnData nameRegister(Account account, Model model, HttpServletRequest request) {
        if(Optional.ofNullable(accountService.selectOneByWrapper(account)).isPresent()){
            return ReturnData.returnError(1001, "用户名已存在");
        }
        account.setPassWord(PasswordUtils.saltMd5(account.getPassWord()));
        account.setAccountType(MConstant.ACCOUNT_USER);
        accountService.save(account);
        return ReturnData.returnSuccess(null);
    }
}
