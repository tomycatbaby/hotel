package action.room;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import entity.Room;

public class SortByGradeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject JSON_Object = new JSONObject();
		PrintWriter out = response.getWriter();
		JSONArray Json_array = new JSONArray();
		Room room = null;
		List<Room> rooms = (List<Room>) session.getAttribute("rooms");
		System.out.println("AAAAAAAA");
		List<Room> r = sort(rooms);
		System.out.println("AAAAAAAA" + r.isEmpty());
		Iterator<Room> iterator = r.iterator();
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

	public List<Room> sort(List<Room> rooms) {
		float min = 0;
		List<Room> r = new ArrayList<Room>();
		int n = rooms.size();
		Room room = null;
		for (int i = 0; i < n; i++) {
			min = rooms.get(0).getGrade();
			room = rooms.get(0);
			// ÕÒ×îÐ¡µÄgrade
			for (int j = 1; j < rooms.size(); j++) {
				if (min > rooms.get(j).getGrade()) {
					min = rooms.get(j).getGrade();
					room = rooms.get(j);
				}
			}
			rooms.remove(room);
			r.add(room);

		}

		return r;
	}
}