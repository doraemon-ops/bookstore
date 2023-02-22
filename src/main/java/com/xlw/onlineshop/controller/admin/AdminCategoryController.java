package com.xlw.onlineshop.controller.admin;

import com.xlw.onlineshop.entity.Category;
import com.xlw.onlineshop.service.CategoryService;
import com.xlw.onlineshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/toCategoryManage")
    public String toCategoryManage(Model model){
        List<Category> categoryList = categoryService.getAllCategory();
        model.addAttribute("categoryList",categoryList);
        return "/admin/category";
    }

    @GetMapping("/edit/{categoryId}")
    public String toEdit(@PathVariable("categoryId") Integer categoryId,Model model){
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category",category);
        return "/admin/categoryEdit";
    }

    @PostMapping("/edit")
    public String edit(Category category,Model model){
        if(category.getCategoryName() == null){
            model.addAttribute("msg","分类名不能为空");
            Category resCategory = categoryService.getCategoryById(category.getCategoryId());
            model.addAttribute("category",resCategory);
            return "/admin/categoryEdit";
        }else{
            categoryService.updateCategory(category);
        }
        return "redirect:/admin/category/toCategoryManage";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "/admin/categoryAdd";
    }

    @PostMapping("/add")
    public String add(Category category,Model model){
        if (category.getCategoryName() == null) {
            model.addAttribute("msg","分类名不能为空");
            return "/admin/categoryAdd";
        }else{
            Category categoryRes = categoryService.getCategoryByName(category.getCategoryName());
            if(categoryRes != null){
                model.addAttribute("msg","分类名已存在");
                return "/admin/categoryAdd";
            }else{
                categoryService.addCategory(category);
                return "redirect:/admin/category/toCategoryManage";
            }
        }

    }

    @GetMapping("/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") Integer categoryId,Model model){
        Integer goodsCount = goodsService.getGoodsCountByCategoryId(categoryId);
        if(goodsCount != 0){
            model.addAttribute("msg","该分类仍有商品存在无法直接删除！");
            List<Category> categoryList = categoryService.getAllCategory();
            model.addAttribute("categoryList",categoryList);
            return "/admin/category";
        }else{
            categoryService.deleteById(categoryId);
            return "redirect:/admin/category/toCategoryManage";
        }
    }




}
