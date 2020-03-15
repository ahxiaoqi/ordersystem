package com.orderSystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderSystem.data.ReturnData;
import com.orderSystem.entity.Category;
import com.orderSystem.entity.SubCategory;
import com.orderSystem.service.impl.CategoryService;
import com.orderSystem.service.impl.SubCategoryService;
import com.orderSystem.util.MConstant.MConstant;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author ahxiaoqi
 * @date 2020/1/21 11:05
 */
@Controller
@RequestMapping(MConstant.ADMIN_PATH_PREV + "/dev/")
public class DevController {

    private final Logger logger = LoggerFactory.getLogger(DevController.class);

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
    public IPage<Category> innitCategory(Page<Category> page) {
        Category category = Category.builder().build();
        return categoryService.innitCategory(category, page);
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

    // 主分类删除
    @ResponseBody
    @RequestMapping(value = "del_Category", method = RequestMethod.POST)
    public ReturnData delCategory(@RequestParam("categoryId") Integer categoryId) {
        try {
            categoryService.deleteById(categoryId);
        } catch (Exception e) {
            return ReturnData.returnError(1001, "删除分类出错");
        }
        return ReturnData.returnSuccess(null);
    }

    // 子分类删除
    @ResponseBody
    @RequestMapping(value = "del_subCategory", method = RequestMethod.POST)
    public ReturnData delSubCategory(@RequestParam("subCategoryId") Integer subCategoryId) {
        try {
            subCategoryService.deleteById(subCategoryId);
        } catch (Exception e) {
            return ReturnData.returnError(1001, "删除子分类出错");
        }
        return ReturnData.returnSuccess(null);
    }

    @PostMapping("/upload")
    @ResponseBody
    public ReturnData upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ReturnData.returnError(1002, "文件为空!");
        }
        // 随便写的
        long l = System.currentTimeMillis() >> 4 << 3;
        String fileName = l + file.getOriginalFilename();
        String filePath = "C:\\Users\\16399\\Desktop\\demo\\项目\\src\\main\\resources\\static\\res\\upload\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            JSONObject json = new JSONObject();
            json.put("path", dest.getPath());
            return ReturnData.returnSuccess(json);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("文件上传出错");
            return ReturnData.returnError(1001, "文件上传出错");
        }
    }


}
