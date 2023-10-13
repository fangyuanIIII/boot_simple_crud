package com.lt.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 公司状态
 * @author luot
 * @date   2023年4月14日
 *
 *
 */
public class CompanyState {

	static final int unreviewed = 11;
	
	static final int unreviewed_unpassed = 12;
	
	static final int unreviewed_expire = 13;
	
	static final int reviewed_expire = 21;
	
	static final int reviewed_not_notice = 22;
	
	static final int reviewed_passed = 23;
	
	static final int reviewed_unpassed = 24;
	
	static final int reviewed_worked = 25;
	
	private static Map<Integer,String> states;
	
	static {
		states = new HashMap<>();
		states.put(unreviewed, "未查阅");
		states.put(unreviewed_unpassed, "未查阅_不通过");
		states.put(unreviewed_expire, "未查阅_已过期");
		states.put(reviewed_expire, "查阅_已过期");
		states.put(reviewed_not_notice, "查阅_未反馈");
		states.put(reviewed_passed, "查阅_通过");
		states.put(reviewed_unpassed, "查阅_不通过");
		states.put(reviewed_worked, "工作过");
	}
	
	/**
	 * 获取状态信息
	 * @param state
	 * @return
	 */
	public static String getStateInfo(int state) {
		if(!states.isEmpty()) {
			return states.get(state);
		}
		return null;
	}
}
