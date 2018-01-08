<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Teams List</title>
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
		<sec:authorize access="hasRole('ROLE_USER')">
		
		<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
		
			<div class="panel-heading" style="font-size:30px;text-align:center;font-weight: bold;">Lista drużyn</div>
			
			<div class="panel-body">
				<div class = "caption" style="font-size:30px">

				<table class = "table table-striped table-striped table-hover" style="text-allign:center;">
				<thead>
				     <tr>
				       <th>#</th>
				       <th>Name</th>
				       <th>Score</th>
				       <th></th>
				       <th></th>
				     </tr>
				   </thead>
				   <tbody>
				   <c:forEach items="${teams }" var="team">
				     <tr>
				       <td>${team.id}</td>
				       <td>${team.name}</td>
				       <td>${team.score}</td>
				       <td>
				       		<c:forEach items="${leagueUsersTeam }" var="leagueUserTeam">
					        		<c:if test="${team.id == leagueUserTeam.usersTeamId }">
										<c:if test ="${leagueUserTeam.usersTeamUserId == userId }">
											<c:choose>
												<c:when test="${team.teamSelected == 0 }">
													<a href="<spring:url value="/userPanel/teams/edit?id=${leagueUserTeam.usersTeamId }" />" class="btn btn-success btn-sm"> <span class = "glyphicon glyphicon-edit"> </span> Zarządzaj składem </a>
												</c:when>
												<c:otherwise>
													Drużyna zbudowana
												</c:otherwise>						
											</c:choose>			
					        			</c:if>
					        		</c:if>
					        </c:forEach>
				       </td>
				       <td>
				       		<c:forEach items="${leagueUsersTeam }" var="leagueUserTeam">
					        		<c:if test="${team.id == leagueUserTeam.usersTeamId }">
										<c:if test ="${leagueUserTeam.usersTeamUserId == userId }">
											<a href="<spring:url value="/userPanel/league?id=${leagueUserTeam.leagueId }" />" class="btn btn-success btn-sm"> Informacje o lidze </a>
					        			</c:if>
					        		</c:if>
					        </c:forEach>
				       		
				       </td>
				     </tr>
				    </c:forEach>
				   </tbody>
				</table>
						
				</div>
			</div>
		</div>
		</div>
		</sec:authorize>
		</section>
	</body>
</html>