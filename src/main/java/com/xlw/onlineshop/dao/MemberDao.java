package com.xlw.onlineshop.dao;

import com.xlw.onlineshop.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberDao {

    Member getMember(Member member);

    Member getMemberById(Integer memberId);

    Member getMemberByName(String memberName);

    void addMember(Member member);
}
