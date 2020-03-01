package com.orderSystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderSystem.entity.Category;
import com.orderSystem.entity.SubCategory;
import com.orderSystem.service.impl.CategoryService;
import com.orderSystem.service.impl.SubCategoryService;
import com.orderSystem.util.MConstant.MConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ahxiaoqi
 * @date 2020/1/21 11:05
 */
@Controller
@RequestMapping(MConstant.ADMIN_PATH_PREV + "/dev/")
public class DevController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    private final String DEV_INDEX = "dev/index";
    private final String DEV_CATEGORY = "dev/category";
    private final String DEV_PRODUCT = "dev/product";
    private final String DEV_ORDER = "dev/order";

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String clogin(Model model, HttpServletRequest request) {
        return DEV_INDEX;
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, HttpServletRequest request) {
        return DEV_CATEGORY;
    }

    @RequestMapping(value = "product", method = RequestMethod.GET)
    public String product(Model model, HttpServletRequest request) {
        return DEV_PRODUCT;
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String order(Model model, HttpServletRequest request) {
        return DEV_ORDER;
    }

    @ResponseBody
    @RequestMapping(value = "innit_dev_category", method = RequestMethod.POST)
    public List<Category> innitCategory() {
        Category category = Category.builder().build();
        return categoryService.selectListByWrapper(category);
    }

    // 初始化子分类
    @ResponseBody
    @RequestMapping(value = "innit_dev_subcategory", method = RequestMethod.POST)
    public IPage<SubCategory> innitSubCategory(@RequestParam("categoryId")Integer categoryId, Page<SubCategory> page) {
        SubCategory subCategory = SubCategory.builder().categoryId(categoryId).build();
        return subCategoryService.innitSubCategory(subCategory,page);
    }


}
