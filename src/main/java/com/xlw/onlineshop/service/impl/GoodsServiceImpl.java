package com.xlw.onlineshop.service.impl;

import com.xlw.onlineshop.dao.GoodsDao;
import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Override
    public List<Goods> getAllGoods() {
        return goodsDao.getAllGoods();
    }

    @Override
    public List<Goods> getGoodsByCategoryId(Integer categoryId) {
        return goodsDao.getGoodsByCategoryId(categoryId);
    }

    @Override
    public List<Goods> getGoodsTop6() {
        return goodsDao.getGoodsTop6();
    }

    @Override
    public List<Goods> searchGoods(String keyWords) {
        return goodsDao.getGoodsByKeyWords(keyWords);
    }

    @Override
    public Integer getGoodsCountByCategoryId(Integer categoryId) {
        return goodsDao.getGoodsCountByCategoryId(categoryId);
    }

    @Override
    public Goods getGoodsById(Integer goodsId) {
        return goodsDao.getGoodsById(goodsId);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsDao.updateGoods(goods);
    }

    @Override
    public void deleteById(Integer goodsId) {
        goodsDao.deleteById(goodsId);
    }

    @Override
    public void addGoods(Goods goods) {
        goodsDao.addGoods(goods);
    }
}
