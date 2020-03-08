package com.orderSystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderSystem.data.ReturnData;
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

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public IPage<SubCategory> innitSubCategory(@RequestParam("categoryId") Integer categoryId, Page<SubCategory> page) {
        SubCategory subCategory = SubCategory.builder().categoryId(categoryId).build();
        return subCategoryService.innitSubCategory(subCategory, page);
    }

    // 主分类信息
    @ResponseBody
    @RequestMapping(value = "get_category_detail", method = RequestMethod.POST)
    public Category getCategoryDetail(@RequestParam("categoryId") Integer categoryId) {
        Category category = Category.builder().categoryId(categoryId).build();
        return categoryService.selectOneByWrapper(category);
    }

    // 子分类信息
    @ResponseBody
    @RequestMapping(value = "get_subCategory_detail", method = RequestMethod.POST)
    public SubCategory getSubCategoryDetail(@RequestParam("subCategoryId") Integer subCategoryId) {
        SubCategory subCategory = SubCategory.builder().subCategoryId(subCategoryId).build();
        return subCategoryService.selectOneByWrapper(subCategory);
    }

    // 主分类保存
    @ResponseBody
    @RequestMapping(value = "save_category", method = RequestMethod.POST)
    public ReturnData saveCategory(Category category) {
        categoryService.save(category);
        return ReturnData.returnSuccess(null);
    }

    // 主分类保存
    @ResponseBody
    @RequestMapping(value = "save_subCategory", method = RequestMethod.POST)
    public ReturnData saveSubCategory(SubCategory subCategory) {
        subCategoryService.save(subCategory);
        return ReturnData.returnSuccess(null);
    }


}
