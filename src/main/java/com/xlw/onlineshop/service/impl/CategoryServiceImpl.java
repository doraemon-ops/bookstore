package com.xlw.onlineshop.service.impl;

import com.xlw.onlineshop.dao.CategoryDao;
import com.xlw.onlineshop.entity.Category;
import com.xlw.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAllCategory();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDao.updateCategory(category);
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryDao.getCategoryByName(categoryName);
    }

    @Override
    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    @Override
    public void deleteById(Integer categoryId) {
        categoryDao.deleteById(categoryId);
    }
}
