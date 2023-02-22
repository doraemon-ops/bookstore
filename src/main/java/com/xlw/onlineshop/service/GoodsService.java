package com.xlw.onlineshop.service;

import com.xlw.onlineshop.entity.Goods;

import java.util.List;

public interface GoodsService {

    public List<Goods> getAllGoods();

    public List<Goods> getGoodsByCategoryId(Integer categoryId);

    public List<Goods> getGoodsTop6();

    List<Goods> searchGoods(String keyWords);

    Integer getGoodsCountByCategoryId(Integer categoryId);

    Goods getGoodsById(Integer goodsId);

    void updateGoods(Goods goods);

    void deleteById(Integer goodsId);

    void addGoods(Goods goods);
}
