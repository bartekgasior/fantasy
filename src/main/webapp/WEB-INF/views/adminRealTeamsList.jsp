<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Login</title>
	</head>
	<body>
		<section>
			<div class="containter">
				<c:forEach items="${realTeamNames}" var="realTeamName">
					<div class = "caption">
						<h3>${realTeamName.name}</h3>
					</div>
				</c:forEach>
			</div>
		</section>
	</body>
</html>