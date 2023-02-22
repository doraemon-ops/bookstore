package com.xlw.onlineshop.service;

import com.xlw.onlineshop.entity.Member;

import java.util.List;

public interface AdminMemberService {

    List<Member> getAllMembers();

    Member getMemberById(Integer memberId);

    void updateMemberById(Member member);

    void deleteMemberById(Integer memberId);

    Member getMemberByName(String memberName);

    void addMember(Member member);
}
