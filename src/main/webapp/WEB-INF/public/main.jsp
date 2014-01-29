<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style type="text/css">
  div.confirmed {
    border: solid 1px #CCCCCC;
    background: #E4F7CD;
    padding: 4px;
    font-weight: bold;
    margin-bottom: 4px;
  }
</style>
<div class="ligne">
  <h3><spring:message code="page.category"/></h3>
  <br />

  <%--<div class="confirmed"><c:out value="${confirmed}"/></div>--%>
</div>