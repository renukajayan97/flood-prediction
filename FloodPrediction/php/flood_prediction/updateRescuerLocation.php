<?php

include("connection.php");

 $uid = $_REQUEST['rid'];
 $lat = $_REQUEST['latitude'];
 $lon = $_REQUEST['longitude'];
 // $uid = "1";
 
$sql = " UPDATE rescuer SET latitude = '$lat', longitude = '$lon'  WHERE id = '$uid' ";
mysqli_query($con,$sql);


?>