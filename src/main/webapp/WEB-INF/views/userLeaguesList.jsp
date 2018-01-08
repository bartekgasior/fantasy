<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Leagues List</title>

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
		
			<div class="panel-heading" style="font-size:30px;text-align:center;font-weight: bold;">Lista twoich lig</div>
			
			<div class="panel-body">
				<div class = "caption" style="font-size:30px">

				<table class = "table table-striped table-striped table-hover" style="text-allign:center;">
				<thead>
				     <tr>
				       <th>Name</th>
				       <th>Starting Money</th>
				       <th>Slots</th>
				       <th></th>
				     </tr>
				   </thead>
				   <tbody>
				   		   
				   <c:forEach items="${leagues }" var="league">
				   
				   <c:forEach items="${leagueUsersTeam }" var="leagueUserTeam">
			      		<c:choose>
			     			<c:when test="${league.id == leagueUserTeam.leagueId}">				        			
					        				
					        	 <tr>
							       <td>${league.name}</td>
							       <td>${league.startingMoney}</td>
							       <td>${league.slots }</td>
							       <td>
							       	<a href="<spring:url value="/userPanel/deleteLeague/${league.id }" />" class="btn btn-danger btn-sm"> <span class = "glyphicon glyphicon-trash"> </span> Usuń </a>
							       </td>
							     </tr>
							 						        			 	
		        			</c:when>
		            	</c:choose>
					</c:forEach>
				   
				    
				    </c:forEach>
				   </tbody>
				</table>
							
				</div>
				
			</div>
			
			<div class="panel-heading" style="font-size:30px;text-align:center;font-weight: bold;">Lista dostępnych lig</div>
			<div class="panel panel-default">
				<div class="panel-body">
				
				<table class = "table table-striped table-striped table-hover" style="text-allign:center;">
				<thead>
				     <tr>
				       <th>Name</th>
				       <th>Starting Money</th>
				       <th>Slots</th>
				       <th></th>
				     </tr>
				   </thead>
				   <tbody>
				   		   
				   <c:forEach items="${availableUserLeagues }" var="l">
			        							        				
					 <tr>
					 <td>${l.name}</td>
					 <td>${l.startingMoney}</td>
					 
					 <c:set var="count" value="0" scope="page" />

						<c:forEach items="${leagues }" var="league">
							<c:if test="${league.id == l.id}">
		        			
   			 					<c:set var="count" value="${count + 1}" scope="page"/>

							</c:if>
						</c:forEach>
						
					 
					 <td>${count} / ${l.slots }</td>
					 <c:if test="${count != l.slots}">
						 <td>
						 	<a href="<spring:url value="leagues/${l.id}/teams/add" />" class="btn btn-success btn-sm"> <span class = "glyphicon glyphicon-plus"> </span> Dołącz </a>
						 </td>
					 </c:if>
					 </tr>
				    
				    </c:forEach>
				   </tbody>
				</table>	
							
				</div>
			</div>
			
			<div class="panel-footer">
				<a href="<spring:url value="league/add" />" class="btn btn-danger btn-md" style="margin-left:5px"> <span class = "glyphicon glyphicon-plus"> </span> Stwórz nową lige</a>
			</div>
		</div>
		</div>
		</sec:authorize>
		</section>
	</body>
</html>