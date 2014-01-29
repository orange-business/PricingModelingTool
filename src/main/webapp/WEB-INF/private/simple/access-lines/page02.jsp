<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h2 style="margin-left: 20px;"><spring:message code="configureAccessLines.title"/></h2>
      <h3 style="margin-left: 20px;">Выберите вид линии доступа</h3>
      <form:select path="selectedChannel" items="${form.getAccessLinesChannels()}"
                   itemValue="value" itemLabel="description"

                   style="margin-left: 20px;float: left; width: 360px;"/><br/>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p><button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.next"/></button></p>
      </div>
    </form:form>
  </div>
</div>