<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="selectClientForm">
  <div class="span-12 last" style="overflow: hidden; width: 800px">
    <spring:hasBindErrors name="form">
      <div class="error">
        <spring:bind path="form.*">
          <c:forEach items="${status.errorMessages}" var="error">
            <span><c:out value="${error}"/></span><br>
          </c:forEach>
        </spring:bind>
      </div>
    </spring:hasBindErrors>
    <form:form action="${flowExecutionUrl}" modelAttribute="form" acceptCharset="UTF-8">
      <h2 style="margin-left: 20px;"><spring:message code="createUser.title"/></h2>
      <div id="define-client" style="margin-left: 20px;margin-top: 20px;">
        <br/><div class="span-4"><label for="email">email:</label></div>
        <form:input path="email" maxlength="40"/><br/><br/>
        <div class="span-4"><label for="firstName">firstName:</label></div>
        <form:input path="firstName" maxlength="40"/><br/><br/>
        <div class="span-4"><label for="lastName">lastName:</label></div>
        <form:input path="lastName" maxlength="40"/><br/><br/>
        <div class="span-4"><label for="password">password:</label></div>
        <form:input path="password" maxlength="40"/><br/>
        <div>
          <div class="label span-4">authorities:</div>
          <div id="authorities" class="span-7 last">
            <form:checkboxes path="selectedGroups" items="${selectableGroups}" element="span class='checkbox'" delimiter="<br/>" />
             <script type="text/javascript">
              dojo.query("#authorities input[type='checkbox']").forEach(function(element){
                Spring.addDecoration(new Spring.ElementDecoration({
                  elementId: element.id,
                  widgetType : "dijit.form.CheckBox",
                  widgetAttrs : { checked : element.checked }
                }));
              });
            </script>
          </div>
        </div>
      </div>
      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p>
          <button type="submit" id="cancel" name="_eventId_cancel"><spring:message code="button.flow.cancel"/></button>
          <button type="submit" id="proceed" name="_eventId_proceed"><spring:message code="button.flow.create_user"/></button>
        </p>
      </div>
    </form:form>
  </div>
</div>