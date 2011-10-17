<%@page trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
  