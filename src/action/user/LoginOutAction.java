package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class LoginOutAction extends ActionSupport{



	private static final long serialVersionUID = 1L;

	
	public void  loginOut() throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("loginOutAction"+response.getCharacterEncoding());
		session.setAttribute("user", null);
		JSONObject JSON_Object = new JSONObject();
		JSONArray Json_array = new JSONArray();
		int status;
		status=1;
		PrintWriter out = response.getWriter();
		
		JSON_Object.put("status", status);
		Json_array.add(JSON_Object);
		JSON_Object = new JSONObject();
		JSON_Object.put("content", Json_array.toJSONString());
		out.write(JSON_Object.toString());
		out.close();

	}

}
