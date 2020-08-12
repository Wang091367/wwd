package com.xiaoshu.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.dao.ContentcategoryMapper;
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentVo;
import com.xiaoshu.entity.Contentcategory;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.ContentService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("content")
public class ContentController extends LogController{
	static Logger logger = Logger.getLogger(ContentController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private ContentService contentService;
	
	
	@RequestMapping("contentIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Role> roleList = roleService.findRole(new Role());
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("goryList", contentService.findContentcategory());
		return "content";
	}
	
	
	@RequestMapping(value="contentList",method=RequestMethod.POST)
	public void userList(ContentVo contentVo,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			//PageInfo<User> userList= userService.findUserPage(user,pageNum,pageSize,ordername,order);
			
			PageInfo<ContentVo> page = contentService.findContentPage(contentVo, pageNum, pageSize);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",page.getTotal() );
			jsonObj.put("rows", page.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveUser")
	public void reserveUser(HttpServletRequest request,Content content,HttpServletResponse response){
		Integer id = content.getContentid();
		JSONObject result=new JSONObject();
		try {
			if (id != null) {   // userId不为空 说明是修改
				if(content.getPrice().length()==3){
					contentService.updateUser(content);
					result.put("success", true);
				}else{
					result.put("success", true);
					result.put("errorMsg", "价格不能超过三位数!");
				}
				
			}else {   // 添加
				contentService.addUser(content);
					result.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户信息错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	@RequestMapping("deleteUser")
	public void delUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				contentService.deleteUser(Integer.parseInt(id));
			}
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping("echartsContent")
	public void echartsContent(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			List<ContentVo> list = contentService.echartsContent();
			result.put("list", list);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	//导入
	/*@RequestMapping("importContent")
	public void importContent(MultipartFile importFile,HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			
			HSSFWorkbook wb = new HSSFWorkbook(importFile.getInputStream());
			
			HSSFSheet sheet = wb.getSheetAt(0);
			
			int lastRowNum = sheet.getLastRowNum();
			
			for (int i = 1; i <= lastRowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				
				String contenttitle = row.getCell(0).getStringCellValue();
				String categoryname = row.getCell(1).getStringCellValue();
				Integer categoryname1 = findByIdName(categoryname);
				String contenturl = row.getCell(2).getStringCellValue();
				String picpath = row.getCell(3).getStringCellValue();
				Double price1 = row.getCell(4).getNumericCellValue();
				int price = price1.intValue();
				String status = row.getCell(5).getStringCellValue();
				Date createtime = row.getCell(6).getDateCellValue();
				
				Content con = new Content();
				con.setContenttitle(contenttitle);
				con.setContentcategoryid(categoryname1);
				con.setContenturl(contenturl);
				con.setPicpath(picpath);
				con.setPrice(price);
				con.setStatus(status);
				con.setCreatetime(createtime);
				
				contentService.addUser(con);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@Autowired
	private ContentcategoryMapper contentcategoryMapper;
	public Integer findByIdName(String categoryname){
		Contentcategory contentcategory = new Contentcategory();
		contentcategory.setCategoryname(categoryname);
		Contentcategory one = contentcategoryMapper.selectOne(contentcategory);
		return one.getContentcategoryid();
	}
	*/
	
	
}
