package com.xiaoshu.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.Teacher;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.StudentService;
import com.xiaoshu.service.TeacherService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("student")
public class StudentController extends LogController{
	static Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private RoleService roleService ;
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService ;
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("studentIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Role> roleList = roleService.findRole(new Role());
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		List<Teacher> tlist=teacherService.findTeacher();
		request.setAttribute("tlist", tlist);
		request.setAttribute("operationList", operationList);
		request.setAttribute("roleList", roleList);
		return "student";
	}
	
	
	@RequestMapping(value="userList",method=RequestMethod.POST)
	public void userList(Student student,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Student> userList= studentService.findUserPage(student,pageNum,pageSize);
			
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",userList.getTotal() );
			jsonObj.put("rows", userList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveUser")
	public void reserveUser(MultipartFile picFile,HttpServletRequest request,Student student,HttpServletResponse response) throws IllegalStateException, IOException{
		Integer userId = student.getSid();
		JSONObject result=new JSONObject();
		if(picFile!=null && picFile.getSize()>0){
			//获取图片名称
			String filename = picFile.getOriginalFilename();
			//获取后缀名称
			String suffix = filename.substring(filename.lastIndexOf("."));
			//重新定义名称
			String newFileName = System.currentTimeMillis()+suffix;
			//创建虚拟路径
			File file = new File("f:/pic/"+newFileName);
			//上传
			picFile.transferTo(file);
			//将新的文件名称保存到实体类中
			student.setPic(newFileName);
		}
		try {
			if (userId != null) {   // userId不为空 说明是修改
				
					student.setSid(userId);
					studentService.updateUser(student);
					result.put("success", true);
				
				
			}else {   // 添加
				
					studentService.addUser(student);
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
				studentService.deleteUser(Integer.parseInt(id));
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
	
	//报表
		@RequestMapping("getEcharts")
		public void getEcharts(HttpServletRequest request,HttpServletResponse response){
			JSONObject result=new JSONObject();
			try {
				//查询报表
				List<Student> slist = studentService.getEcharts();
				result.put("slist", slist);
				result.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("删除用户信息错误",e);
				result.put("errorMsg", "对不起，删除失败");
			}
			WriterUtil.write(response, result.toString());
		}
}
