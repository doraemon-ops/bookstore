package com.xlw.onlineshop.service.impl;

import com.xlw.onlineshop.dao.GoodsCarDao;
import com.xlw.onlineshop.dao.GoodsDao;
import com.xlw.onlineshop.dao.MemberDao;
import com.xlw.onlineshop.dao.OrderDao;
import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.entity.Member;
import com.xlw.onlineshop.entity.Order;
import com.xlw.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private GoodsCarDao goodsCarDao;

    @Override
    public Order addOrder(Integer memberId) {
        Order order = new Order();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = simpleDateFormat.format(date);
        List<Goods> goodsList = goodsCarDao.getGoodsCarByMemberId(memberId);
        UUID uuid = UUID.randomUUID();
        String strUUID = uuid.toString();
        order.setOrderDate(strDate);
        order.setOrderNumber(strUUID);
        order.setGoodsList(goodsList);
        order.setMemberId(memberId);
//        System.out.println("***********");
//        System.out.println(order.toString());
//        System.out.println("***********");
        orderDao.addOrder(order);
        Integer orderId = orderDao.getOrderIdByOrderNumber(strUUID);
        System.out.println("orderId = " + orderId);
        order.setOrderId(orderId);
        for (Goods goods : goodsList) {
            orderDao.addOrderAndGoods(orderId,goods.getGoodsId(),goods.getCount());
            goodsDao.updateGoodsSellCountById(goods.getGoodsId(),goods.getCount()+goods.getGoodsSellCount());
        }
        goodsCarDao.deleteAllByMemberId(memberId);
        return order;
    }

    @Override
    public Order getCurOrder(Integer memberId) {
        Order order = new Order();
        List<Goods> goodsList = goodsCarDao.getGoodsCarByMemberId(memberId);
        order.setGoodsList(goodsList);
        order.setMemberId(memberId);
        return order;
    }

    @Override
    public List<Order> getAllOrdersByMemberId(Integer memberId) {
        List<Integer> orderIdList = orderDao.getOrderIdByMemberId(memberId);
        List<Order> orderList = new ArrayList<>();
        for (Integer orderId : orderIdList) {
            Order order = orderDao.getOrderById(orderId);
            Double totalPrice = 0.0;
            List<Goods> goodsList = goodsDao.getGoodsByOrderId(orderId);
            for (Goods goods : goodsList) {
                totalPrice += goods.getCount()*goods.getPrice();
            }
            Member member = memberDao.getMemberById(order.getMemberId());
            order.setMember(member);
            order.setTotalPrice(totalPrice);
            order.setGoodsList(goodsList);
            orderList.add(order);
        }

        return orderList;
    }

    @Override
    public Integer getGoodsCountById(Integer goodsId) {
        return orderDao.getGoodsCountById(goodsId);
    }
}
