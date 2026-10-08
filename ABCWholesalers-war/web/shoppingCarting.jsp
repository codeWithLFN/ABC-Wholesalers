<%-- 
    Document   : shoppingCarting.jsp
    Created on : 07 Oct 2026, 20:08:56
    Author     : CodeWithLufuno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ShoppingServlet" method="post">
            <input type="hidden" name="select" value="add to cart">
            <input type="hidden" name="itemID" value="1">

            Quantity:
            <input type="number" name="qty" value="1" min="1">

            <button type="submit">Add to Cart</button>
        </form>
        
        <form action="ShoppingServlet" method="post">
            <input type="hidden" name="select" value="check out">

            <button type="submit">Check Out</button>
        </form>
    </body>
</html>
