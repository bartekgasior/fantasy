<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Rejestracja</title>
	</head>
	<body>
		<section class="container">
			<form:form modelAttribute = "newUser" method="post" class ="form-horizontal">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<fieldset>
					<legend> Podaj dane</legend>
						<div class = "form-group">
							<label class="control-label col-lg-2 col-lg-2" for="name">Login</label>
							<div class="col-lg-10">
								<form:input id="username" placeholder = "username" path="username" type="text" class="form:input-large" />
							</div>
						</div>
						<div class = "form-group">
							<label class="control-label col-lg-2 col-lg-2" for="unitPrice">Hasło</label>
							<div class="col-lg-10">
								<form:input id="password" placeholder="password" path="password" type="text" class="form:input-large" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-lg-2" for="description">Powtórz hasło</label>
							<div class="col-lg-10">
								<form:input id="rePassword" path="rePassword" type="text" class="form:input-large"/>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<input type="submit" id="btnAdd" class="btn btn-primary" value="Potwierdz"/>
							</div>
						</div>
				</fieldset>
			</form:form>
		</section>
	</body>
</html>