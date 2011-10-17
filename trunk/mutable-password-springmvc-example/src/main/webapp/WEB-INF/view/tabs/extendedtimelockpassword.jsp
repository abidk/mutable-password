<%@page trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tgs" tagdir="/WEB-INF/tags/"%>

<c:url var="formUrl" value="/app/createUser/createExtendedTimeLockPassword" />        
<form:form  autocomplete="off" action="${formUrl}" modelAttribute="ExtendedTimeLockPasswordFormBean" method="post">
  <form:errors path="*" cssClass="error" /> 
  <fieldset>
    <legend>Extended Time Lock Password</legend>
    <div>Username: <form:input path="username" /></div>
    <div>Password Text: <form:input path="password" /></div>
    <div>Parameter: <form:select path="extendedParameter"><form:options /></form:select></div>
    <div>Time: <form:select path="timeParameter"><form:options /></form:select>
         Start: <form:input path="start" />
         End: <form:input path="end" />
    </div>
    <div><input type="submit" value="Save User"></input></div>
  </fieldset>
</form:form>