<?php

include("connection.php");

$name = $_POST['name'];
$phone = $_POST['phone'];
$email = $_POST['email'];
$housename = $_POST['housename'];
$houseno = $_POST['houseno'];
$taluk = $_POST['taluk'];
$latitude = $_POST['latitude'];
$longitude = $_POST['longitude'];
$username = $_POST['username'];
$password = $_POST['password'];



$sql = " INSERT INTO user(username, password, name, phone_no, house_name, house_no, taluk, latitude, longitude, email) VALUES ('$username','$password','$name','$phone','$housename','$houseno','$taluk','$latitude','$longitude','$email') ";

// echo $sql;

if(mysqli_query($con,$sql))
	echo "success";
else
	echo "Failed";



?>