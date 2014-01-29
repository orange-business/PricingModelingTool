<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h2 style="margin-left: 20px;"><spring:message code="configureAccessLines.title"/></h2>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <c:choose>
          <c:when test="${empty selectableSites}">У этого клиента сайтов нет.</c:when>
          <c:otherwise>
            <b>Выберите сайт</b><br/>
            <form:select items="${selectableSites}" path="selectedSite"
                         itemValue="id" itemLabel="address" title="Выберите сайт"/>
          </c:otherwise>
        </c:choose>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 20px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.next"/></button></p>
      </div>
    </form:form>
  </div>
</div>