package entity;


import java.util.Date;

public class Order {


	private int id;
	private String orderId;
	private String name;
	private String roomId;
	private int totalMoney;
	private Date start_day;
	private Date final_day;
	private Date book_day;
	private String order_status;
	private String sex;



	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getStart_day() {
		return start_day;
	}
	public void setStart_day(Date start_day) {
		this.start_day = start_day;
	}
	public Date getFinal_day() {
		return final_day;
	}
	public void setFinal_day(Date final_day) {
		this.final_day = final_day;
	}
	public String getOrder_Status() {
		return order_status;
	}
	public void setOrder_Status(String order_status) {
		this.order_status = order_status;
	}



	public Date getBook_day() {
		return book_day;
	}

	public void setBook_day(Date book_day) {
		this.book_day = book_day;
	}
	
	
}
