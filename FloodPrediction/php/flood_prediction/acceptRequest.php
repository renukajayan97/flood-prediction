<?php

include("connection.php");

$rid = $_REQUEST['rid'];
$reqId = $_REQUEST['reqId'];

$select = " UPDATE rescue_request SET rid = '$rid' WHERE id = '$reqId' ";
if(mysqli_query($con,$select))
	echo "sucess";


?>