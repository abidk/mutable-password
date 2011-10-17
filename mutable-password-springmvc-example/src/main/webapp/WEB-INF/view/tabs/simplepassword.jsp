<%@page trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tgs" tagdir="/WEB-INF/tags/"%>

<c:url var="formUrl" value="/app/createUser/createSimplePassword" />        
<form:form  autocomplete="off" action="${formUrl}" modelAttribute="SimplePasswordFormBean" method="post">
  <form:errors path="*" cssClass="error" /> 
  <fieldset>
    <legend>Simple Password</legend>
    <div>Username: <form:input path="username" /></div>
    <div>Password: <form:input path="password" type="password"/></div>
    <div><input type="submit" value="Save User"></input></div>
  </fieldset>
</form:form>


