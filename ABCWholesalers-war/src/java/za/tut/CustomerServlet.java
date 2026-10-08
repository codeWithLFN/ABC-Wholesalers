/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.tut;

import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import za.tut.Entity.Customer;
import za.tut.session.CustomerService;

/**
 *
 * @author CodeWithLufuno
 */
@WebServlet(name="CustomerServlet" , urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {
    
    @EJB
    CustomerService customerService;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String action = request.getParameter("Select");
        
        if ("login".equals(action))
        {
            login(request, response);
        }else{
            request.setAttribute("message", "Invalid action");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Read form values
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        //Check that input were entered
        if(email == null || email.trim().isEmpty() || password == null
                || password.trim().isEmpty()) {
            
            request.setAttribute("massage",
                    "Email and password are required"
            );
            
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        }
        
        // EJB validating the user
        Customer customer = customerService.validateLogon(
                email, 
                password
        );
        
        if (customer != null) {
            request.setAttribute("customer", customer);
            
            request.getRequestDispatcher("shoppingCarting.jsp")
                    .forward(request, response);
        }else{
            request.setAttribute("message", "Invalid email or password");
            
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
