package com.xlw.onlineshop.controller.admin;

import com.xlw.onlineshop.entity.Admin;
import com.xlw.onlineshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/index")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/toAdminCenter")
    public String toAdminCenter(){
        return "/admin/adminCenter";
    }

    @PostMapping("/login")
    public String loginCheck(String adminName, String password, HttpSession session, Model model){
        if(adminName == null || password == null || adminName.equals("") || password.equals("")){
            model.addAttribute("msg","用户名或密码不能为空！");
            return "adminLogin";
        }else{
            Admin admin = new Admin();
            admin.setAdminName(adminName);
            admin.setPassword(password);
            Admin resAdmin = adminService.getAdmin(admin);
            if(resAdmin == null){
                model.addAttribute("msg","用户名或密码错误！");
                return "adminLogin";
            }else{
                admin.setPassword(null);
                session.setAttribute("adminSession",resAdmin);
            }
        }
        return "redirect:/admin/index/toAdminCenter";
    }


}
