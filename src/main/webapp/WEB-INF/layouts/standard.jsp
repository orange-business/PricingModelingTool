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

  <%-- отображение некоторых элементов на экране --%>
  <link rel="stylesheet" href="<c:url value="/css/styles/blueprint/screen.css" />" type="text/css" media="screen, projection"/>
  <link rel="stylesheet" href="<c:url value="/css/styles/booking.css"/>" type="text/css" media="screen"/>
</head>
<body>
<div id="contener">
  <div id="liensRef"><a href="http://www.orange-business/index.html#navigation_haute">Navigation haute</a>|<a
      href="http://www.orange-business/index.html#navigation_gauche">Navigation gauche</a>|<a
      href="http://www.orange-business/index.html#contenu">Contenu</a></div>
  <div id="header">
    <div id="logo" style="margin-top: -20px;">
      <a href="http://www.orange-business/index.htm" tppabs="http://www.orange-business.com/ru/content/">
        <img src="/img/navh/Logo-OBS_40pix-Web.gif" alt="orange business services" height="80" width="137" border="0">
      </a>
    </div>
    <div id="toolBar">
      <a href="http://www.orange-business.com/ru/content/about/about/index.html" class="active" id="lastlink">о компании</a>
      <a href="http://www.orange-business.com/ru/content/about/about/clients/lk/index.html" class="active" id="lastlink">клиентам</a>
      <a href="http://www.orange-business.com/ru/content/press-center/press-center/news/index.jsp" class="active"
         id="lastlink">пресс-центр</a>
      <a href="http://www.orange-business.com/en/mnc2/" class="active" id="lastlink">Orange в мире</a>
      <a href="http://www.orange-business.com/ru/content/about/about/mission/index.html" class="active" id="lastlink">контакты</a>
      <security:authorize access="isAuthenticated()"><a href="<c:url value="/public/logout"/>" id="lastlink">Logout</a></security:authorize>
      <security:authorize ifAllGranted="ROLE_ANONYMOUS"><a href="<c:url value="/public/login"/>" id="lastlink">
        Login</a></security:authorize>
      <a href="/?lang=en_US" class="active" id="lastlink"><spring:message code="label_en_US"/></a>
      <a href="/?lang=ru_RU" class="active" id="lastlink"><spring:message code="label_ru_RU"/></a>
    </div>
    <div class="spacer"></div>
    <div id="barreintro">
      <div id="intro">
        <security:authorize access="isAuthenticated()">
          <c:if test="${pageContext.request.userPrincipal != null}">Добро пожаловать ${pageContext.request.userPrincipal.name} | </c:if>
        </security:authorize>
        <security:authorize ifAllGranted="ROLE_ANONYMOUS">Добро пожаловать на Pricing Modeling Tool | </security:authorize>
      </div>
    </div>
    <div id="nav">
      <a name="navigation_haute"></a>
      <ul>
        <li>
          <a onmouseover="swapImage('roll1','','/img/att/1_orange.gif',0);" onmouseout="swapImgRestore();"
             href="http://www.orange-business.com/ru/content/network-services/">
            <img src="/img/att/1_black.gif" alt="сети и доступ" width="93" height="40" name="roll1"  title="сети и доступ" border="0">
          </a>
        </li>
        <li>
          <a onmouseover="swapImage('roll2','','/img/att/telephony-orange.gif',0);" onmouseout="swapImgRestore();"
             href="http://www.orange-business.com/ru/content/telephony/">
            <img src="/img/att/telephony-black.gif" alt="телефония" width="111" height="40" name="roll2" title="телефония" border="0">
          </a>
        </li>
        <li>
          <a onmouseover="swapImage('roll3','','/img/att/hi-end-services-orange.gif',0);" onmouseout="swapImgRestore();"
             href="http://www.orange-business.com/ru/content/hi-end-services/">
            <img src="/img/att/hi-end-services-black.gif" alt="высокотехнологичные услуги" width="180" height="40"
                 name="roll3" title="высокотехнологичные услуги" border="0">
          </a>
        </li>
        <li>
          <a onmouseover="swapImage('roll4','','/img/att/2_orange.gif',0);" onmouseout="swapImgRestore();"
             href="http://www.orange-business.com/ru/content/mobile-work/">
            <img src="/img/att/2_black.gif" alt="услуги для мобильной и совместной работы" width="181" height="40"
                 name="roll4" title="услуги для мобильной и совместной работы" border="0">
          </a>
        </li>
        <li>
          <a onmouseover="swapImage('roll5','','/img/att/professional-services-orange.gif',0);" onmouseout="swapImgRestore();" href="http://www.orange-business.com/ru/content/professional-services/">
            <img src="/img/att/professional-services-black.gif" alt="профессиональные услуги" width="165" height="40" name="roll5" title="профессиональные услуги" border="0">
          </a>
        </li>
        <li>
          <a onmouseover="swapImage('roll6','','/img/att/3_orange.gif',0);" onmouseout="swapImgRestore();" href="http://www.orange-business.com/ru/content/specialized-services/">
            <img src="/img/att/3_black.gif" alt="услуги по отраслям" width="180" height="40" name="roll6" title="услуги по отраслям" border="0">
          </a>
        </li>
      </ul>
    </div>
  </div>
  <div class="spacer"></div>
  <div id="leftNav">
    <a name="navigation_gauche"></a>
    <div class="titlenavg"><a href="http://www.orange-business.com/ru/content/about/about/">о компании</a></div>
    <security:authorize access="hasRole('ROLE_ADMIN')">
      <div class="navglist ">
        <a href="/private/admin/create_user">Создать пользователя</a>
      </div>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_ADMIN')">
      <div class="navglist ">
        <a href="/private/admin/delete_user">Удалить пользователя</a>
      </div>
    </security:authorize>

    <div class="navglist ">
      <a href="/public/spa">Тестирование SPA</a>
    </div>
    <div class="navglist ">
      <a href="/public/spa/test/polymorphism">Тест полиморфизма модели данных</a>
    </div>

    <security:authorize access="hasRole('ROLE_MARKETING')">
      <div class="navglist ">
        <a href="/private/compendium/update">Обновление справочников</a>
      </div>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_MARKETING')">
      <div class="navglist ">
        <a href="/private/face/main">Тестирование интерфейса</a>
      </div>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_MARKETING')">
      <div class="navglist ">
        <a href="/private/manager/client">Работа с клиентами</a>
      </div>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_MARKETING')">
      <div class="navglist ">
        <a href="/private/manager/compendium">Работа со справочниками</a>
      </div>
    </security:authorize>
    <div class="navglist ">
      <a href="/public/static/glossary">Глоссарий</a>
    </div>
    <div class="navglist ">
      <a href="/public/static/code">Коды платежей</a>
    </div>
  </div>
  <div id="infoBulle"></div>
  <div id="center3c">
    <a name="contenu"></a>
    <%--Место для контента--%>
    <tiles:insertAttribute name="body"/>
    <div class="spacer"></div>
  </div>
  <div id="footer">
    <div class="barre"></div>
    <div id="selectfooter1"></div>
    <div id="footerleft">
      <ul>
        <li><a href="http://www.orange-business.com/ru/content/about/about/mission/index.html">
          (с) Orange Business Services 2014. Данный сайт содержит информацию, права на использование которой принадлежат<br/>
          Orange Business Services. Использование размещенных на сайте материалов без согласования с Orange Business Services<br/>
          запрещено, при их использовании ссылка на источник обязательна.<br>
          За дополнительной информацией вы можете обратиться по адресу rus-info@list2.orange.com</a></li>
      </ul>
    </div>
    <div id="selectfooter4"><font class="signature"><a href="http://www.orange.com" target="_blank"> </a></font></div>
  </div>
</div>
</body>
</html>