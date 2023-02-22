package com.xlw.onlineshop.service.impl;

import com.xlw.onlineshop.dao.AdminMemberDao;
import com.xlw.onlineshop.dao.GoodsCarDao;
import com.xlw.onlineshop.dao.OrderDao;
import com.xlw.onlineshop.entity.Member;
import com.xlw.onlineshop.service.AdminMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {

    @Autowired
    private AdminMemberDao adminMemberDao;

    @Autowired
    private GoodsCarDao goodsCarDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Member> getAllMembers() {
        return adminMemberDao.getAllMembers();
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return adminMemberDao.getMemberById(memberId);
    }

    @Override
    public void updateMemberById(Member member) {
        adminMemberDao.updateMemberById(member);
    }

    @Override
    public void deleteMemberById(Integer memberId) {
        goodsCarDao.deleteAllByMemberId(memberId);
        orderDao.deleteMemberOrdersGoods(memberId);
        orderDao.deleteMemberAllOrders(memberId);
        adminMemberDao.deleteMemberById(memberId);
    }

    @Override
    public Member getMemberByName(String memberName) {
        return adminMemberDao.getMemberByName(memberName);
    }

    @Override
    public void addMember(Member member) {
        adminMemberDao.addMember(member);
    }
}
