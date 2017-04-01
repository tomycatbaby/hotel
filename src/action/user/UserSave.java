package action.user;

import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.UserService;

public class UserSave extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	private String name;
	private String sex;
	private String idCard;
 	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Override
	public String execute() throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String n = URLDecoder.decode(request.getParameter("name"), "UTF-8");
		User user = (User)session.getAttribute("user");
		PrintWriter out =  response.getWriter();
		JSONObject JSON_Object = new JSONObject();
		int status;
		JSONArray Json_array = new JSONArray();
		if(user!=null){
			user.setCellphone(cellphone);
			user.setSex(sex);
			user.setName(n);
			user.setIDCard_number(idCard);
			user.setStatus("0");
			this.userService.update(user);

			status = 1;
			JSON_Object.put("status", status);
			Json_array.add(JSON_Object);
			JSON_Object = new JSONObject();
			JSON_Object.put("content", Json_array.toJSONString());
			out.write(JSON_Object.toJSONString());
			out.flush();
			out.close();
		}
		else{
			status = 0;
			JSON_Object.put("status", status);
			Json_array.add(JSON_Object);
			JSON_Object = new JSONObject();
			JSON_Object.put("content", Json_array.toJSONString());
			out.write(JSON_Object.toJSONString());
			out.flush();
			out.close();
			return INPUT;
		}
		return SUCCESS;
	}
}
