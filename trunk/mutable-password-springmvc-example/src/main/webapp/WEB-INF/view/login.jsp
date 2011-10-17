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

<h3>Login</h3>
 
<c:if test="${not empty error}">
  <div class="errorblock">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
</c:if>
 
<form name="f" action="<c:url value="j_spring_security_check" />" method="POST">
  <table>
    <tr>
      <td>User:</td>
      <td><input type="text" name="j_username"></td>
    </tr>
    <tr>
      <td>Password:</td>
      <td><input type="password" name="j_password" /></td>
    </tr>
    <tr>
      <td colspan="2">
        <input name="submit" type="submit" value="submit" />
        <input name="reset" type="reset" />
      </td>
    </tr>
  </table>
</form>

	
<div id="heading">
  <h1>Users</h1>
</div>
  
<table>  
  <thead>
    <tr>
      <th>Username</th>
      <th>Password *</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="user" items="${users}">
      <tr>
        <td><span>${user.username}</span></td>
        <td><span>${user.evaluatedPassword}</span></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<div>* Refresh the page to see the password update.</div>

  