package com.lt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lt.bean.CompanyBean;
import com.lt.service.CompanyService;

@Controller
public class ActionJob {
	
	@Autowired
	CompanyService companySer;

	/**
	 * ��ת��˾ҳ��
	 * @return
	 */
	@GetMapping("/query")
	public String companyView(HttpServletRequest request) {
		String name = request.getParameter("name");
		String sizeStr = request.getParameter("size");
		
		int size = 0;
		List<CompanyBean> beans = null;
		
		if(sizeStr != null && sizeStr != "") {
			size = Integer.parseInt(request.getParameter("size"));
		}
		
		if(name == null || name == "") {
			beans = companySer.getCompanyDatas(size);
		}else {
			
			beans = companySer.getCompanyDatas(name,size);
		}
		int count = companySer.getCompanyCount();
		request.setAttribute("sizes",String.valueOf(size));
		request.setAttribute("lastsizes",String.valueOf(size-1));
		request.setAttribute("nextsizes",String.valueOf(size+1));
		request.setAttribute("endsizes",String.valueOf(count/20 == size));
		request.setAttribute("count",count);
		request.setAttribute("maxpagesize",count/20);
		request.setAttribute("companydatas", beans);
		request.setAttribute("info", "query");
		request.setAttribute("buttonVal", "EDIT");
		return "company";
	}
	
	/**
	 * ��ת��˾ҳ��
	 * @return
	 */
	@GetMapping("/edit")
	public String companyEdit(HttpServletRequest request) {
		request.setAttribute("info", "edit");
		request.setAttribute("buttonVal", "QUERY");
		return "company";
	}
	
	/**
	 * ��ת��˾ҳ��
	 * @return
	 */
	@PostMapping("/getcompanydatas")
	@ResponseBody
	public String getCompanyDatas() {
		List<CompanyBean> beans = companySer.getCompanyDatas(0);
		return JSON.toJSONString(beans);
	}
	
	/**
	 * 
	 * @return
	 */
	@PostMapping("/addcompanydatas")
	@ResponseBody
	public String addCompanyDatas(HttpServletRequest request) {
		String name = request.getParameter("name");
		String dates = request.getParameter("dates");
		String state = request.getParameter("state");
		try {
			companySer.add(name, dates, state);			
		}catch(Exception e) {
			return e.getMessage();
		}
		return "";
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/delcompanydata")
	public String delCompanyData(HttpServletRequest request) {
		String name = request.getParameter("name");
		try {
			companySer.delCompany(name);		
		}catch(Exception e) {
			
		}
		return "redirect:/query";
	}
	
	@GetMapping("/updatecompanydatanotnotice")
	public String updateCompanyDataNotNotice(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		companySer.updateCompanyState(name, 22);
		return "redirect:/query";
	}
	
	@GetMapping("/updatecompanydataunpassed")
	public String updateCompanyDataUnpassed(HttpServletRequest request) {
		String name = request.getParameter("name");
		companySer.updateCompanyState(name, 24);
		return "redirect:/query";
	}
	
	
}
