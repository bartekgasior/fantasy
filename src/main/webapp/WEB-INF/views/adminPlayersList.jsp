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
		
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
			
			
				<div class="panel-heading" style="font-size:25px;text-align:center;font-weight: bold;">
				<form:select name="realTeamsDropdownList" path="realTeams" id="show">
					<c:forEach items="${realTeams}" var="realTeam">
						<form:option id="${realTeam.getId() }" value="${realTeam.getName()}">
							${realTeam.getName()} 
							<c:set var = "realTeamId" value = "${realTeam.getId() }"/>
						 </form:option>
						
					</c:forEach>
				</form:select>
				</div>
				
				<div class="panel-body" id ="panel-body">
					<c:forEach items="${players}" var="player">
					<c:if test="${player.getReal_team_id() == realTeamId}">
						<div class = "caption" style="font-size:30px">
						
							<li class="list-group-item">
								${player.getName() } ${player.getSurname() }
							</li>
							
						</div>
					</c:if>
					</c:forEach>
				</div>
				
				<div class="panel-footer text-center">
					<a href="players/addPlayer" class="btn btn-danger btn-sm">Dodaj zawodnika</a>
				</div>
			</div>
			</div>
		
			
		</sec:authorize>
		</section>
	</body>
</html>