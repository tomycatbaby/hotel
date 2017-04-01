package action.order;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import service.OrderService;

public class OrderAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	public void getOrder() throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONObject JSON_Object = new JSONObject();
		User user = (User) session.getAttribute("user");

		List<Order> order = orderService.findAllOrders();
		
		PrintWriter out = response.getWriter();
		JSONArray Json_array = new JSONArray();
		String s = null;
		String f = null;
		String b = null;
		Order o = new Order();
		Iterator<Order> it = order.iterator();

		while (it.hasNext()) {

			o = it.next();
			if (o.getName().equals(user.getName())) {
				 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				s = sdf.format(o.getStart_day());
				f = sdf.format(o.getFinal_day());
				b = sdf.format(o.getBook_day());
				
				System.out.println(s);
				{
					JSON_Object.put("orderId", o.getOrderId());
					JSON_Object.put("name", o.getName());
					JSON_Object.put("roomId", o.getRoomId());
					JSON_Object.put("startDay", s);
					JSON_Object.put("finalDay", f);
					JSON_Object.put("bookDay", b);
					JSON_Object.put("totalMoney", o.getTotalMoney());
					JSON_Object.put("Status", o.getOrder_Status());
				}
				Json_array.add(JSON_Object);
				JSON_Object = new JSONObject();

				JSON_Object.put("content", Json_array.toJSONString());
				out.write(JSON_Object.toString());
				out.flush();
				out.close();
				return;

			}
		}


	}

}
