/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package za.tut.session;

import jakarta.ejb.Local;
import java.util.List;
import za.tut.Entity.Item;

/**
 *
 * @author CodeWithLufuno
 */
@Local
public interface ShoppingCartService {
    public List<Item> checkout();
    public void addToCart(Item item);
    public void replaceItem(int itemID);
}
