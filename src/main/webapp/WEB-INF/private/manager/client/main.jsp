<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}">
      <h2 style="margin-left: 20px;"><spring:message code="clientForm.title"/></h2>

      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">

      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p>
          <button type="submit" id="cancel" name="_eventId_cancel"><spring:message code="button.flow.cancel"/></button>
          <button type="submit" id="client_create" name="_eventId_client_create"><spring:message code="button.flow.client_create"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>