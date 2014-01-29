<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h2 style="margin-left: 20px;"><spring:message code="selectSite.title"/></h2>
      Клиент: <c:out value="${customer}"/>.<br/>
      <c:choose>
        <c:when test="${empty selectableSites}">У этого клиента сайтов нет.</c:when>
        <c:otherwise>
          <form:select items="${selectableSites}" path="selectedSite"
                       itemValue="id" itemLabel="address" title="Выберите сайт"/>
        </c:otherwise>
      </c:choose>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.add_provision"/></button></p>
      </div>
    </form:form>
  </div>
</div>