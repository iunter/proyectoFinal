<?php
$con=mysqli_connect("localhost","root","","mydb");
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " .mysqli_connect_error();
}
else
{		
$string = file_get_contents('php://input');
$usuario=json_decode($string,true);
$query = "UPDATE usuario SET Direccion=? WHERE idUsuario=?";
$stmt=$con->prepare($query);
$stmt->bind_param(
		'ss',
		$usuario["Direccion"],
		$usuario["idUsuario"],
		);
		$stmt->execute();
		$res=$stmt->get_result();

}
mysqli_close($con);



