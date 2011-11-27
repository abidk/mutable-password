<%@ include file="includes.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <title>Mutable Password Wicket Example</title>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="<c:url value="/css/layout.css" />" />
</head>

<body>
  <div id="wrapper">
    <div id="container">
      <div id="logo"></div>

      <div id="nav">
	    <div id="button">
		  <div id="buttonlink">
		    <sec:authorize url="/">
		      <a href="<c:url value="/users" />">Users</a>
            </sec:authorize>
          </div>
	    </div>
	    <div id="button">
		  <div id="buttonlink">
		    <sec:authorize url="/">
		      <a href="<c:url value="/create" />">Create User</a>
            </sec:authorize>
          </div>
	    </div>
	    <div id="button">
		  <div id="buttonlink">
		    <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
		      <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
            </sec:authorize>
          </div>
	    </div>
	    <div id="button">
		    <div id="buttonlink"></div>
	    </div>
      </div>	


      <div style="clear:both;height:2px;width:1px;"></div>
      <div id="content">
        <div wicket:id="feedbackPanel">          
          <c:forEach var="message" items="${feedbackMessages}">
            <div class="infoMessages"><spring:message message="${message}" /></div>
          </c:forEach>
        </div>
        <div style="clear:both;height:6px;width:1px;"></div>
        <tiles:insertAttribute name="body" />
      </div>
    </div>

	<div id="footer"></div>
  </div>
</body>
</html>