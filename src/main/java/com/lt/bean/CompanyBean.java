package com.lt.bean;

import java.io.Serializable;
import java.sql.Date;

import com.lt.util.CompanyState;

/**
 * 公司bean
 * @author luot
 * @date   2023年4月14日
 *
 *
 */
public class CompanyBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3684813448750755713L;

	/**
	 * 公司名称
	 */
	private String name;
	
	/**
	 * 公司时间
	 */
	private Date dates;
	
	/**
	 * 状态
	 */
	private int state;
	
	private String stateInfo;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the date
	 */
	public Date getDates() {
		return dates;
	}

	/**
	 * @param date the date to set
	 */
	public void setDates(Date dates) {
		this.dates = dates;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
		this.stateInfo = CompanyState.getStateInfo(state);
	}

	/**
	 * @return the stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * @param stateInfo the stateInfo to set
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
}
