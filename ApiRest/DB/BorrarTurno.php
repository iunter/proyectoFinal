<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " .mysqli_connect_error();
}
else
{		
$string = file_get_contents('php://input');
$turno=json_decode($string,true);
$query = "delete from turno where turno.idturno = ?";
$stmt=$con->prepare($query);
$stmt->bind_param(
		'i',
		$turno["idturno"]
		);
		$stmt->execute();
		//$res=$stmt->get_result();
}
mysqli_close($con);
?>