<!DOCTYPE html>
<html>
<body>
<meta http-equiv="refresh" content="10">
<style>

table {
  border-collapse: collapse;
}
</style>
<?php
session_start();
if(isset($_REQUEST['uid'])){
	$_SESSION['uid']=$_REQUEST['uid'];
}



 $con=mysqli_connect("localhost", "root", "","navigation");
 
 $sid=$_SESSION['uid'];
$sar=split("_",$sid);
//$fid=$_SESSION['fid'];

$uid=$sar[0];
$user=$sar[1];

$uid=$_SESSION['uid'];

//echo $uid;
if($user == 'user')
	$res=mysqli_query($con,"select * from sig where uid='$uid' ") or die(mysqli_error($con));
else if($user == 'teacher')
	$res=mysqli_query($con,"select * from teacher_tbl where id='$uid' ") or die(mysqli_error($con));

while($row=mysqli_fetch_array($res))
{  
$ssid=$row['ssid'];
}

//echo $ssid;
$res=mysqli_query($con,"select * from data where wifi='$ssid' ") or die(mysqli_error($con));

while($row1=mysqli_fetch_array($res))
{  
$room=$row1['room'];
}
//echo $room;
?>

<table border="2" width="100%"  overflow-y="scroll"  padding="10">
<tr>
<td style="background-image:url(S6IT.png); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>
<?php
if($room=="S6 IT")
echo "<img src='dot.png' height='50' style='margin-top:10px;'>";

?>
<td style="background-image:url(ULYSSES.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<?php
if($room=="ULYSSES LAB")
echo "<img src='dot.png' height='50' style='margin-top:50px;'>";

?>
<td style="background-image:url(S4ITnew.png); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/></td>


<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(QUANTUM2.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(QUANTUM.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(QUANTUM2.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
</tr>
<tr>
	<td style="background-image:url(BOHRLAB.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>

<td style="background-image:url(court.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>

<td style="background-image:url(ITSTAFFROOM.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(mainstair.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(BASICSTAFFROOM.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(court.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(PHOTON.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
</tr>
<tr>
	<td style="background-image:url(S8ITnew.png); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>

<td style="background-image:url(HEISEN.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<?php
if($room=="HEISENBERG LAB")
echo "<img src='dot.png' height='50' style='margin-top:10px;'>";

?>
<td style="background-image:url(box.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/></td>
<td style="background-image:url(cbox.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(cbox.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(DEPTLIB.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
</tr>
<tr>
	<td style="background-image:url(secstair.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>

<td style="background-image:url(p2.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>

<td style="background-image:url(fbox.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/></td>
<td style="background-image:url(center.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(lawn .jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(dbox.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(p2.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(secstair1.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
</tr>
<tr>
	<td style="background-image:url(S6CSA1.png); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>

<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>

<td style="background-image:url(box.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/></td>
<td style="background-image:url(ebox.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(ebox.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(CCF.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(S8CSG.png); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
</tr>
<tr>
	<td style="background-image:url(CSSTAFFROOM1new.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>

<td style="background-image:url(court.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>

<td style="background-image:url(CSSTAFFROOM2new.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/></td>
<td style="background-image:url(mainstair1.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(CSSTAFFROOM3new.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(court.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(ELECLAB1.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
</tr>
<tr>
<td style="background-image:url(S6CSb1.png); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>

<td style="background-image:url(HERCULES.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>

<td style="background-image:url(S6CSg.png); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(box.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(S8CSb.png); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(ELECLAB2.jpg); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
<td style="background-image:url(S8CSa.png); background-repeat:no-repeat; background-size:cover;" width="150px" height="150px"/></td>
</tr>
<tr>
<td style="background-image:url(box.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>
</tr>
<tr>
<td style="background-image:url(box.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>
</tr>
<tr>
<td style="background-image:url(box.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>
</tr>
<tr>
<td style="background-image:url(box.jpg); background-size:cover; background-repeat:no-repeat;" width="150px" height="150px"/>
</tr>










<!--
</td>
<td style="background-image:url(ULY.png); background-repeat:no-repeat;" />

<?php
if($room=="ULYSSES LAB")
echo "<img src='dot.png' height='50'>";

?>


</td>
<td style="background-image:url(S4.png); background-repeat:no-repeat; background-size:cover;" width="91px" height="117px"/>


<?php
if($room=="QSTAFF")
echo "<img src='dot.png' height='20'>";

?>


</td>
<td width="200px"></td>
<td colspan="2" style="background-image:url(QUA.png); background-repeat:no-repeat; " width="225px" height="114px"/>
<?php
if($room=="QSTAFF")
echo "<img src='dot.png' height='20'>";

?>

</td>
<td rowspan="2" style="background-image:url(PHO.png); background-repeat:no-repeat; " width="133px" height="438px"/>

<?php
if($room=="QSTAFF")
echo "<img src='dot.png' height='20'>";

?>
</td>
</tr>

<tr>
<td style="background-image:url(class1.jpg); background-repeat:no-repeat; background-size:cover;" />


<tr>
<td style="background-image:url(BOHR.png); background-repeat:no-repeat; background-size:cover;" width="150" height="150"/>
<?php
if($room=="QSTAFF")
echo "<img src='dot.png' height='20'>";

?>   </td>

<td style="background-image:url(COU1.png); background-repeat:no-repeat; background-size:cover;" width="137px" height="173px"/></td>
<td style="background-image:url(ITSTAFF.png); background-repeat:no-repeat; background-size:cover;" width="150" height="150"/></td>
<td style="background-image:url(ITSTAIRS.png); background-repeat:no-repeat; "/></td>
<td style="background-image:url(QSTAFF.png); background-repeat:no-repeat;" width="120px"/></td>
<td style="background-image:url(COU2.png); background-repeat:no-repeat;" width="130px" /></td>
</tr>
<tr>
<td width="150" height="150"  style="background-image:url(S8.png); background-repeat:no-repeat;"/></td>
<td rowspan="2" style="background-image:url(HER.png); background-repeat:no-repeat; background-size:cover;" width="150" height="150"/></td>
<td width="200px" colspan="3"></td>
<td style="background-image:url(LIBSTAIRS.png); background-repeat:no-repeat;" /></td>
<td style="background-image:url(LIB.png); background-repeat:no-repeat; background-size:cover;" width="150" height="150"/></td>
</tr>
<tr>
<td height="50px"></td>
<td height="50px"colspan="5"></td>
</tr>

-->
</tr>
</table>
</body>
</html>
