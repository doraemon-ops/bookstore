package com.xlw.onlineshop.service.impl;

import com.xlw.onlineshop.dao.AdminDao;
import com.xlw.onlineshop.entity.Admin;
import com.xlw.onlineshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdmin(Admin admin) {
        return adminDao.getAdmin(admin);
    }
}
