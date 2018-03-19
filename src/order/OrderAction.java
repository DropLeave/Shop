package order;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import user.User;
import utils.PageBean;
import utils.PaymentUtil;
import cart.Cart;
import cart.CartItem;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport {
	// 付款成功后的需要的参数:
	private String r3_Amt;
	private String r6_Order;
	private String pd_FrpId;//银行Id
	private Order order;
	private Integer oid;
	private Integer page;
	private Integer state;
	
	public void setPage(Integer page) {
		this.page = page;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public void setOid(Integer oid) {
		this.oid = oid;
	}


	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}


	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}


	


	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	public Order getOrder() {
		return order;
	}
	

	public void setOrder(Order order) {
		this.order = order;
	}


	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}


	//保存订单
	public String saveOrder(){
		order=new Order();
		/************封装订单数据****************/
		order.setOrdertime(new Date());
		/*1：未付款
		 * 2：已经付款
		 * 3：已发货
		 * 4：已收货
		 * 
		 */
		order.setState(1);
		//获取购物车
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionMessage("你还未购物！请先去购物！");
			return "msg";
		}
		if(cart.getTotal()==0){
			
			this.addActionMessage("你还未购物！请先去购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		
		//订单所属用户
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			this.addActionMessage("你还未登录！请先登录！");
			return "msg";
		}
		order.setUser(user);
		/**************封装订单项********************/
		//从购物车中获取订单项数据
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		cart.clearCart();
		Integer oid=orderService.saveOrder(order);
		order=orderService.findByOid(oid);
		return "saveOrderSuccess";
	}
	//在线付款
	public String  payOrder() throws IOException{
		//修改订单状态，地址，手机，联系人
		//获取订单
		Order currorder=orderService.findByOid(order.getOid());
		currorder.setAddr(order.getAddr());
		currorder.setName(order.getName());
		currorder.setPhone(order.getPhone());
		orderService.updateOrder(currorder);
		// 付款:
				// 定义付款的参数:
				String p0_Cmd = "Buy";
				String p1_MerId = "10001126856";
				String p2_Order = order.getOid().toString();
				String p3_Amt = "0.01";
				String p4_Cur = "CNY";
				String p5_Pid = "";
				String p6_Pcat = "";
				String p7_Pdesc = "";
				String p8_Url = "http://192.168.9.110:8080/Shop/order_callBack.action";//ip地址
				String p9_SAF = "";
				String pa_MP = "";
				String pr_NeedResponse = "1";
				String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
				String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,pd_FrpId , pr_NeedResponse, keyValue);
				
				StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
				sb.append("p0_Cmd=").append(p0_Cmd).append("&");
				sb.append("p1_MerId=").append(p1_MerId).append("&");
				sb.append("p2_Order=").append(p2_Order).append("&");
				sb.append("p3_Amt=").append(p3_Amt).append("&");
				sb.append("p4_Cur=").append(p4_Cur).append("&");
				sb.append("p5_Pid=").append(p5_Pid).append("&");
				sb.append("p6_Pcat=").append(p6_Pcat).append("&");
				sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
				sb.append("p8_Url=").append(p8_Url).append("&");
				sb.append("p9_SAF=").append(p9_SAF).append("&");
				sb.append("pa_MP=").append(pa_MP).append("&");
				sb.append("pd_FrpId=").append(pd_FrpId).append("&");
				sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
				sb.append("hmac=").append(hmac);
				System.out.println(sb.toString());
				HttpServletResponse response = ServletActionContext.getResponse();
				response.sendRedirect(sb.toString());
				return NONE;
			}
			
			
			/*
			 * 付款成功后的回调方法
			 */
			public String callBack(){
				Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
				currOrder.setState(2);// 修改订单状态.
				orderService.updateOrder(currOrder);
				
				this.addActionMessage("订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
				System.out.println("订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
				return "msg";
			}
	//通过用户id获取用户订单
	public String findByUid(){
		List<Order>orderList=orderService.findByUid(((User) ServletActionContext.getRequest().getSession().getAttribute("user")).getUid());
		ActionContext.getContext().getValueStack().set("orderList", orderList);
		return "findByUidSuccess";
	}
	
	//通过订单id查询订单
	public String findByOid(){
		order=orderService.findByOid(oid);
		return "findByOidSuccess";
	}
	/*
	 * 后台查询订单
	 */
	public String adminFindAll(){
		PageBean<Order> pageBean=orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindAllSuccess";
	}
	public String adminFindByState(){
		PageBean<Order> pageBean=orderService.findByState(page,state);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "adminFindByStateSuccess";
	}
	public String adminUpdateState(){
		order=orderService.findByOid(oid);
		order.setState(3);
		orderService.updateOrder(order);
		return "adminUpdateStateSuccess";
	}
	public String updateState(){
		order=orderService.findByOid(oid);
		order.setState(4);
		orderService.updateOrder(order);
		return "updateStateSuccess";
	}
}
