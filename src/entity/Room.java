package entity;

public class Room {
	private int id;
	private String roomId;
	private String roomType;
	private String direction;
	private int limitLive;
	private int spareLive;
	private int price;
	private float grade;
	private int comment;
	private String sex;
	
	
	
	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public float getGrade() {
		return grade;
	}


	public void setGrade(float grade) {
		this.grade = grade;
	}


	public int getComment() {
		return comment;
	}


	public void setComment(int comment) {
		this.comment = comment;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getLimitLive() {
		return limitLive;
	}

	public void setLimitLive(int limitLive) {
		this.limitLive = limitLive;
	}

	public int getSpareLive() {
		return spareLive;
	}

	public void setSpareLive(int spareLive) {
		this.spareLive = spareLive;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
