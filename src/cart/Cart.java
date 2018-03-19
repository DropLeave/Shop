package cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	//������ļ���
	//��Ʒ��ID��Ϊmap��Key,��Ʒ�Ĺ�������Ϊmap��Value
	private Map<Integer,CartItem> map=new HashMap<Integer, CartItem>();
	//�ܼ�
	private Double total=0d;
	
	//��ȡmap��value����
	//�൱��ӵ������cartItems
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public Double getTotal() {
		return total;
	}

	//�����Ʒ
	public void addCart(CartItem cartItem){
		//��ȡ�����Ʒ��pid
		Integer pid=cartItem.getProduct().getPid();
		//�жϹ��ﳵ�Ƿ�ӵ�и���Ʒ
		if(map.containsKey(pid)){
			//�Ѿ�ӵ�и���Ʒ
			//��ȡ���ﳵ�����Ʒ�Ĺ�����
			CartItem _cartItem=map.get(pid);
			//�޸�����
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			//û�и���Ʒ
			map.put(pid, cartItem);
		}
		//�ܼ�
		total+=cartItem.getSubtotal();
	}
	//�Ƴ���Ʒ
	public void removeCart(Integer pid){
		CartItem cartItem=map.remove(pid);
		total-=cartItem.getSubtotal();
		
	}
	//��չ��ﳵ
	public void clearCart(){
		//���map
		map.clear();
	
		//�ܼ�Ϊ0
		total=0d;
	}
}
