<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Dodaj zawodnika</title>
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
			
			<form:form modelAttribute = "player" method="post" class ="form-horizontal">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<fieldset>
					<legend>Podaj dane</legend>
					
						<div class = "form-group">
							<div class="col-lg-10">
								<form:input id="name" placeholder = "Name" path="name" type="text" class="form:input-large"/>
								<form:errors path="name" cssClass="text-danger"></form:errors>
							</div>
						</div>
						
						<div class = "form-group">
							<div class="col-lg-10">
								<form:input id="surname" placeholder = "Surname" path="surname" type="text" class="form:input-large"/>
								<form:errors path="surname" cssClass="text-danger"></form:errors>
							</div>
						</div>
						
						<div class = "form-group">
							<div class="col-lg-10">
								<form:input id="player_fee" placeholder = "Player fee" path="player_fee" type="text" class="form:input-large"/>
								<form:errors path="player_fee" cssClass="text-danger"></form:errors>
							</div>
						</div>
						
						<div class = "form-group">
							<div class="col-lg-10">
							
								<form:select path="position">
								    <c:forEach items="${positions }" var="position">
								        <form:option id="${position.id }" value="${position.name }" itemValue="position">${position.name }</form:option>   
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