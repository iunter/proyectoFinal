<?php
$con=mysqli_connect("localhost","root","","consola");
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " .mysqli_connect_error();
}
else
{		
$string = file_get_contents('php://input');
$turno=json_decode($string,true);
$query = "call ModificarTurno(?,?,?,?,?)";
$stmt=$con->prepare($query);
$stmt->bind_param(
		'issii',
		$turno["idturno"],
		$turno["Fecha"],
		$turno["Hora"],
		$turno["idpaciente"],
		$turno["idcentro"]
		);
		$stmt->execute();
		//$res=$stmt->get_result();
}
mysqli_close($con);
?>