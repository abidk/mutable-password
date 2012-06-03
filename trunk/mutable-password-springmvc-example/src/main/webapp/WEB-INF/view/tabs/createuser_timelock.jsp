<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="formUrl" value="/app/create/createTimeLockPassword" />        
<form:form  autocomplete="off" action="${formUrl}" modelAttribute="timeLockPasswordForm" method="post">     
  <form:errors path="*" cssClass="errorMessages" element="div" /> 

  <fieldset>
    <legend>Time Lock Password</legend>
    <div>Username: <form:input path="username" /></div>
    <div>Password Text: <form:input path="password" /></div>
    <div>Time: <form:select path="timeParameter"><form:options /></form:select>       
         Start: <form:input path="start" />
         End: <form:input path="end" />
    </div>
    <div><input type="submit" value="Save User"></input></div>
  </fieldset>
</form:form>