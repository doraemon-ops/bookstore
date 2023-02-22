package com.xlw.onlineshop.dao;

import com.xlw.onlineshop.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryDao {
    public List<Category> getAllCategory();

    Category getCategoryById(Integer categoryId);

    void updateCategory(Category category);

    Category getCategoryByName(String categoryName);

    void addCategory(Category category);

    void deleteById(Integer categoryId);
}
