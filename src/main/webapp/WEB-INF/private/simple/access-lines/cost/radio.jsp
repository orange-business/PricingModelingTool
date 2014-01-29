<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">.errors { color: red; font-weight: bold; }</style>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h4 style="margin-left: 20px;"><spring:message code="setCosts.title"/> RADIO</h4>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="equipmentCostRadio">Введите закупочную стоимость оборудования в рублях без НДС: </label></div>
        <form:input path="equipmentCostRadio" maxlength="40"/><form:errors path="equipmentCostRadio" cssClass="errors"/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="legalizationCostRadio">Введите стоимость легализации: </label></div>
        <form:input path="legalizationCostRadio" maxlength="40"/><form:errors path="legalizationCostRadio" cssClass="errors"/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="mountingCostRadio">Введите стоимость поддержки: </label></div>
        <form:input path="mountingCostRadio" maxlength="40"/><form:errors path="mountingCostRadio" cssClass="errors"/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="monthlyPaymentRadio">Введите ежемесячные затраты: </label></div>
        <form:input path="monthlyPaymentRadio" maxlength="40"/><form:errors path="monthlyPaymentRadio" cssClass="errors"/>
      </div>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <div class="span-4" style="width: 600px"><label for="supportMonthlyPaymentRadio">Введите ежемесячный платеж на поддержку: </label></div>
        <form:input path="supportMonthlyPaymentRadio" maxlength="40"/><form:errors path="supportMonthlyPaymentRadio" cssClass="errors"/>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.add_cost"/></button></p>
      </div>
    </form:form>
  </div>
</div>