<?php

include("connection.php");

$fname = $_POST['fname'];
$lname = $_POST['lname'];
$phone = $_POST['phone'];
$email = $_POST['email'];
$latitude = $_POST['latitude'];
$longitude = $_POST['longitude'];
$username = $_POST['username'];
$password = $_POST['password'];

$sql = " INSERT INTO user(username, password, name, Lname, phone_no, latitude, longitude, email) VALUES ('$username','$password','$fname','$lname','$phone','$latitude','$longitude','$email') ";

if(mysqli_query($con,$sql))
	echo "success";
else
	echo "Failed";



?>