/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.tut.session;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author CodeWithLufuno
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
            propertyName = "destinationLookup",
            propertyValue = "Jms/recentBoughtItems"
    ),
    @ActivationConfigProperty(
            propertyName = "destinationType",
            propertyValue = "jakarta.jms.Topic"
    )
})
public class RecentlyBoughtItemMDB {
    
    private static final Logger LOGGER =
            Logger.getLogger(RecentlyBoughtItemMDB.class.getName());

    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;

                System.out.println("Recently bought item:"
                        + textMessage.getText()
                );
            }
        } catch(Exception ex) {
            LOGGER.log(
                    Level.SEVERE,
                    "Could not receive recently bought item.",
                    ex
            );
        }
    }
    
    
    
}
