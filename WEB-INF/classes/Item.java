import java.util.*;
import java.io.Serializable;

public class Item implements Serializable{
	private String itemID;
	private String itemName;
	private double price;
	private double discount;
	private double rebates;
	private int stocks;
	private List<String> accessories = new ArrayList<String>();

	public Item(String iID, String iNa, double co, double dis, double reb, int sto){
		setItemId(iID);
		setItemName(iNa);
		setPrice(co);
		setDiscount(dis);
		setRebates(reb);
		setStock(sto);
	}
	public Item(String iID, String iNa, double co, double dis, double reb, int sto, List<String> acc){
		setItemId(iID);
		setItemName(iNa);
		setPrice(co);
		setDiscount(dis);
		setRebates(reb);
		setStock(sto);
		setAccessories(acc);
	}	

	public Item() {
		super();
	}
	
	public void setItemId(String iID){
		this.itemID = iID;
	}
	public void setPrice(double co){
		this.price = co;
	}
	public void setItemName(String iNa){
		this.itemName = iNa;
	}
	public void setDiscount(double dis){
		this.discount = dis;
	}
	public void setRebates(double reb){
		this.rebates = reb;
	}
	public String getItemId(){
		return(itemID);
	}
	public double getPrice(){
		return(price);
	}
	public String getItemName(){
		return(itemName);
	}
	public double getDiscount(){
		return(discount);
	}
	public double getRebates(){
		return(rebates);
	}
	public List<String> getAccessories() {
		return accessories;
	}
	public void setAccessories(List<String> accessories) {
		this.accessories = accessories;
	}
	public int getStock(){
		return(stocks);
	}
	public void setStock(int sto){
		this.stocks = sto;
	}


}