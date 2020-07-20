<?php

include("connection.php");

$uid = $_REQUEST['uid'];

$select = " UPDATE rescue_request SET status = 'completed' WHERE uid = '$uid' ";
$res = mysqli_query($con,$select);


?>