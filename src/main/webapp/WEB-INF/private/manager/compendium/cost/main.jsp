<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<spring:url value="/js/custom/practice_jqGrid.js" var="practice_url" />
<script type="text/javascript" src="${practice_url}"><jsp:text/></script>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}">
      <h2 style="margin-left: 20px;"><spring:message code="manHourForm.title"/></h2>
      <table id="grid"></table>
      <div id="pager"></div>
      <br />
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p>
          <button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.next"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>
