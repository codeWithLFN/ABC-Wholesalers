/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.tut;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import za.tut.Entity.Item;
import za.tut.session.CustomerService;
import za.tut.session.ShoppingCartService;

/**
 *
 * @author CodeWithLufuno
 */
@WebServlet(name = "ShoppingServelet" , urlPatterns = {"/ShoppingServelet"})
public class ShoppingServlet extends HttpServlet {
    
    //Inject the local session bean
    @EJB
    private ShoppingCartService shoppingCartService;

    @EJB
    private CustomerService customerService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // Handles GET request
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Handles POST request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("select");
                
        // ADD To cart
        if ("add to cart".equals(action)){
            addToCart(request, response);
            return;
        }
        
        // CHECKOUT
        if ("check out".equals(action)) {
            checkout(request, response);
            return;
        }

        // Unknown action
        response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Invalid shopping action."
        );
    }

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            int itemID = Integer.parseInt(request.getParameter("itemID"));
            int qty = Integer.parseInt(request.getParameter("qty"));
            
            Item item = customerService.findItem(itemID);
            
            item.setQty(qty);
            
            shoppingCartService.addToCart(item);
            
            request.setAttribute("message", item.getName()+ "was added to your cart");
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "shoppingCarting.jsp"
            );
            
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            System.getLogger(ShoppingServlet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(ShoppingServlet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Item> cartItems = shoppingCartService.checkout();
            
            request.setAttribute("cartItems", cartItems);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    "checkout.jsp"
            );
            dispatcher.forward(request, response);
        } catch (ServletException ex) {
            System.getLogger(ShoppingServlet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(ShoppingServlet.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
