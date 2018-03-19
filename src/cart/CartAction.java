package cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import product.Product;
import product.ProductService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车Action
 * @author Administrator
 *
 */
public class CartAction extends ActionSupport{
	//接受商品id 
	private Integer pid;
	//接受数量
	private Integer count;
	//注入productService
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/*
	 * 从session中获取购物车
	 */
	public Cart getCart(HttpServletRequest request){
		
		//从session中获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//判断购物车是否存在
		if(cart==null){
			 cart=new Cart();
			 request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	//添加商品
	public String addCart(){
		//获取商品信息
		Product product=productService.findByPid(pid);
		//创建一个购物项
		CartItem cartItem=new CartItem();
		cartItem.setProduct(product);
		cartItem.setCount(count);
		Cart cart=getCart(ServletActionContext.getRequest());
		cart.addCart(cartItem);
		return "addCartSuccess";
	}
	//清空购物车
	public String clearCart(){
		Cart cart=getCart(ServletActionContext.getRequest());
		cart.clearCart();
		return "clearCartSuccess";
	}
	//移除购物项
	public String removeCart(){
		Cart cart=getCart(ServletActionContext.getRequest());
		cart.removeCart(pid);
		return "removeCartSuccess";
	}
	//跳转购物车页面
	public String myCart(){
		return "myCartSuccess";
	}
}
