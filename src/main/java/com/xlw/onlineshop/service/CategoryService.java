package com.xlw.onlineshop.service;

import com.xlw.onlineshop.entity.Category;

import java.util.List;


public interface CategoryService {
    public List<Category> getAllCategory();

    Category getCategoryById(Integer categoryId);

    void updateCategory(Category category);

    Category getCategoryByName(String categoryName);

    void addCategory(Category category);

    void deleteById(Integer categoryId);
}
