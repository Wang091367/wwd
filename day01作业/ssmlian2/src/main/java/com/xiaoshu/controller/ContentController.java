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
import com.xiaoshu.entity.Content;
import com.xiaoshu.entity.ContentVo;
import com.xiaoshu.entity.Operation;
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

	private String categoryname; 
	
	@RequestMapping("contentIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("goryList", contentService.findCategory());
		return "content";
	}
	
	
	@RequestMapping(value="contentList",method=RequestMethod.POST)
	public void userList(ContentVo contentVo,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
		//	PageInfo<User> userList= userService.findUserPage(user,pageNum,pageSize,ordername,order);
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
			if (id != null) {  
				contentService.updateUser(content);
					result.put("success", true);
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
	
	
	//导入
	  @RequestMapping("importContent")
	  public void importContent(MultipartFile importFile,HttpServletRequest request,HttpServletResponse response){
	   JSONObject result=new JSONObject();
	   try {
	    //获取webbook
	    HSSFWorkbook wb = new HSSFWorkbook(importFile.getInputStream());
	     
	    //获取sheet页
	    HSSFSheet sheet = wb.getSheetAt(0);
	    //获取最后一行行数
	    int lastRowNum = sheet.getLastRowNum();
	    for (int i = 1; i <= lastRowNum; i++) {
	     //获取每一行的对象
	     HSSFRow row = sheet.getRow(i);
	     String contenttitle = row.getCell(0).toString();
	     String picpath = row.getCell(1).toString();
	     String price = row.getCell(2).toString();
	     String staus = row.getCell(3).toString();
	     String contenturl = row.getCell(4).toString();
	     Date createtime = row.getCell(5).getDateCellValue();
	     String categoryname = row.getCell(6).toString();
	     
	     int contentcategoryid = contentService.findCidByCname(categoryname);
	     //封装实体类
	     Content person = new Content();
	     person.setContenttitle(contenttitle);;
	     person.setPicpath(picpath);
	     person.setPrice(price);
	     person.setStatus(staus);
	     person.setContentcategoryid(contentcategoryid);
	     person.setContenturl(contenturl);
	     person.setCreatetime(createtime);
	     //保存
	     contentService.addUser(person);
	    }
	    result.put("success", true);
	   } catch (Exception e) {
	    e.printStackTrace();
	    logger.error("删除用户信息错误",e);
	    result.put("errorMsg", "对不起，删除失败");
	   }
	   WriterUtil.write(response, result.toString());
	  }
	
	
	@RequestMapping("editPassword")
	public void editPassword(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser.getPassword().equals(oldpassword)){
			User user = new User();
			user.setUserid(currentUser.getUserid());
			user.setPassword(newpassword);
			try {
				userService.updateUser(user);
				currentUser.setPassword(newpassword);
				session.removeAttribute("currentUser"); 
				session.setAttribute("currentUser", currentUser);
				result.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("修改密码错误",e);
				result.put("errorMsg", "对不起，修改密码失败");
			}
		}else{
			logger.error(currentUser.getUsername()+"修改密码时原密码输入错误！");
			result.put("errorMsg", "对不起，原密码输入错误！");
		}
		WriterUtil.write(response, result.toString());
	}
}
