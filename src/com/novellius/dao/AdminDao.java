package com.novellius.dao;

import java.util.List;

import com.novellius.pojo.Admin;

public interface AdminDao {
	
	public Boolean save(Admin admin);
	
	public List<Admin> findAll();
	
	public Admin findById(Integer id);
	
	public List<Admin> findByNombre(String nombre);
	
	public Boolean update (Admin admin);
	
	public Boolean delete(Integer idAd);
	
	public int[] saveAll(List<Admin> admins);
	
	

}
