<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}">
      <h2 style="margin-left: 20px;"><spring:message code="newClientForm.title"/></h2>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <c:if test="${not empty client}">
          Создан клиент:<br/>
          Номер: <c:out value="${client.id}"/><br/>
          Информация: <c:out value="${client.official}"/><br/>
        </c:if>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 50px;">
        <p>
          <button type="submit" id="cancel" name="_eventId_cancel"><spring:message code="button.flow.cancel"/></button>
          <button type="submit" id="client_return" name="_eventId_return"><spring:message code="button.flow.return"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>