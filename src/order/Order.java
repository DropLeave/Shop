package order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Transient;

import user.User;

/*
 * ������ʵ����
 */
	
	public class Order {
		private Integer oid;
		private Double total;
		private Date ordertime;
		private Integer state;
		private String addr;
		private String phone;
		private String name;
		// �������������û�
		private User user;
		// �ŵ��Ƕ�����ļ���.
		
		private Set<OrderItem> orderItems = new HashSet<OrderItem>();
		public Integer getOid() {
			return oid;
		}
		public void setOid(Integer oid) {
			this.oid = oid;
		}
		public Double getTotal() {
			return total;
		}
		public void setTotal(Double total) {
			this.total = total;
		}
		public Date getOrdertime() {
			return ordertime;
		}
		public void setOrdertime(Date ordertime) {
			this.ordertime = ordertime;
		}
		public Integer getState() {
			return state;
		}
		public void setState(Integer state) {
			this.state = state;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Set<OrderItem> getOrderItems() {
			return orderItems;
		}
		public void setOrderItems(Set<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}
	
}