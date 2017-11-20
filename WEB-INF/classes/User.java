import java.util.*;
public class User {
	private String userId;
	private String password;
	private int level;
	private ShoppingCart cart;
	private Map<String, Order> ordersMap = new HashMap<String, Order>();

	public User(String uId, String pwd, int lvl, ShoppingCart cart){
		setUserId(uId);
		setPassword(pwd);
		setLevel(lvl);
		setCart(cart);
	}
	public User(String uId, String pwd, int lvl){
		setUserId(uId);
		setPassword(pwd);
		setLevel(lvl);
	}
	public void setUserId(String uId){
		this.userId = uId;
	}
	public void setPassword(String pwd){
		this.password = pwd;
	}
	public void setLevel(int lvl){
		this.level = lvl;
	}
	public String getUserId(){
		return(userId);
	}
	public String getPassword(){
		return(password);
	}
	public int getLevel(){
		return(level);
	}
	public void setCart(ShoppingCart cart){
		this.cart = cart;
	}
	public ShoppingCart getCart(){
		return cart;
	}
	public void addToOrders(Order order) {
		ordersMap.put(order.getOrderId(), order);
	}
	public Map<String, Order> getOrdersMap() {
		return ordersMap;
	}
	public void cancelOrder(String oId) {
		ordersMap.remove(oId);
	}
}