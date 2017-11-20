




import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class SaxPaserDataStore extends DefaultHandler {
    Item item;
    static Map<String, Item> items = new HashMap<String, Item>();
    String XmlFileName;
    String elementValueRead;

    
    public SaxPaserDataStore(String XmlFileName) {
        this.XmlFileName = XmlFileName;
        // items 
        parseDocument();
        // prettyPrint();
    }


    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(XmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


    private void prettyPrint() {
	
        for (Item item: items.values()) {
        System.out.println("item #"+ item.getItemId() +":");
        System.out.println("\t\t name: " + item.getItemName());
        System.out.println("\t\t Price: " + item.getPrice());
        System.out.println("\t\t Discount: " + item.getDiscount());
        System.out.println("\t\t Rebates: " + item.getRebates());
        System.out.println("\t\t Accessories: " + item.getAccessories());
        }
    }



    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("item")) {
            item = new Item();
            item.setItemId(attributes.getValue("id"));
        }

    }

    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("item")) {
            items.put(item.getItemId(),item);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            item.setItemName(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
            item.setPrice(Double.parseDouble(elementValueRead));
        return;
        }
        if(element.equalsIgnoreCase("discount")){
            item.setDiscount(Double.parseDouble(elementValueRead));
        return;
        }        
        if(element.equalsIgnoreCase("stock")){
            item.setStock(Integer.parseInt(elementValueRead));
        return;
        }
        if(element.equalsIgnoreCase("rebate")){
            item.setRebates(Double.parseDouble(elementValueRead));
        return;
        }        
        if(element.equalsIgnoreCase("accessory")){
            item.getAccessories().add(elementValueRead);
        return;
        }

    }

    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    public static Map<String, Item> getItems(String XmlFileName) {
        new SaxPaserDataStore(XmlFileName);
        // System.out.println(items);
        return items;
    }

    public static Item getItem(String itemID) {
        if (itemID == null) {
            return(null);
        }
        for (Item item : items.values()){
            if(itemID.equals(item.getItemId())){
                return(item);
            }
        }
        return(null);
    }

    public static boolean AddItem(Item item){
        if(items.containsKey(item.getItemId())){
            return false;
        }
        items.put(item.getItemId(),item);
        return true;
    }
    public static boolean RemoveItem(String ItemId){

        items.remove(ItemId);
        return true;
    }
    public static boolean UpdateItem(Item item){

        items.remove(item.getItemId());
        items.put(item.getItemId(),item);
        return true;
    }
    // public static void main(String[] args) {
    //     getItems("ProductCatalog.xml");

    // }

}
