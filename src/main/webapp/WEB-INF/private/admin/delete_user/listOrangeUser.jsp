<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="selectClientForm">
  <spring:hasBindErrors name="form">
    <div class="error">
      <spring:bind path="form.*">
        <c:forEach items="${status.errorMessages}" var="error">
          <span><c:out value="${error}"/></span><br>
        </c:forEach>
      </spring:bind>
    </div>
  </spring:hasBindErrors>
  <form:form action="${flowExecutionUrl}" modelAttribute="form" acceptCharset="UTF-8">
    <h2 style="margin-left: 20px;"><spring:message code="deleteUser.title"/></h2>
    <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
      <div class="span-4"><label for="email">Поиск по email:</label></div>
      <form:input path="email" maxlength="100"/>
      <br/>
    </div>
    <div id="buttons" style="margin-left: 20px;margin-top: 50px;">
      <p>
        <button type="submit" id="cancel" name="_eventId_cancel"><spring:message code="button.flow.cancel"/></button>
        <button type="submit" id="proceed" name="_eventId_proceed"><spring:message code="button.flow.delete_user"/></button>
      </p>
    </div>
  </form:form>
</div>