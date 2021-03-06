<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BMS</title>
<script src="js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(function() {
			$('#city_id').change(function() {  fillOptions('movies_id', this); });
			$('#movies_id').change(function() { fillOptions('theatre_id', this); });

			//$('#choose_seats_id').change(function() { fillOptions('theatre_id', this); });
			var seats_required;
			$('#ok_button_id').click(function (){ 
				total_seats = $('#seat_required').val();
					fillSeats('choose_seats_id');
			 	$.get('Setup',{
					NumOfSeats : total_seats
				},function(total_price){
						
					$('#price_id').text(total_price.value));
				}); 
			});
	    	/* $('#seat_required').change(function(){
				alert($('#seat_required').val());
				var no_seats=$('#seat_required').val();
				$.get('Setup',{
					NumOfSeats : no_seats
				},function(send_price){
					alert(send_price);
					$('#price_id').text(send_price);
				});
			}); */
	});
    	function fillOptions(ddId, callingElement) {
        	var dd = $('#' + ddId);
        	$.getJSON('Setup?dd=' + ddId + '&val=' + $(callingElement).val(), function(opts) {
                $('>option', dd).remove(); // Clean old options first.
                if (opts) {
                     $.each(opts, function(key, value) {
                       //alert(key + "" + value);
                       dd.append($('<option/>').val(key).text(value));
                    });
                } else {
                     dd.append($('<option/>').text("Please select parent"));
                }
          });
       }

    	function fillSeats(ddId) {
        	var dd = $('#' + ddId);
        	$.getJSON('Setup?dd=' + ddId, function(opts) {
                $('>option', dd).remove(); // Clean old options first.
                if (opts) {
                	alert(opts);
                    $.each(opts, function(key, value) {
                       dd.append($('<option/>').val(key).text(value));
                    });
                } else {
                     dd.append($('<option/>').text("Please select parent"));
                }
          });
       }	

    	$('#seat_required').change(function(){
			alert($('#seat_required').val());
			var no_seats=$('#seat_required').val();
			$.get('PriceServlet',{
				NumOfSeats : no_seats
			},function(send_price){
				alert(send_price);
				$('#price_id').text(send_price);
			});
		});
		
</script>
</head>
<body> 
<div class="container">
<div class="demo">
	<h1> Lets Book movie ticket</h1>
	<div class="dropdown">
	<h2>Select a city from the list below</h2>
	<form>
	<c:import url="/Setup" />	
	<c:set var="myCities" value="${requestScope.citylist}" />
	<select id="city_id">
		<option selected="selected">- choose below city-</option> 
		<c:forEach items="${myCities}" var="city">
			<option>${city.getCname()}</option>
		</c:forEach>		
	</select>
	</div>

	
	
	<h2>>Select a movie from the list below</h2>
	
	<select id="movies_id" name="movies_id">
		<option selected="selected">- choose below movie-</option>
		<c:forEach items="${movies_id}" var="movie">
			<option>${movie.getMovieName()}</option>
		</c:forEach>
	</select>
	
	
	
	<h2>Select a theatre from the list below</h2>
	<select id="theatre_id" name="theatre_id">
		<option selected="selected">- choose below theatre-</option>
		<c:forEach items="${theatre_id}" var="theatre">
			<option>${theatre.getName()}</option>
		</c:forEach>
	</select>
	
	<h2>Type number of Seats</h2> 
	<input type="text" name="seat_required" id="seat_required" onchange=""required/>
	<input type="button" name="ok_button_id" id="ok_button_id" value="OK"/>		
		
	<h2>Pick from below available seats</h2>
	<select id="choose_seats_id" name="choose_seats_id" >
		<option selected="selected">- choose from below available seats-</option>
		<c:forEach items="${choose_seats_id}" var="seats">
			<option>${seats.value}</option>
		</c:forEach>
	</select>
	
	<h2>Total Price is:</h2>
	<div id="price_id" name="price_id"></div>

	<button type="submit">Book</button>
	</form>
	</div>
	</div>
</body>
</html>