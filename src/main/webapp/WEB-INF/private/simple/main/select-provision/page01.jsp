<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h2 style="margin-left: 20px;"><spring:message code="chooseProvisionForm.title"/></h2>
      Клиент: <c:out value="${customer}"/>.<br/>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <form:select path="selectedProductName" items="${selectableProductNames}" style="float: left; width: 300px;"/>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 50px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.provision.configure"/></button></p>
      </div>
    </form:form>
  </div>
</div>