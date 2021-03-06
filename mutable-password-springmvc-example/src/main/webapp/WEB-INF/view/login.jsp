<%@ include file="includes.jsp" %>

<h3>Login</h3>
 
<c:if test="${error}">
  <div class="errorMessages">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
</c:if>
 
<form name="f" action="<c:url value="/app/j_spring_security_check" />" method="POST">
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
        <input name="submit" type="submit" value="Submit" />
      </td>
    </tr>
  </table>
</form>

	
<h3>Users</h3>

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

  