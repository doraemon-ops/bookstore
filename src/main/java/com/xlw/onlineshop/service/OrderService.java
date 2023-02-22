package com.xlw.onlineshop.service;

import com.xlw.onlineshop.entity.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(Integer memberId);

    Order getCurOrder(Integer memberId);

    List<Order> getAllOrdersByMemberId(Integer memberId);

    Integer getGoodsCountById(Integer goodsId);
}
