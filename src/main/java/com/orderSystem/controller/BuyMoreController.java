package com.orderSystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderSystem.data.ReturnData;
import com.orderSystem.entity.*;
import com.orderSystem.entity.dto.ActivityDto;
import com.orderSystem.entity.dto.CommentDto;
import com.orderSystem.entity.dto.ProductDto;
import com.orderSystem.entity.dto.productBuyDto;
import com.orderSystem.service.impl.*;
import com.orderSystem.util.MConstant.MConstant;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * @author ahxiaoqi
 * @date 2020/1/4 13:55
 */
@Controller
@RequestMapping(MConstant.USER_PATH_PREV + "/buyMore")
public class BuyMoreController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final String USER_INDEX = "buyMore/index";

    private final String PRODUCT_GRID = "buyMore/product-grid";

    private final String PRODUCT_LIST = "buyMore/product-list";

    private final String SINGLE_PRODUCT = "buyMore/single-product";

    private final String ABOUT = "buyMore/about";

    private final String FAQ = "buyMore/faq.html";

    private final String BLOG_LEFT_SIDEBAR = "buyMore/blog-left-sidebar";

    private final String CONTACT = "buyMore/contact";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    SlideService slideService;

    @Autowired
    DoubleSlideService doubleSlideService;

    @Autowired
    ActivityService activityService;

    @Autowired
    CommentService commentService;

    @Autowired
    SubCategoryService subCategoryService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String clogin(Model model, HttpServletRequest request) {
        return USER_INDEX;
    }

    @RequestMapping(value = "product-grid", method = RequestMethod.GET)
    public String product_grid(Model model, HttpServletRequest request) {
        return PRODUCT_GRID;
    }

    @RequestMapping(value = "product-list", method = RequestMethod.GET)
    public String product_list(Model model, HttpServletRequest request) {
        return PRODUCT_LIST;
    }

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about(Model model, HttpServletRequest request) {
        return ABOUT;
    }

    @RequestMapping(value = "faq", method = RequestMethod.GET)
    public String faq(Model model, HttpServletRequest request) {
        return FAQ;
    }

    @RequestMapping(value = "blog-left-sidebar", method = RequestMethod.GET)
    public String blob(Model model, HttpServletRequest request) {
        return BLOG_LEFT_SIDEBAR;
    }

    @RequestMapping(value = "contact", method = RequestMethod.GET)
    public String contact(Model model, HttpServletRequest request) {
        return CONTACT;
    }

    @RequestMapping(value = "single-product", method = RequestMethod.GET)
    public String single_product(@RequestParam("productId") Integer productId, Model model, HttpServletRequest request) {
        model.addAttribute("productId", productId);
        return SINGLE_PRODUCT;
    }

    @ResponseBody
    @RequestMapping(value = "accountIndo_innit", method = RequestMethod.POST)
    public ReturnData accountIndo_innit(String accountId, Model model, HttpServletRequest request, HttpSession session) {
        if (Optional.ofNullable(session.getAttribute("accountId")).isPresent()) {
            if (accountId.equals(session.getAttribute("accountId"))) {
                return ReturnData.returnData(accountId);
            }
            return ReturnData.returnError(1001, "未登录");
        }
        return ReturnData.returnError(1001, "未登录");
    }

    @ResponseBody
    @RequestMapping(value = "innit_buyMore_category", method = RequestMethod.POST)
    public List<Category> innitCategory() {
        Category category = Category.builder()
                .status(1).build();
        return categoryService.selectListByWrapper(category);
    }

    // 初始化热销产品列表
    // 随机查询四条产品信息
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_hot_product", method = RequestMethod.POST)
    public List<ProductDto> innitHotProduct() {
        return productService.selectHotProduct(4);
    }

    // 初始化新品上市列表
    // 按时间查询四条
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_new_product", method = RequestMethod.POST)
    public IPage<ProductDto> innitNewProduct(Page<Product> page) {
        return productService.initNewProduct(page);
    }

    // 初始化轮播图列表
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_slidebox", method = RequestMethod.POST)
    public List<Slide> innitSlideBox() {
        return slideService.selectListByWrapper(Slide.builder().status(1).build());
    }

    // 初始化轮播图附图
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_slideboxDouble", method = RequestMethod.POST)
    public DoubleSlide innitSlideBoxDouble() {
        return doubleSlideService.selectOneByWrapper(DoubleSlide.builder().status(1).build());
    }

    // 初始化首页活动banner
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_indexActivity", method = RequestMethod.POST)
    public Activity innitIndexActivity() {
        return activityService.selectOneByWrapper(Activity.builder().type(2).build());
    }

    // 初始化评论墙
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_commentBox", method = RequestMethod.POST)
    public List<Comment> innitCommentBox(@RequestParam(value = "size", defaultValue = "4", required = false) Integer size) {
        return commentService.innitCommentBox(size);
    }

    // 初始化底部最新评论
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_newCommentBox", method = RequestMethod.POST)
    public List<ProductDto> innitNewCommentBox(@RequestParam(value = "size", defaultValue = "4", required = false) Integer size) {
        return productService.innitNewCommentBox(size);
    }

    // 初始化底部商品列表
    // 按时间查询四条
    @ResponseBody
    @RequestMapping(value = "innit_buyMore_footer_goods", method = RequestMethod.POST)
    public List<CommentDto> innitFooterGoods(@RequestParam(value = "start", defaultValue = "0") Integer start, @RequestParam(value = "end", defaultValue = "4") Integer end) {
        return productService.innitFooterGoods(start, end);
    }

    // 初始化底部商品列表
    // 按时间查询四条
    @ResponseBody
    @RequestMapping(value = "innit_minPriceGoodsBox", method = RequestMethod.POST)
    public List<ActivityDto> innitMinPriceGoodsBox(@RequestParam(value = "start", defaultValue = "0") Integer start, @RequestParam(value = "end", defaultValue = "4") Integer end) {
        return activityService.innitMinPriceGoodsBox(1, 1, start, end);
    }

    // 初始化优惠商品
    @ResponseBody
    @RequestMapping(value = "innit_offerBox", method = RequestMethod.POST)
    public List<ActivityDto> innitOfferBox(@RequestParam(value = "start", defaultValue = "0") Integer start, @RequestParam(value = "end", defaultValue = "4") Integer end) {
        return activityService.innitMinPriceGoodsBox(1, 2, start, end);
    }

    // 初始化子分类
    @ResponseBody
    @RequestMapping(value = "innit_subcategory", method = RequestMethod.POST)
    public IPage<SubCategory> innitSubCategory(@RequestParam("categoryId") Integer categoryId, Page<SubCategory> page) {
        SubCategory subCategory = SubCategory.builder().categoryId(categoryId).status(1).build();
        return subCategoryService.innitSubCategory(subCategory, page);
    }

    // 初始化商品列表
    @ResponseBody
    @RequestMapping(value = "innit_productListBox", method = RequestMethod.POST)
    public IPage<ProductDto> innitProductListBox(Product product, Page<Product> page) {
        return productService.innitProductListBox(product, page);
    }

    // 初始化商品列表
    @ResponseBody
    @RequestMapping(value = "innit_productDetail", method = RequestMethod.POST)
    public ProductDto innitProductDetail(@RequestParam("productId") Integer productId) {
        return productService.innitProductDetail(productId);
    }

    @ResponseBody
    @RequestMapping(value = "buy_product", method = RequestMethod.POST)
    public ReturnData buyProduct(productBuyDto product) {
        return ReturnData.returnData(null);
    }

}
