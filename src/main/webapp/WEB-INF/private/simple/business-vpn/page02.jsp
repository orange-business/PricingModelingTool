<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:errors path="selectedLocationTown" cssClass="error"/>
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h2 style="margin-left: 20px;">Сконфигурируйте услугу Business VPN - Город</h2>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <h3 style="margin-left: 20px;">Выберите город</h3>
        <form:select path="town" items="${selectableTowns}" style="float: left; width: 300px;" />
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 40px;">
        <p>
          <button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.next"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>