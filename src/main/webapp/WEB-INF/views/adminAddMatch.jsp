<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Dodaj spotkanie</title>
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
		<section class="container">
			
			<form:form modelAttribute = "match" method="post" class ="form-inline">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<fieldset>
					<legend>Podaj dane</legend>
						
						<div class = "form-group">
							<div class="col-lg-10">
							
								<form:select path="home_team_id">
								   
								    <c:forEach items="${realTeams }" var="realTeam">
								        <form:option id="${realTeam.id }" value="${realTeam.id }" itemValue="home_team_id">${realTeam.name }</form:option>   
								    </c:forEach>
								</form:select>
								
							</div>
						</div>
						
						<div class = "form-group">
							<div class="col-sm-10">
								<form:input id="home_team_score" placeholder = "Bramki gospodarzy" path="home_team_score" type="text" class="form:input-large"/>
							</div>
						</div>
						 :	
						<div class = "form-group">
							<div class="col-sm-10">
								<form:input id="away_team_score" placeholder = "Bramki gości" path="away_team_score" type="text" class="form:input-large"/>
							</div>
						</div>
						
						<div class = "form-group">
							<div class="col-lg-10">
							
								<form:select path="away_team_id">
								   
								    <c:forEach items="${realTeams }" var="realTeam">
								        <form:option id="${realTeam.id }" value="${realTeam.id }" itemValue="away_team_id">${realTeam.name }</form:option>   
								    </c:forEach>
								</form:select>
								
							</div>
						</div>
						
						<div class = "form-group">
							<div class="col-lg-10">
							
								<form:select path="result_id">
								   
								    <c:forEach items="${results }" var="result">
								        <form:option id="${result.id }" value="${result.id }" itemValue="result_id">${result.name }</form:option>   
								    </c:forEach>
								</form:select>
								
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-lg-10">
								<input type="submit" id="btnAdd" class="btn btn-primary" value="Potwierdz"/>
							</div>
						</div>
				</fieldset>
			</form:form>
		</section>
	</body>
</html>