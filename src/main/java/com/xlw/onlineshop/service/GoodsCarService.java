package com.xlw.onlineshop.service;

import com.xlw.onlineshop.entity.Goods;

import java.util.List;

public interface GoodsCarService {
    List<Goods> getGoodsCarGoods(Integer memberId);

    void addGoods(Integer goodsId, Integer memberId,Integer count);

    void subGoods(Integer goodsId, Integer memberId);

    void clear(Integer memberId);

    Integer getGoodsCountById(Integer goodsId);
}
