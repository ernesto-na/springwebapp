package com.novellius.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.novellius.pojo.Admin;
import com.novellius.pojo.AdminRowMapper;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao{

	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		
	}
	@Override
	public Boolean save(Admin admin) {
		// TODO Auto-generated method stub
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();
//		paramMap.addValue("nombre", admin.getNombre());
//		paramMap.addValue("cargo", admin.getCargo());
//		paramMap.addValue("fechaCreacion", admin.getFechaCreacion());
		
		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);
		
		//Siempre y cuando el nombre de la propiedad sea identica al del setter y getter
		
		
		return jdbcTemplate.update("INSERT INTO Admin(nombre,cargo,fechaCreacion)values(:nombre,:cargo,:fechaCreacion)", paramMap)==1;
	}
	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM admin", new RowMapper<Admin>() {

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Admin admin = new Admin();
				admin.setIdAd(rs.getInt("idAd"));
				admin.setCargo(rs.getString("cargo"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				admin.setNombre(rs.getString("nombre"));
				return admin;
			}
			
		});
	}
	@Override
	public Admin findById(Integer id) {
		
//		return (Admin) jdbcTemplate.query("SELECT * FROM ADMIN WHERE idAd=:idAd"
//				,new MapSqlParameterSource("idAd",id), new AdminRowMapper());
		return jdbcTemplate.queryForObject("SELECT * FROM ADMIN WHERE idAd=:idAd"
				,new MapSqlParameterSource("idAd",id), new AdminRowMapper());
	}
	@Override
	public List<Admin> findByNombre(String nombre) {

		return jdbcTemplate.query("SELECT * FROM ADMIN WHERE nombre like :nombre",
				new MapSqlParameterSource("nombre","%" + nombre + "%"), new AdminRowMapper());
	}
	@Override
	public Boolean update(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println("---->"+admin);
		return jdbcTemplate.update("UPDATE admin SET nombre= :nombre , cargo= :cargo , fechaCreacion= :fechaCreacion where idAd=:idAd",
				new BeanPropertySqlParameterSource(admin))==1;
	}
	@Override
	public Boolean delete(Integer idAd) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM admin where idAd=:idAd",new MapSqlParameterSource("idAd" ,idAd)) == 1;
	}
	@Transactional
	@Override
	public int[] saveAll(List<Admin> admins) {
		// TODO Auto-generated method stub
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(admins.toArray());
		return jdbcTemplate.batchUpdate("INSERT INTO Admin(nombre,cargo,fechaCreacion)values(:nombre,:cargo,:fechaCreacion)"
				, batchArgs);
	}

}
