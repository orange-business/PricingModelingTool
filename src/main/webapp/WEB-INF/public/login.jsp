<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<div class="span-5">
	<p><spring:message code="loginPage.warning.title"/></p>
	<%--<ul>--%>
        <%--<li>evgeniya.kirillova@orange.com<li></li>ROLE_MARKETING</li>--%>
        <%--<li>sergey.bogachek@orange.com<li></li>ROLE_MARKETING, ROLE_ADMIN</li>--%>
        <%--<li>radik.zaynullin@orange.com<li></li>ROLE_MARKETING, ROLE_ADMIN</li>--%>
        <%--<li>nadezhda.minkina@orange.com<li></li>ROLE_MARKETING</li>--%>
        <%--<li>alexander.samoylov@orange.com<li></li>ROLE_MARKETING</li>--%>
	<%--</ul>--%>
</div>
<div class="span-10 append-2 last">
	<c:if test="${not empty param.login_error}">
		<div class="error">
			Your login attempt was not successful, try again.<br /><br />
			Reason: <%= ((AuthenticationException) session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
		</div>
	</c:if>
	<form name="f" action="<c:url value="/public/loginProcess"/>" method="post">
		<fieldset>
			<legend>Login Information</legend>
			<p>
				<label for="j_username">User:</label><br />
				<input type="text" name="j_username" id="j_username" size="60" <c:if test="${not empty param.login_error}">value="<%= session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>"</c:if> />
			</p>
			<p>
				<label for="j_password">Password:</label><br />
				<input type="password" name="j_password" id="j_password"/>
			</p>
			<p>
				<input type="checkbox" name="_spring_security_remember_me" id="remember_me" />
				<label for="remember_me">Don't ask for my password for two weeks:</label>
			</p>
			<p><button id="submit" type="submit">Login</button></p>
		</fieldset>
	</form>
</div>