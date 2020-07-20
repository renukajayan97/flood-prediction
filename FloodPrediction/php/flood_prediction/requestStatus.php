<?php

include("connection.php");

$uid = $_REQUEST['uid'];

$select = " SELECT * FROM rescue_request WHERE uid = '$uid' and status = 'requested' ";
$res = mysqli_query($con,$select);

if(mysqli_num_rows($res) > 0)
{

	$row = mysqli_fetch_assoc($res);
	$rid = $row['rid'];
	if($rid == 0)
	{
		echo "Not Assigned";
	}
	else{
		$rescuer_select = " SELECT * FROM rescuer WHERE id = '$rid' ";
		$result = mysqli_query($con,$rescuer_select);
		$rrow = 	mysqli_fetch_assoc($result);
		$data['data'][] = $rrow;

		echo json_encode($data);
	}

}
else
{
	echo "Failed";
}



?>