package com.xlw.onlineshop.entity;

public class Goods {
    private Integer goodsId;
    private String goodsName;
    private Integer categoryId;
    private Double price;
    private String goodsDes;
    private Integer goodsSellCount;
    private String goodsPicture;
    private Category category;

    private Integer count;
    public Goods() {
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getGoodsSellCount() {
        return goodsSellCount;
    }

    public void setGoodsSellCount(Integer goodsSellCount) {
        this.goodsSellCount = goodsSellCount;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGoodsDes() {
        return goodsDes;
    }

    public void setGoodsDes(String goodsDes) {
        this.goodsDes = goodsDes;
    }


    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", goodsDes='" + goodsDes + '\'' +
                ", goodsSellCount=" + goodsSellCount +
                ", goodsPicture='" + goodsPicture + '\'' +
                ", category=" + category +
                ", count=" + count +
                '}';
    }
}
