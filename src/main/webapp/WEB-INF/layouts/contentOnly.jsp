<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title><spring:message code="page.category"/></title>
  <meta name="author" content="Orange Business Services">

  <%-- отображение некоторых элементов на экране --%>
  <link rel="stylesheet" href="<c:url value="/css/styles/blueprint/screen.css" />" type="text/css" media="screen, projection"/>
  <link rel="stylesheet" href="<c:url value="/css/styles/booking.css"/>" type="text/css" media="screen"/>

  <link rel="stylesheet" href="<c:url value="/css/orange/default.css" />" type="text/css" media="screen"/>
  <link rel="stylesheet" href="<c:url value="/css/orange/layout.css" />" type="text/css" media="screen"/>
  <link rel="stylesheet" href="<c:url value="/css/orange/content.css" />" type="text/css" media="screen"/>
  <link rel="stylesheet" href="<c:url value="/css/orange/typo.css" />" type="text/css" media="screen"/>
  <link rel="stylesheet" href="<c:url value="/css/orange/navg.css" />" type="text/css" media="screen"/>
  <link rel="stylesheet" href="<c:url value="/css/orange/navh.css" />" type="text/css" media="screen"/>

  <%-- DHTMLX --%>
  <link rel="stylesheet" href="<c:url value="/js/lib/dhtmlx/dhtmlx.css"/>" type="text/css" media="screen"/>
  <spring:url value="/js/lib/dhtmlx/dhtmlx.js" var="dhtmlx_url"/>
  <script type="text/javascript" src="${dhtmlx_url}"><jsp:text/></script>

  <%-- dhtmlxlayout_pattern5u --%>
  <spring:url value="/js/lib/dhtmlx/patterns/dhtmlxlayout_pattern5u.js" var="dhtmlxlayout_pattern5u_url"/>
  <script type="text/javascript" src="${dhtmlxlayout_pattern5u_url}"><jsp:text/></script>
</head>
<body>
<div id="contener">
  <tiles:insertAttribute name="body"/>
</div>
</body>
</html>