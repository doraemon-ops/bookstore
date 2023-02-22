package com.xlw.onlineshop.dao;

import com.xlw.onlineshop.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminMemberDao {

    List<Member> getAllMembers();

    Member getMemberById(Integer memberId);

    void updateMemberById(Member member);

    void deleteMemberById(Integer memberId);

    Member getMemberByName(String memberName);

    void addMember(Member member);
}
