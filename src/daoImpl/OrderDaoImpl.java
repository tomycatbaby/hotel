package daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.OrderDao;
import entity.Order;
import entity.User;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	@Override
	public void saveOrder(Order order) {
		this.getHibernateTemplate().save(order);

	}

	@Override
	public void removeOrder(Order order) {
		this.getHibernateTemplate().delete(order);

	}

	@Override
	public void updateOrder(Order order) {
		this.getHibernateTemplate().update(order);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrderByName(String name) {
		String hql = "from Order user_order where user_order.name like '" + name + "'";
		return (List<Order>) this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrders() {
		String hql = "from Order user_order order by user_order.id desc";
		return (List<Order>) this.getHibernateTemplate().find(hql);
	}

}
