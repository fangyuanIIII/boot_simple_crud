package com.lt.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.lt.bean.CompanyBean;

/**
 * 公司仓库类
 * @author luot
 * @date   2023年4月14日
 *
 *
 */
@Repository
public class CompanyRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	/**
	 * 查询
	 * @param querySQL
	 * @return
	 */
	public List<CompanyBean> query(String querySQL) {
		RowMapper<CompanyBean> rowMapper = new BeanPropertyRowMapper<>(CompanyBean.class);
		return jdbcTemplate.query(querySQL, new Object[] {  }, rowMapper);
	}
	
	/**
	 * 新增
	 * @param insertSQL
	 */
	public void add(String insertSQL) {
		jdbcTemplate.execute(insertSQL);
	}
	
	/**
	 * 删除
	 * @param delSQL
	 */
	public void delete(String delSQL) {
		jdbcTemplate.execute(delSQL);
	}
	
	/**
	 * 更新
	 * @param updateSQL
	 */
	public void update(String updateSQL) {
		jdbcTemplate.update(updateSQL);
	}
	
	/**
	 * 统计
	 * @param countSQL
	 * @return
	 */
	public int count(String countSQL) {
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(countSQL);
		while(rowSet.next()) {
			return rowSet.getInt(1);
		}
		return 0;
	}
	
	public String getDbName() {
		try {
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			DatabaseMetaData dbdata = conn.getMetaData();
			return dbdata.getDatabaseProductName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
