/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.tut.Interceptor;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import za.tut.Entity.Item;

/**
 *
 * @author CodeWithLufuno
 */
public class ShoppingInterceptor {
     @AroundInvoke
    public Object subtractLevy(InvocationContext context) throws Exception {

        Object[] parameters = context.getParameters();

        if (parameters.length > 0
                && parameters[0] instanceof Item) {

            Item item = (Item) parameters[0];

            double newPrice = item.getPrice() - 1.14;

            item.setPrice(newPrice);

            parameters[0] = item;
            context.setParameters(parameters);
        }

        return context.proceed();
    }
}
