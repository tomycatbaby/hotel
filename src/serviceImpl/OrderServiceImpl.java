package serviceImpl;

import java.util.List;

import dao.OrderDao;
import entity.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public void saveOrder(Order order) {
		this.orderDao.saveOrder(order);

	}

	@Override
	public void removeOrder(Order order) {
		this.orderDao.removeOrder(order);

	}

	@Override
	public void updateOrder(Order order) {
		this.orderDao.updateOrder(order);

	}

	@Override
	public List<Order> findOrderByName(String name) {
		return this.orderDao.findOrderByName(name);
	}

	@Override
	public List<Order> findAllOrders() {
		
		return this.orderDao.findAllOrders();
	}


}
