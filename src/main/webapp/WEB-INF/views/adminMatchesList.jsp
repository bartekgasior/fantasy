<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Lista spotkań</title>
		
		<script type="text/javascript">
		
		function redirect(){
			    var realTeam = document.getElementById("getHomeTeamName");
			    var realTeamName = realTeam.options[realTeam.selectedIndex].value;
			    var position = document.getElementById("getAwayTeamName");
			    var positionName = position.options[position.selectedIndex].value;
			    
			    var url = "/admin/matches/1/filter/ByCriteria;";
			    if(realTeam.selectedIndex != 0){
			    	url = url + "homeTeam=" + realTeamName + ";";
			    }
			    
			    if(position.selectedIndex != 0){
			    	url = url + "awayTeam=" + positionName + ";";
			    }
			    
			    window.location = url;
		}
		
		</script>
		
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
			
				<div class="panel-heading" style="font-size:17px;">
				
				<form method="post">
				
					<select name="realTeamName" id="getHomeTeamName">  
						<option selected disabled hidden>Wybierz drużynę gospodarzy</option>
					    <c:forEach items="${realTeamsList}" var="realTeam">
					        <option id="${realTeam.id}" value="${realTeam.name }">${realTeam.name }</option>   
					    </c:forEach>
					</select>
				
					<select name="positionName" id="getAwayTeamName">  
						<option selected disabled hidden>Wybierz drużynę gości</option>
					    <c:forEach items="${realTeamsList}" var="realTeam">
					        <option id="${realTeam.id}" value="${realTeam.name }">${realTeam.name }</option>   
					    </c:forEach>
					</select>

					<button type="button" id="searchButton" class="btn btn-info btn-sm" onclick="redirect()"> <span class = "glyphicon glyphicon-trash"></span> Szukaj</button>
					
					<a href="<spring:url value="/admin/matches/addMatch" />" class="btn btn-success btn-sm pull-right"> <span class = "glyphicon glyphicon-plus"> </span> Dodaj spotkanie </a>
					
				</form>
				</div>
				
				<div class="panel-body">
					<div class = "table-responsive">
					<table class = "table table-striped table-striped table-hover" style="text-allign:center;">
						<thead>
					      <tr>
					        <th>#</th>
					        <th>Home team</th>
					        <th>Away team</th>
					        <th>Result</th>
					        <th></th>
					        <th></th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${matchesList }" var="match">
					      <tr>
					        <td>${match.id}</td>
					        <td>
					        	<c:forEach items="${realTeamsList }" var="realTeam">
					        		<c:choose>
					        			<c:when test="${match.home_team_id == realTeam.id}">
					        			
					        				${realTeam.name }
					        			 
					        			</c:when>
					        		</c:choose>
					        	</c:forEach>
					        </td>
					        <td>
					        	<c:forEach items="${realTeamsList }" var="realTeam">
					        		<c:choose>
					        			<c:when test="${match.away_team_id == realTeam.id}">
					        			
					        				${realTeam.name }
					        			 
					        			</c:when>
					        		</c:choose>
					        	</c:forEach>
					        </td>
					        <td>
					        	<c:forEach items="${realTeamsList }" var="realTeam">
					        		<c:choose>
					        			<c:when test="${match.home_team_id == realTeam.id}">
					        			
					        				${match.home_team_score} -
					        			 
					        			</c:when>
					        		</c:choose>
					        	</c:forEach>
					        	<c:forEach items="${realTeamsList }" var="realTeam">
					        		<c:choose>
					        			<c:when test="${match.home_team_id == realTeam.id}">
					        			
					        				${match.away_team_score}
					        			 
					        			</c:when>
					        		</c:choose>
					        	</c:forEach>
					        </td>
					        <td>
					        	<a href="<spring:url value="/admin/matches/${currentPage }/deleteMatch/${match.id }" />" class="btn btn-danger btn-sm"> <span class = "glyphicon glyphicon-trash"> </span> Usuń </a>
					        </td>
					        <td>
					        	<a href="<spring:url value="/admin/editMatch/${match.id}" />" class="btn btn-info btn-sm"> <span class = "glyphicon glyphicon-trash"> </span> Edytuj </a>
					      	</td>
					      </tr>
					     </c:forEach>
					    </tbody>
					</table>
					</div>
				</div>
								
				<div class="panel-footer text-center" style = "font-size:20px;">
					 <c:forEach begin="1" end ="${pages }" varStatus="page">
					 <c:choose>
					 	 <c:when test="${page.index == currentPage}">
					        <a href="${page.index }" style = "color: red; text-decoration: underline;">${page.index }</a>  
					    </c:when>    
					    <c:otherwise>
					        <a href="${page.index }">${page.index }</a>  
					    </c:otherwise>
					 </c:choose>
					 </c:forEach>
				</div>
			</div>
			</div>
		
		</sec:authorize>
		</section>
	</body>
</html>