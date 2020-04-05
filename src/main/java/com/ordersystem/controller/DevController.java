package com.ordersystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordersystem.data.ReturnData;
import com.ordersystem.entity.*;
import com.ordersystem.entity.dto.ActivityDto;
import com.ordersystem.entity.dto.OrderDto;
import com.ordersystem.entity.dto.ProductDto;
import com.ordersystem.service.impl.*;
import com.ordersystem.util.MConstant.MConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author ahxiaoqi
 * @date 2020/1/21 11:05
 */
@Controller
@RequestMapping(MConstant.ADMIN_PATH_PREV + "/dev/")
public class DevController {

    private final Logger logger = LoggerFactory.getLogger(DevController.class);

    @Value("${uploadPath}")
    private String uploadPath;


    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductTagService productTagService;

    @Autowired
    ActivityService activityService;

    @Autowired
    SlideService slideService;

    @Autowired
    DoubleSlideService doubleSlideService;

    @Autowired
    OrderService orderService;

    @Autowired
    SpecService specService;


    private final String DEV_INDEX = "dev/index";
    private final String DEV_CATEGORY = "dev/category";
    private final String DEV_PRODUCT = "dev/product";
    private final String DEV_ORDER = "dev/order";
    private final String DEV_ACTIVITY = "dev/activity";
    private final String DEV_SLIDE = "dev/slide";
    private final String DEV_SLIDE_DOUBLE = "dev/slideDouble";

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

    @RequestMapping(value = "activity", method = RequestMethod.GET)
    public String activity(Model model, HttpServletRequest request) {
        return DEV_ACTIVITY;
    }

    @RequestMapping(value = "slide", method = RequestMethod.GET)
    public String slide(Model model, HttpServletRequest request) {
        return DEV_SLIDE;
    }

