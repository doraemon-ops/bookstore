package com.xlw.onlineshop.service.impl;

import com.xlw.onlineshop.dao.GoodsCarDao;
import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.entity.GoodsCar;
import com.xlw.onlineshop.service.GoodsCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsCarServiceImpl implements GoodsCarService {

    @Autowired
    private GoodsCarDao goodsCarDao;

    @Override
    public List<Goods> getGoodsCarGoods(Integer memberId) {
        return goodsCarDao.getGoodsCarByMemberId(memberId);
    }

    @Override
    public void addGoods(Integer goodsId, Integer memberId,Integer count) {
        GoodsCar goodsCar = goodsCarDao.getGoodsCarByMemberIdAndGoodsId(goodsId, memberId);
        if(goodsCar == null){
            goodsCarDao.insertGoods(goodsId,memberId,1);
        }else{
            goodsCarDao.updateGoods(goodsId,memberId,goodsCar.getCount()+1);
        }

    }

    @Override
    public void subGoods(Integer goodsId, Integer memberId) {
        GoodsCar goodsCar = goodsCarDao.getGoodsCarByMemberIdAndGoodsId(goodsId, memberId);
        if(goodsCar.getCount() <= 1){
            goodsCarDao.deleteGoodsCar(goodsId, memberId);
        }else{
            goodsCarDao.updateGoods(goodsId,memberId,goodsCar.getCount()-1);
        }
    }

    @Override
    public void clear(Integer memberId) {
        goodsCarDao.deleteAllByMemberId(memberId);
    }

    @Override
    public Integer getGoodsCountById(Integer goodsId) {
        return goodsCarDao.getGoodsCountById(goodsId);
    }

}
