

import java.util.*;

 
public class ShoppingCart {
  

  private int cnt;
  private ArrayList itemsOrdered;


  /** Builds an empty shopping cart. */
  
  public ShoppingCart() {
    itemsOrdered = new ArrayList();
  }


  /** Returns List of ItemOrder entries giving
   *  Item and number ordered. Declared as List instead
   *  of ArrayList so that underlying implementation
   *  can be changed later.
   */
  
  public List getItemsOrdered() {
    return(itemsOrdered);
  }

  /** Looks through cart to see if it already contains
   *  an order entry corresponding to item ID. If it does,
   *  increments the number ordered. If not, looks up
   *  Item in catalog and adds an order entry for it.1
   */
  
  public synchronized void addItem(String itemID) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemId().equals(itemID)) {
        order.incrementNumItems();
        return;
      }
    }
    ItemOrder newOrder = new ItemOrder(SaxPaserDataStore.getItem(itemID));
    itemsOrdered.add(newOrder);
  }

  /** Looks through cart to find order entry corresponding
   *  to item ID listed. If the designated number
   *  is positive, sets it. If designated number is 0
   *  (or, negative due to a user input error), deletes
   *  item from cart.
   */
  
  public synchronized void setNumOrdered(String itemID,
                                         int numOrdered) {
    ItemOrder order;
    for(int i=0; i<itemsOrdered.size(); i++) {
      order = (ItemOrder)itemsOrdered.get(i);
      if (order.getItemId().equals(itemID)) {
        if (numOrdered <= 0) {
          itemsOrdered.remove(i);
        } else {
          order.setNumItems(numOrdered);
        }
        return;
      }
    }
    ItemOrder newOrder =
      new ItemOrder(SaxPaserDataStore.getItem(itemID));
    itemsOrdered.add(newOrder);
  }

  public int getItemNumber(){
    ItemOrder order;
    int total = 0;
    // if (itemsOrdered != null){
    for (int i=0; i<itemsOrdered.size();i++){
      order = (ItemOrder)itemsOrdered.get(i);
      total += order.getNumItems();
    }
    // }
    return total;
  }

  public void clearCart(){
    itemsOrdered.clear();
    System.out.println("if cart is clean, the content is :" + itemsOrdered);
  }

}
    