    @RequestMapping(value = "slideDouble", method = RequestMethod.GET)
    public String slideDouble(Model model, HttpServletRequest request) {
        return DEV_SLIDE_DOUBLE;
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

    // 产品保存
    @ResponseBody
    @RequestMapping(value = "save_product", method = RequestMethod.POST)
    public ReturnData saveProduct(Product product) {
        productService.save(product);
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

    // 初始化商品列表
    @ResponseBody
    @RequestMapping(value = "innit_dev_product_list", method = RequestMethod.POST)
    public IPage<ProductDto> innitDevProductList(Page<Product> page, Product product) {
        return productService.innitProductListBox(product, page);
    }

    //  全部分类名称
    @ResponseBody
    @RequestMapping(value = "innitCategoryName", method = RequestMethod.POST)
    public List<Category> innitCategoryName() {
        return categoryService.selectListByWrapper(Category.builder().build());
    }

    @ResponseBody
    @RequestMapping(value = "innitSubCategoryName", method = RequestMethod.POST)
    public List<SubCategory> innitSubCategoryName(@RequestParam("categoryId") Integer categoryId) {
        return subCategoryService.selectListByWrapper(SubCategory.builder().categoryId(categoryId).build());
    }

    // 产品删除
    @ResponseBody
    @RequestMapping(value = "del_product", method = RequestMethod.POST)
    public ReturnData delProduct(@RequestParam("productId") Integer productId) {
        try {
            productService.deleteById(productId);
            return ReturnData.returnSuccess(null);
        } catch (Exception e) {
            return ReturnData.returnError(1001, "删除商品异常");
        }
    }

    // 商品详情
    @ResponseBody
    @RequestMapping(value = "get_product_detail", method = RequestMethod.POST)
    public Product getProductDetail(@RequestParam("productId") Integer productId) {
        return productService.selectOneByWrapper(Product.builder().productId(productId).build());
    }

    // 规格列表
    @ResponseBody
    @RequestMapping(value = "get_product_spec", method = RequestMethod.POST)
    public List<Spec> getProductSpecList(@RequestParam("productId") Integer productId) {
        return specService.selectListByWrapper(Spec.builder().productId(productId).build());
    }

    // 规格详情
    @ResponseBody
    @RequestMapping(value = "get_spec_detail", method = RequestMethod.POST)
    public Spec getProductSpecDetail(@RequestParam("specId") Integer specId) {
        return specService.selectOneByWrapper(Spec.builder().specId(specId).build());
    }

    // 规格列表
    @ResponseBody
    @RequestMapping(value = "del_spec", method = RequestMethod.POST)
    public ReturnData delSpec(@RequestParam("specId") Integer specId) {
        try {
            specService.deleteById(specId);
            return ReturnData.returnData(null);
        } catch (Exception e) {
            logger.info("删除规格出错{}", e.getMessage());
            return ReturnData.returnError(1001, "保存出错");
        }
    }

    // 保存规格
    @ResponseBody
    @RequestMapping(value = "save_spec", method = RequestMethod.POST)
    public ReturnData getProductSpec(Spec spec) {
        try {
            specService.save(spec);
            return ReturnData.returnData(null);
        } catch (Exception e) {
            logger.info("保存规格出错{}", e.getMessage());
            return ReturnData.returnError(1001, "保存出错");
        }
    }

    // 获取标签列表
    @ResponseBody
    @RequestMapping(value = "innitTagList", method = RequestMethod.POST)
    public List<ProductTag> innitTagList() {
        return productTagService.selectListByWrapper(ProductTag.builder().build());
    }

    // 活动列表
    @ResponseBody
    @RequestMapping(value = "innitActivity", method = RequestMethod.POST)
    public IPage<ActivityDto> innitActivity(Page<Activity> page, Activity activity) {
        return activityService.innitActivity(page, activity);
    }

    // 轮播图列表
    @ResponseBody
    @RequestMapping(value = "innitSlide", method = RequestMethod.POST)
    public IPage<Slide> innitSlide(Page<Slide> page, Slide slide) {
        return slideService.innitSlide(page, slide);
    }

    // 订单列表
    @ResponseBody
    @RequestMapping(value = "innitOrder", method = RequestMethod.POST)
    public IPage<Order> innitOrder(Page<Order> page, Order order) {
        return orderService.innitOrder(page, order);
    }

    // 轮播图列表
    @ResponseBody
    @RequestMapping(value = "innitDoubleSlide", method = RequestMethod.POST)
    public IPage<DoubleSlide> innitDoubleSlide(Page<DoubleSlide> page, DoubleSlide doubleSlide) {
        return doubleSlideService.innitDoubleSlide(page, doubleSlide);
    }

    // 活动详情
    @ResponseBody
    @RequestMapping(value = "get_activity_detail", method = RequestMethod.POST)
    public Activity getActivityDetail(@RequestParam("activityId") Integer activityId) {
        return activityService.selectOneByWrapper(Activity.builder().activityId(activityId).build());
    }

    // 轮播图详情
    @ResponseBody
    @RequestMapping(value = "get_slide_detail", method = RequestMethod.POST)
    public Slide getSlideDetail(@RequestParam("slideId") Integer slideId) {
        return slideService.selectOneByWrapper(Slide.builder().slideId(slideId).build());
    }

    // 轮播副图详情
    @ResponseBody
    @RequestMapping(value = "get_double_slide_detail", method = RequestMethod.POST)
    public DoubleSlide getDoubleSlideDetail(@RequestParam("doubleSlideId") Integer doubleSlideId) {
        return doubleSlideService.selectOneByWrapper(DoubleSlide.builder().doubleSlideId(doubleSlideId).build());
    }

    // 订单详情
    @ResponseBody
    @RequestMapping(value = "get_order_detail", method = RequestMethod.POST)
    public OrderDto getOrderDetail(@RequestParam("orderId") Integer orderId) {
        return orderService.getOrderDetail(orderId);
    }

    // 活动详情
    @ResponseBody
    @RequestMapping(value = "save_activity", method = RequestMethod.POST)
    public ReturnData saveActivity(Activity activity) {
        try {
            activity.setType(2);
            return activityService.saveAndCheckCount(activity);
        } catch (Exception e) {
            logger.info("活动保存错误{}", e.getMessage());
            return ReturnData.returnError(1001, "活动保存错误");
        }
    }

    // 活动详情
    @ResponseBody
    @RequestMapping(value = "save_slide", method = RequestMethod.POST)
    public ReturnData saveSlide(Slide slide) {
        try {
            slideService.save(slide);
        } catch (Exception e) {
            logger.info("轮播图保存错误{}", e.getMessage());
            return ReturnData.returnError(1001, "活动保存错误");
        }
        return ReturnData.returnData(null);
    }

    // 活动详情
    @ResponseBody
    @RequestMapping(value = "save_double_slide", method = RequestMethod.POST)
    public ReturnData saveDoubleSlide(DoubleSlide doubleSlide) {
        try {
            return doubleSlideService.saveAndCheckCount(doubleSlide);
        } catch (Exception e) {
            logger.info("轮播副图保存错误{}", e.getMessage());
            return ReturnData.returnError(1001, "活动保存错误");
        }
    }

    // 活动详情
    @ResponseBody
    @RequestMapping(value = "del_activity", method = RequestMethod.POST)
    public ReturnData delActivity(@RequestParam("activityId") Integer activityId) {
        try {
            activityService.deleteById(activityId);
        } catch (Exception e) {
            logger.info("删除活动错误{}", e.getMessage());
            return ReturnData.returnError(1001, "删除活动错误");
        }
        return ReturnData.returnData(null);
    }

    // 活动详情
    @ResponseBody
    @RequestMapping(value = "del_slide", method = RequestMethod.POST)
    public ReturnData delSlide(@RequestParam("slideId") Integer slideId) {
        try {
            slideService.deleteById(slideId);
        } catch (Exception e) {
            logger.info("删除轮播图错误{}", e.getMessage());
            return ReturnData.returnError(1001, "删除活动错误");
        }
        return ReturnData.returnData(null);
    }

    // 活动详情
    @ResponseBody
    @RequestMapping(value = "del_double_slide", method = RequestMethod.POST)
    public ReturnData delDoubleSlide(@RequestParam("doubleSlideId") Integer doubleSlideId) {
        try {
            doubleSlideService.deleteById(doubleSlideId);
        } catch (Exception e) {
            logger.info("删除轮播副图错误{}", e.getMessage());
            return ReturnData.returnError(1001, "删除活动错误");
        }
        return ReturnData.returnData(null);
    }



    @PostMapping("/upload")
    @ResponseBody
    public ReturnData upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ReturnData.returnError(1002, "文件为空!");
        }
        // 随便写的
        long tmp = System.currentTimeMillis() >> 4 << 3 * 4;
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        String fileName = tmp + "." + suffix;
        String filePath = uploadPath;
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            JSONObject json = new JSONObject();
            json.put("path", "uploadPath/" + fileName);
            return ReturnData.returnSuccess(json);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("文件上传出错");
            return ReturnData.returnError(1001, "文件上传出错");
        }
    }


}
