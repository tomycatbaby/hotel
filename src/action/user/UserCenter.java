package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import entity.User;


public class UserCenter extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		User user = (User) session.getAttribute("user");
		PrintWriter out = response.getWriter();
		JSONObject JSON_Object = new JSONObject();
		int status;
		JSONArray Json_array = new JSONArray();
		if (user.getStatus().equals("0")) {

			JSON_Object.put("image", user.getPhoto());
			JSON_Object.put("name", user.getName());
			JSON_Object.put("idCard", user.getIDCard_number());
			JSON_Object.put("cellphone", user.getCellphone());
			JSON_Object.put("sex", user.getSex());
			JSON_Object.put("userStatus", user.getStatus());

			Json_array.add(JSON_Object);
			JSON_Object = new JSONObject();
			JSON_Object.put("content", Json_array.toJSONString());
			out.write(JSON_Object.toJSONString());
			out.flush();
			out.close();
		}
		else {
			status = 1;
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
