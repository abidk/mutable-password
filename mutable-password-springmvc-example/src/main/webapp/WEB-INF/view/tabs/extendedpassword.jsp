<%@page trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tgs" tagdir="/WEB-INF/tags/"%>


<c:url var="formUrl" value="/app/createUser/createExtendedPassword" />        
<form:form  autocomplete="off" action="${formUrl}" modelAttribute="ExtendedPasswordFormBean" method="post">
  <form:errors path="*" cssClass="error" /> 
  <fieldset>
    <legend>Extended Password</legend>
    <div><strong>e.g. Set the extended field to seconds, save the user and check the results in the Login page.</strong></div>
    <div>Username: <form:input path="username" /></div>
    <div>Password Text: <form:input path="password" /></div>
    <div>Parameter: <form:select path="timeParameter"><form:options /></form:select></div>
    <div><input type="submit" value="Save User"></input></div>
  </fieldset>
</form:form>