<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h2 style="margin-left: 20px;"><spring:message code="newClientForm.title"/></h2>
      <form:errors path="official" cssClass="error"/>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        Введите официальную информацию о клиенте:<br/>
        <form:label path="official" title="Официальная информация о клиенте"/>
        <form:textarea path="official" cols="10" rows="10"/>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p>
          <button type="submit" id="cancel" name="_eventId_cancel"><spring:message code="button.flow.cancel"/></button>
          <button type="submit" id="new" name="_eventId_save"><spring:message code="button.flow.save"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>