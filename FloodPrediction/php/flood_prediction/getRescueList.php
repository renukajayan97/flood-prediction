<?php

include("connection.php");

$rid = $_REQUEST['rid'];

$sql = " SELECT * FROM rescuer WHERE id = '$rid' ";
$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	$lat = $row['latitude'];
	$lon = $row['longitude'];
	
			$sql1 = " SELECT id,name,location,latitude,longitude,SQRT( POW(69.1 * (latitude - '$lat'), 2) + POW(69.1 * ('$lon' - longitude) * COS(latitude / 57.3), 2)) AS distance FROM rescue_request WHERE status = 'requested' AND rid = '0' HAVING distance < 10 ORDER BY distance DESC ";
			$result1 = mysqli_query($con,$sql1);
			while($row1 = mysqli_fetch_assoc($result1))
			{
				$data['data'][] = $row1;			    
			}	

			echo json_encode($data);
}
else{
echo "Failed";	
}


?>