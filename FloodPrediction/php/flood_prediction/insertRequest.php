<?php

include("connection.php");

$uid = $_POST['uid'];
$location = $_POST['location'];
$emergency = $_POST['emergency'];
$needs = $_POST['needs'];
$hazards = $_POST['hazards'];
$pplCount = $_POST['pplCount'];
$petCount = $_POST['petCount'];
$disabledCount = $_POST['disabledCount'];
$message = $_POST['message'];
$latitude = $_POST['latitude'];
$longitude = $_POST['longitude'];


$sql = " SELECT * FROM user WHERE id = '$uid' ";
$result = mysqli_query($con,$sql);
$row1 = mysqli_fetch_assoc($result);
$name = $row1['name'];			    
$phone = $row1['phone_no'];			    



$select = " SELECT * FROM rescue_request WHERE uid = '$uid' and status = 'requested'  ";
$res = mysqli_query($con,$select);

if(mysqli_num_rows($res) > 0)
	echo "Already requested";

else
{
	$sql = " INSERT INTO rescue_request(uid, name, phone, location, emergency, needs, hazards, ppl_count, pets_count, disabled_count, message, latitude, longitude, status) VALUES 
			  ('$uid', '$name', '$phone', '$location', '$emergency', '$needs', '$hazards', '$pplCount', '$petCount', '$disabledCount', '$message', '$latitude', '$longitude', 'requested') ";

	if(mysqli_query($con,$sql))
		echo "success";
	else
		echo "Failed";
}



?>