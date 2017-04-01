package entity;

import java.util.Date;

public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String IDCard_number;
	private String address;
	private String sex;
	private int money;
	private Date birthday;
	private String cellphone;
	private String photo;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User() {

	}



	public User(int id, String username, String password, String name, String iDCard_number, String address, String sex,
			int money, Date birthday, String cellphone, String photo, String status) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		IDCard_number = iDCard_number;
		this.address = address;
		this.sex = sex;
		this.money = money;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.photo = photo;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIDCard_number() {
		return IDCard_number;
	}

	public void setIDCard_number(String iDCard_number) {
		IDCard_number = iDCard_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
