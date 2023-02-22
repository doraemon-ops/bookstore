package com.xlw.onlineshop.dao;

import com.xlw.onlineshop.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDao {

    void addOrder(Order order);

    void addOrderAndGoods(@Param("orderId") Integer orderId,@Param("goodsId") Integer goodsId,@Param("count")Integer count);

    Integer getOrderIdByOrderNumber(String strUUID);

    List<Integer> getOrderIdByMemberId(Integer memberId);

    Order getOrderById(Integer orderId);

    void deleteMemberOrdersGoods(Integer memberId);

    void deleteMemberAllOrders(Integer memberId);

    Integer getGoodsCountById(Integer goodsId);
}
