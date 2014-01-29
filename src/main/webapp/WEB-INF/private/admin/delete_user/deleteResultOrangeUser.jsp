<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="selectClientForm">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form" acceptCharset="UTF-8" >
      <h2 style="margin-left: 20px;"><spring:message code="selectClientForm.title"/></h2>
      <fieldset>
        <legend>Confirm Orange User Details</legend>
        <div class="span-8 last">
          <p>email: <spring:bind path="email">${status.value}</spring:bind></p>
        </div>
        <div class="span-8 last"><p>Пользователь удален.</p></div>
     </fieldset>
       <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
         <p><button type="submit" id="confirm" name="_eventId_confirm"><spring:message code="button.flow.exit"/></button></p>
       </div>
    </form:form>
  </div>
</div>