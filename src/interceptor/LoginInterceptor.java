package interceptor;

import java.io.PrintWriter;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import entity.User;

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		HttpServletResponse responce = ServletActionContext.getResponse();
		Map session = context.getSession();
		User user = (User) session.get("user");
		JSONObject JSON_Object = new JSONObject();
		PrintWriter out = responce.getWriter();
		JSONArray jsonArray = new JSONArray();
		int status;
		if (user != null) {
			status=1;
			
			
			return invocation.invoke();
		}
		status=0;
		JSON_Object.put("status", status);
		jsonArray.add(JSON_Object);
		JSON_Object = new JSONObject();
		JSON_Object.put("content", jsonArray.toJSONString());
		out.write(JSON_Object.toString());
		out.close();
		return Action.LOGIN;
	}

}
