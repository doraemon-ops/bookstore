package com.xlw.onlineshop.controller;

import com.xlw.onlineshop.entity.Category;
import com.xlw.onlineshop.entity.Goods;
import com.xlw.onlineshop.entity.Member;
import com.xlw.onlineshop.service.CategoryService;
import com.xlw.onlineshop.service.GoodsCarService;
import com.xlw.onlineshop.service.GoodsService;
import com.xlw.onlineshop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCarService goodsCarService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String toLoginPage(){
        return "login";
    }

    @GetMapping("/toAdminLogin")
    public String toAdminLogin(){
        return "adminLogin";
    }

    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }


    @GetMapping("/toShoppingCenter")
    public String toShoppingCenter(Model model,HttpSession session){
        Member userSession = (Member)session.getAttribute("userSession");
        List<Category> allCategory = categoryService.getAllCategory();
        List<Goods> allGoods = goodsService.getAllGoods();
        List<Goods> goodsCarGoods = goodsCarService.getGoodsCarGoods(userSession.getMemberId());
        model.addAttribute("allCategory",allCategory);
        model.addAttribute("allGoods",allGoods);
        model.addAttribute("goodsCarGoods",goodsCarGoods);
        return "center/shoppingCenter";
    }

    @PostMapping("/login")
    public String login(@RequestParam String memberName, @RequestParam String password, HttpSession session, Model model){
        if(memberName == null || password == null || memberName.equals("") || password.equals("")){
            model.addAttribute("msg","用户名或密码不能为空！");
            return "login";
        }else{
            Member member = new Member();
            member.setMemberName(memberName);
            member.setPassword(password);
            Member resMember = memberService.getMember(member);
            if(resMember == null){
                model.addAttribute("msg","用户名或密码错误！");
                return "login";
            }else{
                member.setPassword(null);
                session.setAttribute("userSession",resMember);
            }
        }
        return "redirect:/toShoppingCenter";
    }

    @PostMapping("/register")
    public String register(Member member,Model model){
        if(member.getMemberName() == null || member.getPassword()==null || member.getAddress()==null
                || member.getPhoneNum() == null || member.getSex()==null){
            model.addAttribute("msg","信息不能为空");
            return "register";
        }else{
            if(memberService.getMemberByName(member.getMemberName())!= null){
                model.addAttribute("msg","该用户名以被注册");
                return "register";
            }else{
                memberService.addMember(member);
                model.addAttribute("msg","注册成功！");
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userSession");
        return "redirect:/";
    }

}
