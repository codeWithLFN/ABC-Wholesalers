/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatefulEjbClass.java to edit this template
 */
package za.tut.session;

import jakarta.ejb.Stateful;
import jakarta.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import za.tut.Entity.Item;
import za.tut.Interceptor.ShoppingInterceptor;
import za.tut.Interceptor.ReplaceItemInterceptor;

/**
 *
 * @author CodeWithLufuno
 */
@Stateful
public class ShoppingCartBean implements ShoppingCartService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private final List<Item> items = new ArrayList<>();
    
    // Method to get all Items
    @Override
    public List<Item> checkout() {
        return new ArrayList<>(items);
    }

    //Method to add Item
    @Override
    @Interceptors(ShoppingInterceptor.class)
    public void addToCart(Item item) {
        items.add(item);
    }

    //Method to removing an item
    @Override
    @Interceptors(ReplaceItemInterceptor.class)
    public void replaceItem(int itemID) {
        Iterator<Item> iterator = items.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();

            if (item.getItemID() == itemID) {
                iterator.remove();
                return;
            }
        }
    }

}
