/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.tut.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author CodeWithLufuno
 */
@Entity
public class Item implements Serializable {
    private int itemID;
    private String name;
    private String itemType;
    private int qty;
    private double price;

    public int getItemID() {
        return itemID;
    }

    //constructor
    public Item() {
    }

    public Item(int itemID, String name, String itemType, int qty, double price) {
        this.itemID = itemID;
        this.name = name;
        this.itemType = itemType;
        this.qty = qty;
        this.price = price;
    }

    // Getter and Setter
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "itemID=" + itemID + ", name=" + name + ", itemType=" + itemType + ", qty=" + qty + ", price=" + price + '}';
    }
 
}
