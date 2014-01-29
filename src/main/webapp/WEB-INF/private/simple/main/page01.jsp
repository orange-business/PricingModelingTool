<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <h4>Создан тестовый опотьюнити с параметрами:</h4>
    flushModeValue: <c:out value="${flushModeValueOld}"/><br/>
    flushModeValue: <c:out value="${flushModeValueNew}"/><br/>
    Клиент: <c:out value="${customer}"/><br/>

    <b>Детали опотьюнити:</b> <br/>
    Срок контракта: <c:out value="${scenario.contractTerm}"/> месяцев.<br/>
    Номер оппотьюнити: <c:out value="${opportunity.externalId}"/><br/>
    Комментарий к оппотьюнити: <c:out value="${opportunity.note}"/><br/><br/>
    <b>Детали версии:</b> <br/>
    Клиент: <c:out value="${customer.official}"/><br/>
    Email владельца этой сборки: <c:out value="${scenario.getOwnerEmail()}"/><br/>
    Комментарий к сборке: <c:out value="${scenario.getNote()}"/><br/>
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p><button type="submit" id="next" name="_eventId_next">Next</button></p>
      </div>
    </form:form>
  </div>
</div>