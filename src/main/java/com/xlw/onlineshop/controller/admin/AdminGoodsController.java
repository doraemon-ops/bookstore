package com.xlw.onlineshop.controller.admin;

import com.xlw.onlineshop.entity.Category;
import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.service.CategoryService;
import com.xlw.onlineshop.service.GoodsCarService;
import com.xlw.onlineshop.service.GoodsService;
import com.xlw.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/goods")
public class AdminGoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsCarService goodsCarService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/toGoodsManage")
    public String toGoodsManage(Model model){
        List<Goods> goodsList = goodsService.getAllGoods();
        for (Goods goods : goodsList) {
            Category category = categoryService.getCategoryById(goods.getCategoryId());
            goods.setCategory(category);
        }
        model.addAttribute("goodsList",goodsList);
        return "/admin/goods";
    }

    @GetMapping("/edit/{goodsId}")
    public String toEdit(@PathVariable("goodsId") Integer goodsId, Model model){
        Goods goods = goodsService.getGoodsById(goodsId);
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("goods",goods);
        model.addAttribute("categoryList",categoryList);
        return "/admin/goodsEdit";
    }

    @PostMapping("/edit")
    public String edit(Goods goods,Model model){
        if(goods.getGoodsName()==null  ||  goods.getGoodsSellCount()==null ||goods.getGoodsPicture()==null
        || goods.getCategoryId()==null || goods.getPrice()==null || goods.getGoodsDes()==null){
            model.addAttribute("msg","内容不能为空");
            Goods resGoods = goodsService.getGoodsById(goods.getGoodsId());
            List<Category> categoryList = categoryService.getAllCategory();
            model.addAttribute("goods",resGoods);
            model.addAttribute("categoryList",categoryList);
            return "/admin/goodsEdit";
        }else{
            goodsService.updateGoods(goods);
            return "redirect:/admin/goods/toGoodsManage";
        }
    }

    @GetMapping("delete/{goodsId}")
    public String delete(@PathVariable("goodsId") Integer goodsId,Model model){
       Integer goodsCountInGoodsCar = goodsCarService.getGoodsCountById(goodsId);
       Integer goodsCountInOrder = orderService.getGoodsCountById(goodsId);
       if(goodsCountInGoodsCar!=0 || goodsCountInOrder !=0){
           model.addAttribute("msg","该商品已在订单或者购物车中暂时无法删除");
           List<Goods> goodsList = goodsService.getAllGoods();
           for (Goods goods : goodsList) {
               Category category = categoryService.getCategoryById(goods.getCategoryId());
               goods.setCategory(category);
           }
           model.addAttribute("goodsList",goodsList);
           return "/admin/goods";
       }else{
           goodsService.deleteById(goodsId);
           return "redirect:/admin/goods/toGoodsManage";
       }
    }

    @GetMapping("/toAdd")
    public String toAdd(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        return "/admin/goodsAdd";
    }

    @PostMapping("/add")
    public String add(Goods goods,Model model){
        System.out.println(goods.toString());
        if(goods.getGoodsName()==null ||goods.getGoodsPicture()==null
                || goods.getCategoryId()==null || goods.getPrice()==null || goods.getGoodsDes()==null){
            model.addAttribute("msg","内容不能为空");
            List<Category> categoryList = categoryService.getAllCategory();
            model.addAttribute("categoryList",categoryList);
            return "/admin/goodsAdd";
        }else{
            goods.setGoodsSellCount(0);
            goodsService.addGoods(goods);
            return "redirect:/admin/goods/toGoodsManage";
        }
    }

}
