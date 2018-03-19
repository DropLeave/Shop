package cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//购物项的集合
	//商品的ID作为map的Key,商品的购物项作为map的Value
	private Map<Integer,CartItem> map=new HashMap<Integer, CartItem>();
	//总计
	private Double total=0d;
	
	//获取map中value集合
	//相当与拥有属性cartItems
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public Double getTotal() {
		return total;
	}

	//添加商品
	public void addCart(CartItem cartItem){
		//获取添加商品的pid
		Integer pid=cartItem.getProduct().getPid();
		//判断购物车是否拥有该商品
		if(map.containsKey(pid)){
			//已经拥有该商品
			//获取购物车里该商品的购物项
			CartItem _cartItem=map.get(pid);
			//修改数量
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//没有该商品
			map.put(pid, cartItem);
		}
		//总计
		total+=cartItem.getSubtotal();
	}
	//移除商品
	public void removeCart(Integer pid){
		CartItem cartItem=map.remove(pid);
		total-=cartItem.getSubtotal();
		
	}
	//清空购物车
	public void clearCart(){
		//清除map
		map.clear();
	
		//总计为0
		total=0d;
	}
}
