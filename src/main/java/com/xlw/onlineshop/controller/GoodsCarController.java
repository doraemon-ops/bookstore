package com.xlw.onlineshop.controller;

import com.xlw.onlineshop.entity.Category;
import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.entity.Member;
import com.xlw.onlineshop.service.CategoryService;
import com.xlw.onlineshop.service.GoodsCarService;
import com.xlw.onlineshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("goodsCar")
public class GoodsCarController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCarService goodsCarService;

    @GetMapping("/toShoppingCenter")
    public String toShoppingCenter(Model model,HttpSession session){
        Member userSession = (Member)session.getAttribute("userSession");
        List<Category> allCategory = categoryService.getAllCategory();
        List<Goods> allGoods = goodsService.getAllGoods();
        List<Goods> goodsCarGoods = goodsCarService.getGoodsCarGoods(userSession.getMemberId());
        model.addAttribute("allCategory",allCategory);
        model.addAttribute("allGoods",allGoods);
        model.addAttribute("goodsCarGoods",goodsCarGoods);
        return "center/shoppingCenter";
    }

    @GetMapping("/goodsAdd/{goodsId}")
    public String goodsAdd(@PathVariable Integer goodsId, HttpSession session, Model model){
        Member userSession = (Member)session.getAttribute("userSession");
        Integer memberId = userSession.getMemberId();
        goodsCarService.addGoods(goodsId,memberId,0);
        return "redirect:/toShoppingCenter";
    }


    @GetMapping("/goodsSub/{goodsId}")
    public String goodsSub(@PathVariable Integer goodsId, HttpSession session, Model model){
        Member userSession = (Member)session.getAttribute("userSession");
        Integer memberId = userSession.getMemberId();
        goodsCarService.subGoods(goodsId,memberId);
        return "redirect:/toShoppingCenter";
    }

    @GetMapping("/clear")
    public String clearGoodCar(HttpSession session){
        Member userSession = (Member)session.getAttribute("userSession");
        goodsCarService.clear(userSession.getMemberId());
        return "redirect:/toShoppingCenter";
    }


}
