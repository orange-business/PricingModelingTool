<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h1>Выберите сценарий для редактирования</h1>
<c:forEach var="entry" items="${map}">
  <a href="${entry.key}">${entry.value}</a><br/>
</c:forEach>