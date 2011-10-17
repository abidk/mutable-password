<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="tabs" required="true" type="abid.password.springmvc.model.TabPanel" %>
<%@attribute name="baseUrl" required="true" type="java.lang.String" %>

<div class="tab-row">
<ul class="tabs">
  <c:forEach var="tab" items="${tabs.tabItems}"><li class="<c:if test="${tab.active}">active</c:if>"><a href="<c:url value="${baseUrl}/${tab.urlName}" />">${tab.name}</a></li></c:forEach>
</ul>
</div>

<div class="tab-panel">
<div class="tabcontent">
<jsp:include page="tabs/${fn:toLowerCase(tabs.selectedTab)}.jsp" />
</div>
</div>