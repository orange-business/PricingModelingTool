<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="businessVPN" class="com.orange.ru.domain.product.BusinessVpn" scope="session"/>
<jsp:useBean id="accessLines" class="com.orange.ru.domain.product.AccessLines" scope="session"/>
<%@ page import="java.text.NumberFormat, java.util.Locale" %>
<% pageContext.setAttribute("russian", NumberFormat.getCurrencyInstance(new Locale("ru", "RU"))); %>

<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h4 style="margin-left: 20px;">Просмотр сконфигурированной услуги
        <c:choose>
          <c:when test="${product.getCode() eq businessVPN.getClassCode()}">Business VPN</c:when>
          <c:when test="${product.getCode() eq accessLines.getClassCode()}">Access Lines</c:when>
        </c:choose>
      </h4>

      <c:choose>
        <c:when test="${product.getCode() eq businessVPN.getClassCode()}">
          Срок контракта: <c:out value="${scenario.contractTerm/12}"/> лет.<br/>
          Имя клиента: <c:out value="${customer.toString()}"/>.<br/>
          Клиентский сайт: <c:out value="${product.site.address}"/>.<br/><br/>
          <b>Параметры порта:</b><br/>
          Выбранный город:
          <c:choose>
            <c:when test="${not empty product.town}"><c:out value="${product.town}"/>.</c:when>
            <c:otherwise> выбор города отложен.</c:otherwise>
          </c:choose>
          <br/>
          Схема тарификации: <c:out value="${product.port.tarifficationScheme.description}"/>.<br/>
          Выбранный вид порта: <c:out value="${product.port.coverage.description}"/>.<br/>
          Выбранный тип порта: <c:out value="${product.port.type.description}"/>.<br/>
          Выбранная скорость: <c:out value="${product.port.speed}"/> кб/с.<br/><br/>
          <b>Денежные начисления:</b><br/>
          <c:forEach items="${productItem.treasureList}" var="item">${item.toString()}<br/></c:forEach>
        </c:when>
        <c:when test="${product.getCode() eq accessLines.getClassCode()}">
          Срок контракта: <c:out value="${scenario.contractTerm/12}"/> лет.<br/>
          Имя клиента: <c:out value="${customer.toString()}"/>.<br/>
          Клиентский сайт: <c:out value="${product.site.address}"/>.<br/>
          Вид линии доступа: <c:out value="${product.type.description}"/>.<br/><br/>
          <b>Затраты:</b><br/>
          <c:choose>
            <c:when test="${product.type.code eq 'fiber' }">
              <b>Денежные начисления:</b><br/>
              <c:forEach items="${productItem.treasureList}" var="item">${item.toString()}<br/></c:forEach>
            </c:when>
            <c:when test="${product.type.code eq 'copper' }">
              <b>Денежные начисления:</b><br/>
              <c:forEach items="${productItem.treasureList}" var="item">${item.toString()}<br/></c:forEach>
            </c:when>
            <c:when test="${product.type.code eq 'radio' }">
              <b>Денежные начисления:</b><br/>
              <c:forEach items="${productItem.treasureList}" var="item">${item.toString()}<br/></c:forEach>
            </c:when>
          </c:choose>
        </c:when>
      </c:choose>
      <br/>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
          <button type="submit" id="save_exit" name="_eventId_save_exit">Сохранить и выйти </button>
      </div>
    </form:form>
  </div>
</div>