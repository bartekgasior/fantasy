<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Lista drużyn</title>
	</head>
	<body>
		<section>
			<div class="jumbotron">
				<c:url var="logoutUrl" value="/j_spring_security_logout"/> 
					<form action="${logoutUrl}" method="post"> 
						<button type="submit" class="btn btn-danger btn-mini pull-right">Wyloguj się</button> 
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
			</div>
		</section>
		<section>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-default" style="vertical-align: middle;">
		
				<div class="panel-heading" style="font-size:30px;text-align:center;font-weight: bold;">Lista drużyn</div>
				
				<div class="panel-body">
				<ul class="list-group">
					<c:forEach items="${realTeams}" var="realTeam">
						<div class = "caption" style="font-size:30px">

							<li class="list-group-item">
								${realTeam.name}
								<a href="<spring:url value="/admin/deleteRealTeam/${realTeam.id}" />" class="btn btn-danger btn-md pull-right" style="margin-left:5px"> <span class = "glyphicon glyphicon-trash"> </span> Usuń </a>
								<a href="<spring:url value="/admin/realTeam/edit?id=${realTeam.id}" />" class="btn btn-success btn-md pull-right" style="margin-left:5px"> <span class = "glyphicon glyphicon-edit"></span> Edytuj </a>
								<a href="<spring:url value="/admin/realTeam/${realTeam.id }/players" />" class="btn btn-info btn-md pull-right"> <span class = "glyphicon glyphicon-plus-sign"> </span> Zarządzaj zawodnikami </a>								
							</li>
							
						</div>
					</c:forEach>
				
				</ul>
				</div>
				
				<div class="panel-footer text-center">
					<a href="realTeams/addRealTeam" class="btn btn-danger btn-sm">Dodaj drużynę</a>
				</div>
			</div>
			</div>
		</sec:authorize>
		</section>
	</body>
</html>