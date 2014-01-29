<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h3 style="margin-left: 20px;"><spring:message code="viewPreconfiguredPortForm.title"/></h3>
      Схема тарификации: <c:out value="${port.tarifficationScheme}"/>.<br/>
      Вид порта: <c:out value="${port.coverage}"/>.<br/>
      Тип порта: <c:out value="${port.type}"/>.<br/>
      Выбранная скорость: <c:out value="${port.speed}"/> кб/с.<br/>
      <div id="buttons" style="margin-left: 20px;margin-top: 50px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.next"/></button></p>
      </div>
    </form:form>
  </div>
</div>