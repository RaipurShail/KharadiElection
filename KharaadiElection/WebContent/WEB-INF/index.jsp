<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kharadi Election</title>
<style>
    form{
        margin: 20px 0;
    }
    form input, button{
        padding: 5px;
    }
    table{
        width: 100%;
        margin-bottom: 20px;
		border-collapse: collapse;
    }
    table, th, td{
        border: 1px solid #cdcdcd;
    }
    table th, table td{
        padding: 10px;
        text-align: left;
    }
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
    $(document).ready(function(){
    	
    	var data="";
    	
        $(".add-row").click(function(){
            var firstName = $("#firstName").val();
            var lastName = $("#lastName").val();
            var age = $("#age").val();
            var country = $("#country").val();
            data = data+firstName+","+lastName+","+age+","+country+":";
            $("#candidateData").val(data);
            var markup = "<tr><td>" + firstName + "</td><td>" + lastName + "</td><td>" + age + "</td><td>" + country + "</td></tr>";
            $("table tbody").append(markup);
            $("#firstName").val("");
            $("#lastName").val("");
            $("#age").val("");
            $("#country").val("");
        });
    });    
</script>
</head>
<body>
	<input type="text" name="firstName" id="firstName" placeholder="First Name">
	<input type="text" name="lastName" id="lastName" placeholder="Last Name">
	<input type="text" name="age" id="age" placeholder="Age">
	<input type="text" name="country" id="country" placeholder="Country">
	<form:form name="voterForm" action="getWinner" method="GET" modelAttribute="electionBean">
    	<form:input type="hidden" path="candidateData"/>
        <input type="button" class="add-row" value="Vote to Candidate">
        <input type="submit" id="submit" name="submit" value="Show Winner"/>
    </form:form>
    <h3><u>Voting Stat</u></h3>
    <table>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                <th>Country</th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
    <h1>${winner}</h1>
</body> 
</html>