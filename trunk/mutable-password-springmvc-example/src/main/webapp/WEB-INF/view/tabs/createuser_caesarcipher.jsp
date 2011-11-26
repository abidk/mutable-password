<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="formUrl" value="/app/create/createCaesarCipherPassword" />        
<form:form  autocomplete="off" action="${formUrl}" modelAttribute="CaesarCipherPasswordFormBean" method="post">
  <form:errors path="*" cssClass="error" /> 

  <fieldset>
    <legend>Caesar Cipher Password</legend>
    <div>Username: <form:input path="username" /></div>
    <div>Password Text: <form:input path="password" /></div>
    <div>Parameter: <form:select path="timeParameter"><form:options /></form:select></div>
    <div><input type="submit" value="Save User"></input></div>
  </fieldset>
</form:form>