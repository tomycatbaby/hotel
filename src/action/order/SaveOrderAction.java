package action.order;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

import entity.Order;
import entity.User;
import service.BedService;
import service.OrderService;
import service.RoomService;

public class SaveOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoomService roomService;
	private BedService bedService;
	private OrderService orderService;
	private int totalMoney;
	private String roomId;
	private String start_day;
	private String final_day;

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public BedService getBedService() {
		return bedService;
	}

	public void setBedService(BedService bedService) {
		this.bedService = bedService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

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
		User user = (User) session.getAttribute("user");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Order newOrder = new Order();
		String orderId = "R0000"+user.getId();
		Date sday = sdf.parse(start_day);
		Date fday = sdf.parse(final_day);
		//订单表生成
		{
			newOrder.setOrderId(orderId);
			newOrder.setName(user.getName());
			newOrder.setRoomId(roomId);
			newOrder.setTotalMoney(totalMoney);
			newOrder.setStart_day(sday);
			newOrder.setFinal_day(fday);
			newOrder.setOrder_Status("预定中");
			newOrder.setBook_day(date);
			newOrder.setSex(user.getSex());
			orderService.saveOrder(newOrder);
		}
		JSON_Object.put("content", Json_array.toJSONString());
		out.write(JSON_Object.toString());
		out.flush();
		out.close();
		return SUCCESS;
	}
}
