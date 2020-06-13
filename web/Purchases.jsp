<%-- 
    Document   : Purchases
    Created on : Apr 1, 2020, 2:12:50 PM
    Author     : n.riley
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Purchases</title>
        <fmt:formatNumber value="${baldue}" type="currency" var="baldue" />
        <fmt:formatNumber value="${actbal}" type="currency" var="actbal" />       
    </head>
    
    <body>
        <h1>Transactions Account for:</h1>
        <h2>${m.memid}, ${m.firstname} ${m.lastname}</h2>
        <br>
        <table border="1">      
            <tr>
                <th>Purchase Date</th>
                <th>Trans Type</th>
                <th>Trans Code</th>
                <th>Trans Desc</th>
                <th>Amount</th>
            </tr>
            <c:forEach var="p" items="${pur}">
                <tr>
                    <td align="right">${p.purchasedtS}</td>
                    <td align="right">${p.transtype}</td>
                    <td align="right">${p.transcd}</td>
                    <td align="right">${p.transdesc}</td>
                    <td align="right">${p.amountS}</td>
                </tr>
            </c:forEach>
        </table>
        <p>${msg}, Balance Due: ${baldue}</p>
        <a href="MemberScreen.jsp">Member Screen</a>
    </body>
</html>
