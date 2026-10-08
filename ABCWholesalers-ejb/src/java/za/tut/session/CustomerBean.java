/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package za.tut.session;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import za.tut.Entity.Customer;
import za.tut.Entity.Item;

/**
 *
 * @author CodeWithLufuno
 */
@Stateless
public class CustomerBean implements CustomerService {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public void storeCustomer(Customer customer) {
        em.persist(customer);
    }

    @Override
    public Customer validateLogon(String email, String password) {

        // 1. CHECK: Were email and password supplied?
        if (email == null || email.trim().isEmpty()
                || password == null || password.isEmpty()) {
            return null;
        }
        // 2. FIND: Search for the customer using their email.
        List<Customer> matches = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email", Customer.class
        ).setParameter("email", email)
                .getResultList();

        if (matches.isEmpty()) {
            return null;
        }

        Customer customer = matches.get(0);

        // 3. COMPARE: Learning example with a plain-text password.
        if (password.equals(customer.getPassword())) {
            return customer;
        }

        // 4. RETURN: Wrong password means login failed.        
        return null;
    }

    @Override
    public Item findItem(int itemID) {
        return em.find(Item.class, itemID);
    }

    @Override
    public List<Item> getAllItems() {
        return em.createQuery(
                "SELECT i FROM Item i", Item.class
        ).getResultList();
    }
}
