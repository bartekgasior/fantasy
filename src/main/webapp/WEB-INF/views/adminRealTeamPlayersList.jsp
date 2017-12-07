<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Lista zawodników</title>
		
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
			<div class="panel panel-default">
			
			
				<div class="panel-heading" style="font-size:25px;text-align:center;font-weight: bold;"> ${realTeam.name }</div>
				
				<div class="panel-body">
					<div class = "table-responsive">
					<table class = "table table-bordered table-striped table-hover">
						<thead>
					      <tr>
					        <th>#</th>
					        <th>Name</th>
					        <th>Surname</th>
					        <th>Fee</th>
					        <th>Position</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${realTeamPlayers}" var="player">
					      <tr>
					        <td>${player.id}</td>
					        <td>${player.name}</td>
					        <td>${player.surname}</td>
					        <td>${player.player_fee}</td>
					        <td>${player.position}</td>
					      </tr>
					     </c:forEach>
					    </tbody>
					</table>
					</div>
				</div>
								
				<div class="panel-footer text-center">
					<a href="addPlayer" class="btn btn-danger btn-sm">Dodaj zawodnika</a>
				</div>
			</div>
			</div>
		
		</sec:authorize>
		</section>
	</body>
</html>