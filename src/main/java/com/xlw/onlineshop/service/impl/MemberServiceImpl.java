package com.xlw.onlineshop.service.impl;

import com.xlw.onlineshop.dao.MemberDao;
import com.xlw.onlineshop.entity.Member;
import com.xlw.onlineshop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member getMember(Member member) {
        return memberDao.getMember(member);
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberDao.getMemberById(memberId);
    }

    @Override
    public Member getMemberByName(String memberName) {
        return memberDao.getMemberByName(memberName);
    }

    @Override
    public void addMember(Member member) {
        memberDao.addMember(member);
    }
}
