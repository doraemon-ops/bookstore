package com.xlw.onlineshop.dao;

import com.xlw.onlineshop.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsDao {
    List<Goods> getAllGoods();

    List<Goods> getGoodsByCategoryId(Integer categoryId);

    List<Goods> getGoodsTop6();

    List<Goods> getGoodsByKeyWords(String keyWords);

    Goods getGoodsById(Integer goodsId);

    void updateGoodsSellCountById(@Param("goodsId") Integer goodsId,@Param("count") Integer count);

    List<Goods> getGoodsByOrderId(Integer orderId);

    Integer getGoodsCountByCategoryId(Integer categoryId);

    void updateGoods(Goods goods);

    void deleteById(Integer goodsId);

    void addGoods(Goods goods);
}
