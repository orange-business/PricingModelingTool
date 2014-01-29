<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ligne">
  <b>Коды денежных начислений</b><br/>
  <c:forEach items="${codes}" var="item">${item.toString()}<br/></c:forEach>
</div>