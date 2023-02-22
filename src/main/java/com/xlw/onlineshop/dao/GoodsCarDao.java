package com.xlw.onlineshop.dao;

import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.entity.GoodsCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsCarDao {
     public List<Goods> getGoodsCarByMemberId(Integer memberId);

    void insertGoods(@Param("goodsId") Integer goodsId,@Param("memberId") Integer memberId,@Param("count") Integer count);

    GoodsCar getGoodsCarByMemberIdAndGoodsId(@Param("goodsId") Integer goodsId,@Param("memberId") Integer memberId);

    void updateGoods(@Param("goodsId") Integer goodsId,@Param("memberId") Integer memberId,@Param("count") Integer count);

    void deleteGoodsCar(@Param("goodsId") Integer goodsId,@Param("memberId") Integer memberId);

    void deleteAllByMemberId(Integer memberId);

    Integer getGoodsCountById(Integer goodsId);
}
