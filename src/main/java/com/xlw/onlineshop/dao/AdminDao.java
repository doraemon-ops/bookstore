package com.xlw.onlineshop.dao;

import com.xlw.onlineshop.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminDao {

    Admin getAdmin(Admin admin);
}
