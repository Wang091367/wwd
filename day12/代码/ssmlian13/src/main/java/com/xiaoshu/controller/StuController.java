package com.xiaoshu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.Stu;
import com.xiaoshu.entity.StuVo;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.StuService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("stu")
public class StuController extends LogController{
	static Logger logger = Logger.getLogger(StuController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private StuService stuService;
	
	
	@RequestMapping("stuIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Role> roleList = roleService.findRole(new Role());
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("majorList", stuService.findMajor());
		return "stu";
	}
	
	
	@RequestMapping(value="stuList",method=RequestMethod.POST)
	public void userList(StuVo stuVo,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			//PageInfo<User> userList= userService.findUserPage(user,pageNum,pageSize,ordername,order);
			PageInfo<StuVo> page = stuService.findStuPage(stuVo, pageNum, pageSize);
			
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
	public void reserveUser(HttpServletRequest request,Stu stu,HttpServletResponse response){
		Integer id = stu.getSdId();
		JSONObject result=new JSONObject();
		try {
			if (id != null) {   // userId不为空 说明是修改
				stuService.updateUser(stu);
					result.put("success", true);
			}else {   // 添加
				stuService.addUser(stu);
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
				stuService.deleteUser(Integer.parseInt(id));
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
	
	@RequestMapping("exportStu")
	public void exportStu(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String time = TimeUtil.formatTime(new Date(), "yyyyMMddHHmmss");
		    String excelName = "手动备份"+time;
			StuVo stuVo = new StuVo();
			List<StuVo> list = stuService.findExportStu(stuVo);
			String[] handers = {"学生编号","学生姓名","学生性别","学生爱好","学生生日","专业"};
			// 1导入硬盘
			ExportExcelToDisk(request,handers,list, excelName);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	// 导出到硬盘
		@SuppressWarnings("resource")
		private void ExportExcelToDisk(HttpServletRequest request,
				String[] handers, List<StuVo> list, String excleName) throws Exception {
			
			try {
				HSSFWorkbook wb = new HSSFWorkbook();//创建工作簿
				HSSFSheet sheet = wb.createSheet("操作记录备份");//第一个sheet
				HSSFRow rowFirst = sheet.createRow(0);//第一个sheet第一行为标题
				rowFirst.setHeight((short) 500);
				for (int i = 0; i < handers.length; i++) {
					sheet.setColumnWidth((short) i, (short) 4000);// 设置列宽
				}
				//写标题了
				for (int i = 0; i < handers.length; i++) {
				    //获取第一行的每一个单元格
				    HSSFCell cell = rowFirst.createCell(i);
				    //往单元格里面写入值
				    cell.setCellValue(handers[i]);
				}
				for (int i = 0;i < list.size(); i++) {
				    //获取list里面存在是数据集对象
					StuVo vo = list.get(i);
				    //创建数据行
				    HSSFRow row = sheet.createRow(i+1);
				    //设置对应单元格的值
				    row.setHeight((short)400);   // 设置每行的高度
				    //"序号","操作人","IP地址","操作时间","操作模块","操作类型","详情"
				    if(vo.getSdhobby().contains("篮球") && vo.getMdname().equals("大数据")){
				    	   row.createCell(0).setCellValue(i+1);
						    row.createCell(1).setCellValue(vo.getSdname());
						    row.createCell(2).setCellValue(vo.getSdsex());
						    row.createCell(3).setCellValue(vo.getSdhobby());
						    row.createCell(4).setCellValue(TimeUtil.formatTime(vo.getSdbirth(), "yyyy-MM-dd"));
						    row.createCell(5).setCellValue(vo.getMdname());
					}
				}
				//写出文件（path为文件路径含文件名）
					OutputStream os;
					File file = new File("D:/xiaoshixun"+File.separator+excleName+".xls");
					
					if (!file.exists()){//若此目录不存在，则创建之  
						file.createNewFile();  
						logger.debug("创建文件夹路径为："+ file.getPath());  
		            } 
					os = new FileOutputStream(file);
					wb.write(os);
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
		}

	
		
		@RequestMapping("echartsStu")
		public void echartsStu(HttpServletRequest request,HttpServletResponse response){
			JSONObject result=new JSONObject();
			try {
				List<StuVo> list = stuService.findEchartsStu();
				result.put("success", true);
				result.put("list", list);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("删除用户信息错误",e);
				result.put("errorMsg", "对不起，删除失败");
			}
			WriterUtil.write(response, result.toString());
		}
	
		
		@RequestMapping("addStu1")
		public void addStu1(Major major,HttpServletRequest request,HttpServletResponse response){
			JSONObject result=new JSONObject();
			try {
				stuService.addStu1(major);
				result.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("保存专业信息错误",e);
				result.put("errorMsg", "对不起，保存失败");
			}
			WriterUtil.write(response, result.toString());
		}
}
