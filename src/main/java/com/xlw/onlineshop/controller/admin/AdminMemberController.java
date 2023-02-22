package com.xlw.onlineshop.controller.admin;

import com.xlw.onlineshop.entity.Member;
import com.xlw.onlineshop.service.AdminMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

    @Autowired
    private AdminMemberService adminMemberService;

    @GetMapping("/toMemberManage")
    public String toMemberManage(Model model){
        model.addAttribute("memberList",adminMemberService.getAllMembers());
        return "/admin/member";
    }

    @GetMapping("/edit/{memberId}")
    public String toMemberEdit(@PathVariable("memberId") Integer memberId, Model model){
        Member member = adminMemberService.getMemberById(memberId);
        model.addAttribute("member",member);
        System.out.println(member.toString());
        return "/admin/memberEdit";
    }

    @PostMapping("/edit")
    public String memberEdit(Member member,Model model){
        if(member.getMemberName()==null || member.getSex()==null || member.getPhoneNum()==null || member.getAddress()==null){
            model.addAttribute("msg","信息不能为空");
            Member preMember = adminMemberService.getMemberById(member.getMemberId());
            model.addAttribute("member",preMember);
            return "/admin/memberEdit";
        }
        adminMemberService.updateMemberById(member);
        return "redirect:/admin/member/toMemberManage";
    }

    @GetMapping("/delete/{memberId}")
    public String memberDelete(@PathVariable("memberId") Integer memberId){
        adminMemberService.deleteMemberById(memberId);
        return "redirect:/admin/member/toMemberManage";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "/admin/memberAdd";
    }

    @PostMapping("/add")
    public String memberAdd(Member member,Model model){
        if(member.getMemberName() == null || member.getPassword()==null || member.getAddress()==null
                || member.getPhoneNum() == null || member.getSex()==null){
            model.addAttribute("msg","信息不能为空");
            return "/admin/memberAdd";
        }else{
            if(adminMemberService.getMemberByName(member.getMemberName())!= null){
                model.addAttribute("msg","该用户名以被注册");
                return "/admin/memberAdd";
            }else{
                adminMemberService.addMember(member);
            }
        }
        return "redirect:/admin/member/toMemberManage";
    }
}
