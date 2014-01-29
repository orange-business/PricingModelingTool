<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">.errors { color: red; font-weight: bold; }</style>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <%--<spring:hasBindErrors name="form">--%>
      <%--<div class="error">--%>
        <%--<spring:bind path="form.*">--%>
          <%--<c:forEach items="${status.errorMessages}" var="error">--%>
            <%--<span><c:out value="${error}"/></span><br>--%>
          <%--</c:forEach>--%>
        <%--</spring:bind>--%>
      <%--</div>--%>
    <%--</spring:hasBindErrors>--%>
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h4 style="margin-left: 20px;"><spring:message code="setCosts.title"/>Строительство Orange: copper communication line</h4>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <br/><div class="span-4" style="width: 600px"><label for="equipmentCostOrange">Введите закупочную стоимость оборудования в рублях без НДС: </label></div>
        <form:input path="equipmentCostOrange" maxlength="40"/>
        <form:errors path="equipmentCostOrange" cssClass="errors"/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <br/><div class="span-4" style="width: 600px"><label for="oneTimePaymentOrange">Введите разовые затраты: </label></div>
        <form:input path="oneTimePaymentOrange" maxlength="40"/>
        <form:errors path="oneTimePaymentOrange" cssClass="errors"/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <br/><div class="span-4" style="width: 600px"><label for="monthlyPaymentOrange">Введите ежемесячные затраты: </label></div>
        <form:input path="monthlyPaymentOrange" maxlength="40"/>
        <form:errors path="monthlyPaymentOrange" cssClass="errors"/>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 50px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.add_cost"/></button></p>
      </div>
    </form:form>
  </div>
</div>