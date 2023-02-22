package com.xlw.onlineshop.controller;

import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.entity.Member;
import com.xlw.onlineshop.entity.Order;
import com.xlw.onlineshop.service.GoodsCarService;
import com.xlw.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private GoodsCarService goodsCarService;

    @Autowired
    private OrderService orderService;



    @GetMapping("/settlement")
    public String settlement(HttpSession session, Model model){
        Member userSession = (Member)session.getAttribute("userSession");
        List<Goods> goodsCarGoods = goodsCarService.getGoodsCarGoods(userSession.getMemberId());
        Double totalPrice = 0.0;
        for (Goods goods : goodsCarGoods) {
            totalPrice += goods.getCount()*goods.getPrice();
        }
        Order order = orderService.getCurOrder(userSession.getMemberId());
        order.setMember(userSession);
        order.setTotalPrice(totalPrice);
        model.addAttribute("order",order);
        return "/center/settlement";
    }


    @GetMapping("/addOrder")
    public String print(HttpSession session, Model model){
        Member userSession = (Member)session.getAttribute("userSession");
        List<Goods> goodsCarGoods = goodsCarService.getGoodsCarGoods(userSession.getMemberId());
        Double totalPrice = 0.0;
        for (Goods goods : goodsCarGoods) {
            totalPrice += goods.getCount()*goods.getPrice();
        }
        Order order = orderService.addOrder(userSession.getMemberId());
        order.setTotalPrice(totalPrice);
        order.setMember(userSession);
        model.addAttribute("order",order);
        return "/center/order";
    }


    @GetMapping("/getAllOrders")
    public String getAllOrders(HttpSession session, Model model){
        Member userSession = (Member)session.getAttribute("userSession");
        List<Order> orderList = orderService.getAllOrdersByMemberId(userSession.getMemberId());
        System.out.println(orderList.toString());
        model.addAttribute("orderList",orderList);

        return "center/allOrders";
    }

}
