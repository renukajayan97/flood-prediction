<?php

include("connection.php");

$reqId = $_REQUEST['reqId'];

$sql = " SELECT * FROM rescue_request WHERE id = '$reqId' ";
$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result) > 0)
{
	while($row1 = mysqli_fetch_assoc($result))
	{
		$data['data'][] = $row1;			    
	}	

	echo json_encode($data);
}
else{
echo "Failed";	
}


?>