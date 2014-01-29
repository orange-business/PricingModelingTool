<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--begin заполняем js переменную выбранными сайтами --%>
<spring:url value="/js/custom/jquery.jec-1.3.4.js" var="jec_url" />
<script type="text/javascript" src="${jec_url}"><jsp:text/></script>
<script type="text/javascript">
  var jq = jQuery.noConflict();
  jq(function() { jq('#editable_select').jec(); });
</script>
<div id="calculations-order">
  <div class="span-12 last" style="overflow: hidden;">
    <form:form action="${flowExecutionUrl}" modelAttribute="form">
      <h3 style="margin-left: 20px;">Конфигурирование порта</h3>

      <h3 style="margin-left: 20px;">Выберите вид порта</h3>
      <form:select path="selectedCoverage" items="${form.getCoverageValues()}" style="float: left; width: 120px;"
           itemValue="value" itemLabel="description"  />
      <br/><br/>

      <h3 style="margin-left: 20px;">Выберите тип порта</h3>
      <form:select path="selectedType" items="${form.getTypeValues()}" style="float: left; width: 120px;"
                   itemValue="value" itemLabel="description"/>
      <br/><br/>

      <h3 style="margin-left: 20px;">Выберите скорость</h3>
      <form:select id="editable_select" path="selectedSpeed" items="${speedValues}" style="float: left; width: 120px;"/>
      <br/><br/>

      <h3 style="margin-left: 20px;">Выберите схему тарификации порта</h3>
      <form:select id="editable_select" path="selectedTarifficationPlan" items="${form.getTarifficationPlans()}"
                   style="float: left; width: 120px;" itemValue="value" itemLabel="description"/>
      <br/><br/>

      <div id="buttons" style="margin-left: 20px;margin-top: 10px;">
        <p><button type="submit" id="next" name="_eventId_next">Next</button></p>
      </div>
    </form:form>
  </div>
</div>