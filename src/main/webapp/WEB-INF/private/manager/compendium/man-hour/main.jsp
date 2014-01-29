<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}">
      <h2 style="margin-left: 20px;"><spring:message code="manHourForm.title"/></h2>
      <h3>Order overview:</h3>
      <table id="ordersTable" style="width: 100%;" rules="groups">
        <thead>
        <tr>
          <th align="left"><spring:message code="label.page.cost"/></th>
          <th align="left"><spring:message code="label.page.value"/></th>
          <th align="left"><spring:message code="label.page.detail"/></th>
        </tr>
        </thead>
        <tbody>
        <tr height="10px"/>
        <c:forEach items="${orders}" var="order" varStatus="status">
          <tr>
            <td>${order.cost}</td>
            <td>${order.value}</td>
            <td>
              <form:form id="orderDetailForm_${status.index}" action="${flowExecutionUrl}">
                <input type="hidden" name="index" value="${status.index}"/>

                <a name="_eventId_showOrderDetail" id="orderDetailLink_${status.index}" href="#">View</a>
              </form:form>
            </td>
          </tr>
        </c:forEach>
        <tr height="20px"/>
        </tbody>
      </table>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p>
          <button type="submit" id="next" name="_eventId_next"><spring:message code="button.flow.next"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>
<script>
  dojo.addOnLoad(function () {
    dojo.query("[id^='orderDetailLink_']").forEach(function (element) {
      var index = element.id.substr(element.id.lastIndexOf('_') + 1, element.id.length);
       Spring.addDecoration(new Spring.AjaxEventDecoration({
        elementId: element.id,
        event: "onclick",
        formId: "orderDetailForm_" + index
      }));
    });
  });
</script>