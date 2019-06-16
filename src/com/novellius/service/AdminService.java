package com.novellius.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novellius.dao.AdminDao;
import com.novellius.pojo.Admin;
import java.sql.Timestamp;

@Service
public class AdminService {

	@Autowired
	public AdminDao adminDao;
	
	public Boolean save(Admin admin) {
		
		admin.setFechaCreacion(new Timestamp(new Date().getTime()));
		
		System.out.println(admin.getFechaCreacion());
		
		return adminDao.save(admin);
	}
	
}
