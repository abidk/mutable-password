<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="formUrl" value="/app/create/createSimplePassword" />        
<form:form  autocomplete="off" action="${formUrl}" modelAttribute="simplePasswordForm" method="post">
  <form:errors path="*" cssClass="errorMessages" element="div" /> 
  
  <fieldset>
    <legend>Simple Password</legend>
    <div>Username: <form:input path="username" /></div>
    <div>Password: <form:input path="password" type="password"/></div>
    <div><input type="submit" value="Save User"></input></div>
  </fieldset>
</form:form>


