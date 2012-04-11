<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div>          
  <c:forEach var="message" items="${feedbackMessages}">
    <div class="infoMessages"><spring:message message="${message}" /></div>
  </c:forEach>
</div>