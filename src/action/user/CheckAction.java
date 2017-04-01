package action.user;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.UserService;

public class CheckAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private UserService userService;

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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void check() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest res = ServletActionContext.getRequest();
		res.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("checkAction"+response.getCharacterEncoding()+res.getCharacterEncoding());
		List<User> list = userService.findAll();
		JSONObject JSON_Object = null;
		JSON_Object = new JSONObject();
		int status;
		User u = new User();
		Iterator<User> it = list.iterator();
		PrintWriter out = response.getWriter();
		JSONArray Json_array = new JSONArray();
		while (it.hasNext()) {
			System.out.println(u.getUsername()+"&&"+ username );
			u = it.next();
			if (u.getUsername().equals(username)) {
				status = 0;
				JSON_Object.put("status", status);
				Json_array.add(JSON_Object);
				JSON_Object = new JSONObject();
				
				JSON_Object.put("content", Json_array.toJSONString());
				out.write(JSON_Object.toJSONString());
				out.flush();
				out.close();
				
			}

		}
		status = 1;
		JSON_Object.put("status", status);
		JSON_Object.put("username", username);
		Json_array.add(JSON_Object);
		JSON_Object = new JSONObject();
		JSON_Object.put("content", Json_array.toJSONString());
		out.write(JSON_Object.toString());
		out.flush();
		out.close();
	}

}
