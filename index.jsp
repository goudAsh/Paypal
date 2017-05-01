<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BMS</title>
</head>
<body>
	<h1> Lets Book movie ticket</h1>
	<h2>Select a city from the list below</h2>
	
	<c:import url="/Setup" />
	<c:set var="myCities" value="${requestScope.citylist}" />
	<select id="cityid">
		<option selected="selected">- choose below city-</option>
		<c:forEach items="${myCities}" var="city">
			<option>${city.getCname()}</option>
		</c:forEach>		

	</select>

	<h2>Select a movie from the list below</h2>
	
	<select id="movies_id">
	</select>
</body>
</html>