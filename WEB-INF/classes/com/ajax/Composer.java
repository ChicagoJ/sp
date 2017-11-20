package com.ajax;

public class Composer {

    private String id;
    private String firstName;
    private String lastName;
    private double price;
    
    
    public Composer (String id, String firstName, double price) {
        this.id = id;
        this.firstName = firstName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    
    public String getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    

}