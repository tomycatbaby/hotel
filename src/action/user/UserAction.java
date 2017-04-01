package action.user;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.UserService;

public class UserAction extends ActionSupport {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void login() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest res = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<User> list = userService.findAll();
		String str =  URLDecoder.decode(res.getParameter("username"), "UTF-8");
		JSONObject JSON_Object = null;
		System.out.println("userAction+"+response.getCharacterEncoding()+res.getCharacterEncoding());
		JSON_Object = new JSONObject();
		int status;
		User u = new User();
		Iterator<User> it = list.iterator();
		PrintWriter out = response.getWriter();
		
		JSONArray Json_array = new JSONArray();
		while (it.hasNext()) {
			
			u = it.next();
			if (u.getUsername().equals(str) && u.getPassword().equals(password)) {
				
				status = 1;
				session.setAttribute("user", u);
				
				JSON_Object.put("status", status);
				JSON_Object.put("username", u.getUsername());

				Json_array.add(JSON_Object);
				JSON_Object = new JSONObject();
				JSON_Object.put("content", Json_array.toJSONString());
				out.write(JSON_Object.toString());
				out.flush();
				out.close();

			}

		}

		status = 0;
		JSON_Object.put("status", status);
		Json_array.add(JSON_Object);
		JSON_Object = new JSONObject();
		JSON_Object.put("content", Json_array.toJSONString());
		out.write(JSON_Object.toString());
		out.flush();
		out.close();
	}

}
