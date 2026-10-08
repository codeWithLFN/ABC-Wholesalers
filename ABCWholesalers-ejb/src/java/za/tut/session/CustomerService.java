/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package za.tut.session;

import jakarta.ejb.Local;
import java.util.List;
import za.tut.Entity.Customer;
import za.tut.Entity.Item;

/**
 *
 * @author CodeWithLufuno
 */
@Local
public interface CustomerService {
    public void storeCustomer(Customer customer);
    public Customer validateLogon(String email, String password);
    public Item findItem(int itemID);
    public List<Item> getAllItems();
    
}
