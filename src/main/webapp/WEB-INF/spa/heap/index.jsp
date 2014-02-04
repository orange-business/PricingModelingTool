<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
       hello world!1111111<br/>
       <%--<c:forEach var="entry" items="${data}">--%>
         <%--Name:  ${entry.key} <br/>--%>
         <%--Value: ${entry.value} <br/>--%>
       <%--</c:forEach>--%>


       <c:forEach var="entry" items="${map}">
         Name:  ${entry.key} <br/>
         Value: ${entry.value} <br/>
       </c:forEach>
</body>
</html>