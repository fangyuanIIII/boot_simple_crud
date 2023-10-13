package com.lt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.bean.CompanyBean;
import com.lt.repository.CompanyRepository;

/**
 * 公司业务类
 * @author luot
 * @date   2023年4月8日
 *
 *
 */
@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	/**
	 * 获取所有名单
	 * @param count
	 * @return
	 */
	public List<CompanyBean> getCompanyDatas(int count){
		StringBuilder querySQL = new StringBuilder();
		querySQL.append("select name,dates,state from (select name,dates,state,rownum rown from (select * from company order by dates desc)) where rown < ")
		.append(count*20 + 20).append(" and rown > ").append(count*20).append(" order by dates desc");
		return companyRepository.query(querySQL.toString());
	}
	
	/**
	 * 根据名称获取公司信息
	 * @param name
	 * @param count
	 * @return
	 */
	public List<CompanyBean> getCompanyDatas(String name,int count){
		StringBuilder querySQL = new StringBuilder();
		querySQL.append("select name,dates,state from (select name,dates,state,rownum rown from company where name like'%")
		.append(name).append("%' order by dates desc) where rown < ")
		.append(count*20 + 20).append(" and rown > ").append(count*20).append(" order by dates desc");
		return companyRepository.query(querySQL.toString());
	}
	
	/**
	 * 添加公司信息
	 * @param name
	 * @param dates
	 * @param state
	 */
	public void add(String name,String dates,String state){
		if(name == "" || name == null || 
				dates == "" || dates == null || 
						state == "" || state == null) {
			throw new RuntimeException("请传输正确的参数");
		}else if(!this.getCompanyDatas(name,0).isEmpty()) {
			throw new RuntimeException("名称重复");
		}
	
		StringBuilder insertSQL = new StringBuilder();
		insertSQL.append("insert into company ").append("values('")
		.append(name).append("',")
		.append("to_date('").append(dates).append("','yyyy/mm/dd')").append(",")
		.append(Integer.parseInt(state)).append(")");
		companyRepository.add(insertSQL.toString());
	}
	
	/**
	 * 删除公司信息
	 * @param name
	 */
	public void delCompany(String name) {
		StringBuilder delSQL = new StringBuilder();
		delSQL.append("delete from company where name ='")
		.append(name).append("'");
		companyRepository.delete(delSQL.toString());
	}
	
	/**
	 * 获取公司总数
	 * @return
	 */
	public int getCompanyCount() {
		String countSQL = "select count(*) from company";
		return companyRepository.count(countSQL);
		
	}
	
	/**
	 * 更新公司状态
	 * @param name
	 * @param state
	 */
	public void updateCompanyState(String name,int state) {
		StringBuilder updateSQL = new StringBuilder();
		updateSQL.append("update company set state=").append(state).append(" where name ='")
		.append(name).append("'");
		companyRepository.update(updateSQL.toString());
	}

}
