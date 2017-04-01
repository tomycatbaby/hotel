package dao;

import java.util.List;

import entity.Order;

public interface OrderDao {
	public void saveOrder(Order order);
	public void removeOrder(Order order);
	public void updateOrder(Order order);
	public List<Order> findOrderByName(String name);
	public List<Order> findAllOrders();
}
