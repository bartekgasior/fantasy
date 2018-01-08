<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		
		<title>Zarządzaj składem</title>
		
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
		<section>
		<sec:authorize access="hasRole('ROLE_USER')">
		
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-default">
			
			<div class="panel-heading" style="font-size:17px;">
			 	<input type="checkbox" id="radioGK" value="GK" onclick="showGKs();"> GoalKeepers
				<input type="checkbox" id="radioDF" value="DF" onclick="showDFs();"> Defenders
				<input type="checkbox" id="radioMF" value="MF" onclick="showMFs();"> Midfielders 
				<input type="checkbox" id="radioFW" value="FW" onclick="showFWs();"> Forwards 
				<div id="availableMoney" style="font-size:36px;text-align:center;"></div>
			</div>
				
			<div class="panel-body">

				<div class = "table-responsive" id="tableGK" ></div>
				<div class = "table-responsive" id="tableDF" ></div>
				<div class = "table-responsive" id="tableMF" ></div>
				<div class = "table-responsive" id="tableFW" ></div>
				
			</div>
			
			<div class="panel-footer">
				<a href="<spring:url value="/userPanel/teams" />" type ="submit" id="btnAdd" class="btn btn-primary" onclick="sendData()"> Potwierdz </a>
			</div>
		</div>
		</div>
		
		</sec:authorize>
		</section>
		
		<script type="text/javascript">		
		var money = parseInt('${leagueStartingMoney}', 10);
		
		var gkIDs = [];
		var dfIDs = [];
		var mfIDs = [];
		var fwIDs = [];
		
		var gkCBs = [];
		var dfCBs = [];
		var mfCBs = [];
		var fwCBs = [];
		
		function showGKs(){
		if (document.getElementById('radioGK').checked) {
			var players = JSON.parse('${gkList}');
			
			var tableDiv = document.getElementById("tableGK");
			tableDiv.innerHTML = "";
			
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
		        
		        for (var i = 1; i < col.length - 2; i++) {
		            var th = document.createElement("th");
		            th.innerHTML = col[i];
		            tr.appendChild(th);
		        }
		        
		        for (var i = 0; i < players.length; i++) {

		            tr = table.insertRow(-1);

			       for (var j = 1; j < col.length-2; j++) {
			           var tabCell = tr.insertCell(-1);
			           tabCell.innerHTML = players[i][col[j]];
			       }
			       var tabCell = tr.insertCell(-1);
			         
			       var checkbox = document.createElement("input");
			       checkbox.type="checkbox"
			       checkbox.value = players[i][col[3]];
			       checkbox.id = "checkboxGK" + players[i][col[0]].toString();
			       
			       gkCBs.push(checkbox.id);
				   //gkIDs.push(checkbox.id);

			       tabCell.appendChild(checkbox);
		        }
		        
		     tableDiv.appendChild(table);
		} else {
			var tableDiv = document.getElementById("tableGK");
			tableDiv.innerHTML = "";
		}
		}
		
		function showDFs(){
			if (document.getElementById('radioDF').checked) {
				var players = JSON.parse('${dfList}');
				
				var tableDiv = document.getElementById("tableDF");
				tableDiv.innerHTML = "";
				
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
			        
			        for (var i = 1; i < col.length - 2; i++) {
			            var th = document.createElement("th");
			            th.innerHTML = col[i];
			            tr.appendChild(th);
			        }
			        
			        for (var i = 0; i < players.length; i++) {

			            tr = table.insertRow(-1);

				       for (var j = 1; j < col.length-2; j++) {
				           var tabCell = tr.insertCell(-1);
				           tabCell.innerHTML = players[i][col[j]];
				       }
				       var tabCell = tr.insertCell(-1);
				         
				       var checkbox = document.createElement("input");
				       checkbox.type="checkbox"
				       checkbox.value = players[i][col[3]];
				       checkbox.id = "checkboxDF" + players[i][col[0]].toString();
				       
				       dfCBs.push(checkbox.id);
					   //gkIDs.push(checkbox.id);

				       tabCell.appendChild(checkbox);
			        }
			        
			     tableDiv.appendChild(table);
			} else {
				var tableDiv = document.getElementById("tableDF");
				tableDiv.innerHTML = "";
			}
			}
		
		function showMFs(){
			if (document.getElementById('radioMF').checked) {
				var players = JSON.parse('${mfList}');
				
				var tableDiv = document.getElementById("tableMF");
				tableDiv.innerHTML = "";
				
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
			        
			        for (var i = 1; i < col.length - 2; i++) {
			            var th = document.createElement("th");
			            th.innerHTML = col[i];
			            tr.appendChild(th);
			        }
			        
			        for (var i = 0; i < players.length; i++) {

			            tr = table.insertRow(-1);

				       for (var j = 1; j < col.length-2; j++) {
				           var tabCell = tr.insertCell(-1);
				           tabCell.innerHTML = players[i][col[j]];
				       }
				       var tabCell = tr.insertCell(-1);
				         
				       var checkbox = document.createElement("input");
				       checkbox.type="checkbox"
				       checkbox.value = players[i][col[3]];
				       checkbox.id = "checkboxMF" + players[i][col[0]].toString();
				       
				       mfCBs.push(checkbox.id);
					   //gkIDs.push(checkbox.id);

				       tabCell.appendChild(checkbox);
			        }
			        
			     tableDiv.appendChild(table);
			} else {
				var tableDiv = document.getElementById("tableMF");
				tableDiv.innerHTML = "";
			}
			}
		
		function showFWs(){
			if (document.getElementById('radioFW').checked) {
				var players = JSON.parse('${fwList}');
				
				var tableDiv = document.getElementById("tableFW");
				tableDiv.innerHTML = "";
				
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
			        
			        for (var i = 1; i < col.length - 2; i++) {
			            var th = document.createElement("th");
			            th.innerHTML = col[i];
			            tr.appendChild(th);
			        }
			        
			        for (var i = 0; i < players.length; i++) {

			            tr = table.insertRow(-1);

				       for (var j = 1; j < col.length-2; j++) {
				           var tabCell = tr.insertCell(-1);
				           tabCell.innerHTML = players[i][col[j]];
				       }
				       var tabCell = tr.insertCell(-1);
				         
				       var checkbox = document.createElement("input");
				       checkbox.type="checkbox"
				       checkbox.value = players[i][col[3]];
				       checkbox.id = "checkboxFW" + players[i][col[0]].toString();
				       
				       fwCBs.push(checkbox.id);
					   //gkIDs.push(checkbox.id);

				       tabCell.appendChild(checkbox);
			        }
			        
			     tableDiv.appendChild(table);
			} else {
				var tableDiv = document.getElementById("tableFW");
				tableDiv.innerHTML = "";
			}
			}
		
		function sendData(){
			gkIDs=[];
			dfIDs=[];
			mfIDs=[];
			fwIDs=[];
			for (var i = 0; i<gkCBs.length; i++){
				var gkcb = document.getElementById(gkCBs[i]);
				if(gkcb.checked){
					gkIDs.push(gkcb.id);
				}
			}
			
			for (var i = 0; i<dfCBs.length; i++){
				var dfcb = document.getElementById(dfCBs[i]);
				if(dfcb.checked){
					dfIDs.push(dfcb.id);
				}
			}
			
			for (var i = 0; i<mfCBs.length; i++){
				var mfcb = document.getElementById(mfCBs[i]);
				if(mfcb.checked){
					mfIDs.push(mfcb.id);
				}
			}
			
			for (var i = 0; i<fwCBs.length; i++){
				var fwcb = document.getElementById(fwCBs[i]);
				if(fwcb.checked){
					fwIDs.push(fwcb.id);
				}
			}
			
			$.ajax({
				type : "POST",
				url : "/userPanel/teams/edit",
				data : {
					gk : gkIDs,
					df : dfIDs,
					mf : mfIDs,
					fw : fwIDs
				},
				dataType: "json",
				traditional : true,
				success : function(response) {
				       alert(ok);
				},
				error : function(e) {
					   //alert(JSON.stringify(e));
				}
			});
		}
		
		$(document).ready(function() {
			document.getElementById("availableMoney").innerHTML = money;
			});
		
		$(document).on('change', '[type=checkbox]', function (e) {
			if(this.value != "GK" && this.value != "DF" && this.value != "MF" && this.value != "FW"){
				if(this.checked){
					money -= parseInt(this.value,10);
					if(money<0){
						alert("Budżet przekroczony!");
						money += parseInt(this.value,10);
						$(this).prop('checked', false);
					} else{
						
					}
				}else{
					money += parseInt(this.value,10);
				}
				document.getElementById("availableMoney").innerHTML = "";
				document.getElementById("availableMoney").innerHTML = money;
			}
			//alert('clicked');
		});
		</script>
		
		
	</body>
</html>