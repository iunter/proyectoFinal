<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " .mysqli_connect_error();
}
else
{		
$string = file_get_contents('php://input');
$usuario=json_decode($string,true);
$query = "insert into usuariopaciente (idusuario, idpaciente) values (?, ?);";
$stmt=$con->prepare($query);
$stmt->bind_param(
		'ii',
		$usuario["idusuario"],
		$usuario["idpaciente"]
		);
		$stmt->execute();
		//$res=$stmt->get_result();
}
mysqli_close($con);
?>