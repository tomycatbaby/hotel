package action.room;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import action.tool.SortByPrice;
import entity.Room;

public class SortByPriceAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session  = ServletActionContext.getRequest().getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject JSON_Object = new JSONObject();
		PrintWriter out = response.getWriter();
		JSONArray Json_array = new JSONArray();
		Room room = null;
		List<Room> rooms = (List<Room>)session.getAttribute("rooms");
		Comparator<Room> comparator = new SortByPrice();
		Collections.sort(rooms,comparator);
		Iterator<Room> iterator = rooms.iterator();
		while (iterator.hasNext()) {
			room = iterator.next();
			System.out.println(room.getRoomId());
			JSON_Object.put("roomId", room.getRoomId());
			JSON_Object.put("grade", room.getGrade());
			JSON_Object.put("comment", room.getComment());  
			JSON_Object.put("sparelive", room.getSpareLive());
			JSON_Object.put("limitLive", room.getLimitLive());
			JSON_Object.put("price", room.getPrice());
			JSON_Object.put("direction", room.getDirection());
			Json_array.add(JSON_Object);
			JSON_Object = new JSONObject();
		}
		JSON_Object.put("content", Json_array.toJSONString());
		out.write(JSON_Object.toString());
		out.flush();
		out.close();
		return SUCCESS;
	}
}
