<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Dodaj spotkanie</title>	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
							
								<form:select path="home_team_id" id ="getHomeRealTeamId" onchange="showHomeTeamPlayers()">
								    <option selected disabled hidden>Wybierz drużynę gospodarzy</option>
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
							
								<form:select path="away_team_id" id ="getAwayRealTeamId" onchange="showAwayTeamPlayers()">
								    <option selected disabled hidden>Wybierz drużynę gosci</option>
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
								<input type="submit" id="btnAdd" class="btn btn-primary" value="Potwierdz" onclick="sendData()"/>
							</div>
						</div>
				</fieldset>
			</form:form>
			<div class = "table-responsive" id="homePlayersTable" style="width:48%; display:inline-block;"></div>
			<div class = "table-responsive" id="awayPlayersTable" style="width:48%; display:inline-block;"></div>
		</section>
		
		
		<script type="text/javascript" >				
		
		var homeTeamPlayersResultId = [];
		var homeTeamPlayersResultValue = [];
		var awayTeamPlayersResultId = [];
		var awayTeamPlayersResultValue = [];
		
		function showHomeTeamPlayers(){
			var players = JSON.parse('${players}');
			var playerResults = JSON.parse('${playerResults}');
			
		    var tableDiv = document.getElementById("homePlayersTable");
		    tableDiv.innerHTML = "";
		    
		    var homeTeam = document.getElementById("getHomeRealTeamId");
		    var homeTeamId = homeTeam.options[homeTeam.selectedIndex].id; 
		    
		    var colPlayerResults = [];
	        for (var i = 0; i < playerResults.length; i++) {
	            for (var key in playerResults[i]) {
	                if (colPlayerResults.indexOf(key) === -1) {
	                    colPlayerResults.push(key);
	                }
	            }
	        }
		
		    var col = [];
	        for (var i = 0; i < players.length; i++) {
	            for (var key in players[i]) {
	                if (col.indexOf(key) === -1) {
	                    col.push(key);
	                }
	            }
	        }
	        var table = document.createElement("table");
	        table.className = "table table-hover";
	        var tr = table.insertRow(-1);
	        
	        for (var i = 1; i < col.length-1; i++) {
	            var th = document.createElement("th");
	            th.innerHTML = col[i];
	            tr.appendChild(th);
	            if(i==2) i++;
	        }
	        
	        for (var i = 0; i < players.length; i++) {

	            tr = table.insertRow(-1);

	            if(players[i][col[5]] == homeTeamId){
		            for (var j = 1; j < col.length-1; j++) {
		                var tabCell = tr.insertCell(-1);
		                tabCell.innerHTML = players[i][col[j]];
		                if(j==2) j++;
		            }
		            var tabCell = tr.insertCell(-1);
		            
		            var selectList = document.createElement("select");
		            
		            selectList.id = "select" + players[i][col[0]].toString();
				    homeTeamPlayersResultId.push(selectList.id);
				    
				    for (var k = 0; k < playerResults.length; k++) {
				    	var option = document.createElement("option");
				        option.value = playerResults[k][colPlayerResults[1]];
				        option.text = playerResults[k][colPlayerResults[1]];
				       // option.id = playerResults[k][1];
				        selectList.appendChild(option);
				    }
				    //homeTeamPlayersResultValue.push(selectList.options[selectList.selectedIndex].value);
		            tabCell.appendChild(selectList);
	            }
	        }
	        
	        tableDiv.appendChild(table);
		}
		
		
		
		function showAwayTeamPlayers(){
			var players = JSON.parse('${players}');
			var playerResults = JSON.parse('${playerResults}');
			
		    var tableDiv = document.getElementById("awayPlayersTable");
		    tableDiv.innerHTML = "";
		    
		    var awayTeam = document.getElementById("getAwayRealTeamId");
		    var awayTeamId = awayTeam.options[awayTeam.selectedIndex].id;   
		    
		    var colPlayerResults = [];
	        for (var i = 0; i < playerResults.length; i++) {
	            for (var key in playerResults[i]) {
	                if (colPlayerResults.indexOf(key) === -1) {
	                    colPlayerResults.push(key);
	                }
	            }
	        }
	
		    var col = [];
	        for (var i = 0; i < players.length; i++) {
	            for (var key in players[i]) {
	                if (col.indexOf(key) === -1) {
	                    col.push(key);
	                }
	            }
	        }
	        var table = document.createElement("table");
	        table.className = "table table-hover";
	        var tr = table.insertRow(-1);
	        
	        for (var i = 1; i < col.length-1; i++) {
	            var th = document.createElement("th");
	            th.innerHTML = col[i];
	            tr.appendChild(th);
	            if(i==2) i++;
	        }
	        
	        for (var i = 0; i < players.length; i++) {

	            tr = table.insertRow(-1);

	            if(players[i][col[5]] == awayTeamId){
		            for (var j = 1; j < col.length-1; j++) {
		                var tabCell = tr.insertCell(-1);
		                tabCell.innerHTML = players[i][col[j]];
		                if(j==2) j++;
		            }
		            var tabCell = tr.insertCell(-1);
		            
		            var selectList = document.createElement("select");
		            
		            selectList.id = "select" + players[i][col[0]].toString();
				    awayTeamPlayersResultId.push(selectList.id);
				    
				    for (var k = 0; k < playerResults.length; k++) {
				    	var option = document.createElement("option");
				        option.value = playerResults[k][colPlayerResults[1]];
				        option.text = playerResults[k][colPlayerResults[1]];
				       // option.id = playerResults[k][1];
				        selectList.appendChild(option);
				    }
				    //awayTeamPlayersResultValue.push(selectList.options[selectList.selectedIndex].value);
		            tabCell.appendChild(selectList);
	            }
	        }
	        
	        tableDiv.appendChild(table);
		}
		
		function sendData(){
			
			for (var i = 0; i<homeTeamPlayersResultId.length; i++){
				var select = document.getElementById(homeTeamPlayersResultId[i]);
				var selectValue = select.options[select.selectedIndex].value;
				homeTeamPlayersResultValue.push(selectValue);
			}
			
			for (var i = 0; i<awayTeamPlayersResultId.length; i++){
				var select = document.getElementById(awayTeamPlayersResultId[i]);
				var selectValue = select.options[select.selectedIndex].value;
				awayTeamPlayersResultValue.push(selectValue);
			}
			
			$.ajax({
				type : "POST",
				url : "/admin/matches/addMatch",
				data : {
					myArray : homeTeamPlayersResultId,
					myArray1 : awayTeamPlayersResultId,
					myArray2 : homeTeamPlayersResultValue,
					myArray3 : awayTeamPlayersResultValue
				},
				traditional : true,
				success : function(response) {
				       alert(ok);
				},
				error : function(e) {
					   //alert(JSON.stringify(e));
				}
			});
		}
		</script>
	</body>
</html>