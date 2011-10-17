<%--

    Copyright 2011 Abid Khalil

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%@page trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tgs" tagdir="/WEB-INF/tags/"%>

<c:url var="formUrl" value="/app/createUser/createCaesarCipherPassword" />        
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