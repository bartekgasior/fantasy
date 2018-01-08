<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Rejestracja</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
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
								<form:input id="username" placeholder = "Username" path="username" type="text" class="form:input-large" />
								<form:errors path="username" cssClass="text-danger"></form:errors>
							</div>
						</div>
						<div class = "form-group">
							<label class="control-label col-lg-2 col-lg-2" for="unitPrice">Hasło</label>
							<div class="col-lg-10">
								<form:input id="password" placeholder="Password" path="password" type="password" class="form:input-large" />
								<form:errors path="password" cssClass="text-danger"></form:errors>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-lg-2" for="description">Powtórz hasło</label>
							<div class="col-lg-10">
								<input id="rePassword" placeholder="Confirm Password" type="password" class="form:input-large"/>
								<span id="message"></span>
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
		<script type="text/javascript">		
		$('#password, #rePassword').on('keyup', function () {
		    if ($('#password').val() == $('#rePassword').val()) {
		    	$('#message').html(' ').css('color', 'green');
		    	$('#btnAdd').prop('disabled', false);
		    } else {
		        $('#message').html('Hasła nie są identyczne').css('color', 'red');
		        $('#btnAdd').prop('disabled', true);
		    }
		});
		</script>
	</body>
</html>