<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="selectClientForm">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form" acceptCharset="UTF-8">
      <h2 style="margin-left: 20px;"><spring:message code="selectClientForm.title"/></h2>
      <fieldset>
        <legend>Confirm Orange User Details</legend>
        <div class="span-8 last"><p>email: <spring:bind path="email">${status.value}</spring:bind></p></div>
        <div class="span-8 last"><p>firstName: <spring:bind path="firstName">${status.value}</spring:bind></p></div>
        <div class="span-8 last"><p>lastName: <spring:bind path="lastName">${status.value}</spring:bind></p></div>
        <div class="span-8 last"><p>password: <spring:bind path="password">${status.value}</spring:bind></p></div>
        <div class="span-8 last"><p>Groups: <spring:bind path="selectedGroups">${status.value}</spring:bind></p></div>
      </fieldset>
      <div id="buttons" style="margin-left: 20px;margin-top: 50px;">
        <p>
          <button type="submit" id="confirm" name="_eventId_confirm"><spring:message code="button.flow.persist_user"/></button>
          <button type="submit" id="cancel" name="_eventId_cancel"><spring:message code="button.flow.cancel"/></button>
          <button type="submit" id="revise" name="_eventId_revise"><spring:message code="button.flow.revise"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>