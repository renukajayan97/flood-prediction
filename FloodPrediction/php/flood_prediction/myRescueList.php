<?php

include("connection.php");

$rid = $_REQUEST['rid'];

$sql = " SELECT * FROM rescue_request WHERE rid = '$rid' AND status = 'requested'";
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