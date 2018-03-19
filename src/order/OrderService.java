package order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import user.User;
import utils.PageBean;

@Transactional
public class OrderService {

	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public Integer saveOrder(Order order) {
		return orderDao.saveOrder(order);
		
	}

	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}

	public void updateOrder(Order currorder) {
		orderDao.updateOrder(currorder);
		
	}

	public List<User> findByUid(Object attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Order> findByUid(Integer uid) {
		
		return orderDao.findByUid(uid);
	}

	public PageBean<Order> findByPage(Integer page) {
		
		return orderDao.findByPage(page);
	}

	public PageBean<Order> findByState(Integer page, Integer state) {
		
		return orderDao.findBySate(page,state);
	}

}
