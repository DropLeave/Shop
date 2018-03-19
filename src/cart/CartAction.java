package cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import product.Product;
import product.ProductService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ���ﳵAction
 * @author Administrator
 *
 */
public class CartAction extends ActionSupport{
	//������Ʒid 
	private Integer pid;
	//��������
	private Integer count;
	//ע��productService
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
	 * ��session�л�ȡ���ﳵ
	 */
	public Cart getCart(HttpServletRequest request){
		
		//��session�л�ȡ���ﳵ
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//�жϹ��ﳵ�Ƿ����
		if(cart==null){
			 cart=new Cart();
			 request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	//�����Ʒ
	public String addCart(){
		//��ȡ��Ʒ��Ϣ
		Product product=productService.findByPid(pid);
		//����һ��������
		CartItem cartItem=new CartItem();
		cartItem.setProduct(product);
		cartItem.setCount(count);
		Cart cart=getCart(ServletActionContext.getRequest());
		cart.addCart(cartItem);
		return "addCartSuccess";
	}
	//��չ��ﳵ
	public String clearCart(){
		Cart cart=getCart(ServletActionContext.getRequest());
		cart.clearCart();
		return "clearCartSuccess";
	}
	//�Ƴ�������
	public String removeCart(){
		Cart cart=getCart(ServletActionContext.getRequest());
		cart.removeCart(pid);
		return "removeCartSuccess";
	}
	//��ת���ﳵҳ��
	public String myCart(){
		return "myCartSuccess";
	}
}
