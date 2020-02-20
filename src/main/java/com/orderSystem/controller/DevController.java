package com.orderSystem.controller;

import com.orderSystem.util.MConstant.MConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ahxiaoqi
 * @date 2020/1/21 11:05
 */
@Controller
@RequestMapping(MConstant.ADMIN_PATH_PREV)
public class DevController {

    private final String DEV_INDEX = "/dev/index";

    @RequestMapping(value = "dev/index", method = RequestMethod.GET)
    public String clogin(Model model, HttpServletRequest request) {
        return DEV_INDEX;
    }
}
