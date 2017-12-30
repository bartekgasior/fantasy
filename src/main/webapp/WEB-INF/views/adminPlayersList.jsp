<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Lista zawodników</title>
		
		<script type="text/javascript">
		
		function redirect(){
			    var realTeam = document.getElementById("getRealTeamName");
			    var realTeamName = realTeam.options[realTeam.selectedIndex].value;
			    var position = document.getElementById("getPositionName");
			    var positionName = position.options[position.selectedIndex].value;
			    
			    var url = "/admin/players/1/filter/ByCriteria;";
			    if(realTeam.selectedIndex != 0){
			    	url = url + "realTeam=" + realTeamName + ";";
			    }
			    
			    if(position.selectedIndex != 0){
			    	url = url + "position=" + positionName + ";";
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
				
					<select name="realTeamName" id="getRealTeamName">  
						<option selected disabled hidden>Wybierz drużynę</option>
					    <c:forEach items="${realTeamsList}" var="realTeam">
					        <option id="${realTeam.id}" value="${realTeam.name }">${realTeam.name }</option>   
					    </c:forEach>
					</select>
				
					<select name="positionName" id="getPositionName">  
						<option selected disabled hidden>Wybierz pozycje</option>
					    <c:forEach items="${positions }" var="position">
					        <option id="${position.id }" value="${position.name }">${position.name }</option>   
					    </c:forEach>
					</select>

					<button type="button" id="searchButton" class="btn btn-info btn-sm" onclick="redirect()"> <span class = "glyphicon glyphicon-trash"></span> Szukaj</button>
				</form>
				</div>
				
				<div class="panel-body">
					<div class = "table-responsive">
					<table class = "table table-striped table-striped table-hover" style="text-allign:center;">
						<thead>
					      <tr>
					        <th>#</th>
					        <th>Name</th>
					        <th>Surname</th>
					        <th>Fee</th>
					        <th>Position</th>
					        <th>Club</th>
					        <th></th>
					        <th></th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${playersList }" var="player">
					      <tr>
					        <td>${player.id}</td>
					        <td>${player.name}</td>
					        <td>${player.surname}</td>
					        <td>${player.player_fee}</td>
					        <td>${player.position}</td>
					        <td>
					        	<c:forEach items="${realTeamsList }" var="realTeam">
					        		<c:choose>
					        			<c:when test="${player.real_team_id == realTeam.id}">
					        			
					        				${realTeam.name }
					        			 
					        			</c:when>
					        		</c:choose>
					        	</c:forEach>
					        </td>
					        <td>
					        	<a href="<spring:url value="/admin/players/${currentPage }/deletePlayer/${player.id }" />" class="btn btn-danger btn-sm"> <span class = "glyphicon glyphicon-trash"> </span> Usuń </a>
					        </td>
					        <td>
					        	<a href="<spring:url value="/admin/players/edit?id=${player.id}" />" class="btn btn-success btn-sm"> <span class = "glyphicon glyphicon-edit"> </span> Edytuj </a>
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