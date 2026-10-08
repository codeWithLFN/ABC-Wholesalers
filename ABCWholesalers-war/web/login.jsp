<%-- 
    Document   : login.jsp
    Created on : 07 Oct 2026, 20:01:22
    Author     : CodeWithLufuno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="CustomerServlet" method="post">
            <input type="hidden" name="select" value="login">
            
            <label>Email:</label>
            <input type="email" name="email" required>
            
            <label>Password:</label>
            <input type="password" name="password" required>
            
            <button type="submit">Login</button>
        </form>
    </body>
</html>
