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
public class ReplaceItemInterceptor {
    @AroundInvoke
    public Object addLevy(InvocationContext context) throws Exception {

        Object[] parameters = context.getParameters();

        if (parameters.length > 0
                && parameters[0] instanceof Item) {

            Item item = (Item) parameters[0];

            double price = item.getPrice();

            double levy;

            if (price >= 1000.00) {
                levy = 0.08;
            } else {
                levy = 0.03;
            }

            item.setPrice(price + levy);

            parameters[0] = item;
            context.setParameters(parameters);
        }

        return context.proceed();
    }
}
