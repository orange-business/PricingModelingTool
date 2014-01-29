<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">.errors { color: red; font-weight: bold; }</style>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h4 style="margin-left: 20px;"><spring:message code="setCosts.title"/>Строительство Orange: fiber-optic communication line</h4>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="equipmentCostOrange">Введите закупочную стоимость оборудования в рублях без НДС: </label></div>
        <form:input path="equipmentCostOrange" maxlength="40"/><form:errors path="equipmentCostOrange" cssClass="errors"/><br/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="oneTimePaymentOrange">Введите разовые затраты: </label></div>
        <form:input path="oneTimePaymentOrange" maxlength="40"/><form:errors path="oneTimePaymentOrange" cssClass="errors"/><br/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="monthlyPaymentOrange">Введите ежемесячные затраты: </label></div>
        <form:input path="monthlyPaymentOrange" maxlength="40"/><form:errors path="monthlyPaymentOrange" cssClass="errors"/>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 20px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.add_cost"/></button></p>
      </div>
    </form:form>
  </div>
</div>