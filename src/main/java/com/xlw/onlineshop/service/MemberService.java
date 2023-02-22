package com.xlw.onlineshop.service;

import com.xlw.onlineshop.entity.Member;

public interface MemberService {

    Member getMember(Member member);

    Member getMemberById(Integer memberId);

    Member getMemberByName(String memberName);

    void addMember(Member member);
}
