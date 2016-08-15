<?php
$con=mysqli_connect("localhost","root","","consola");
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " .mysqli_connect_error();
}
else
{		
$string = file_get_contents('php://input');
$usuario=json_decode($string,true);
$query = "insert into usuario (usuario, nombre , apellido , dni , foto , matricula , idtipo , idclave) values (?, ?, ?, ?, ?, ?, ?, ?);";
$stmt=$con->prepare($query);
$stmt->bind_param(
		'sssissii',
		$usuario["usuario"],
		$usuario["nombre"],
		$usuario["apellido"],
		$usuario["dni"],
		$usuario["foto"],
		$usuario["matricula"],
		$usuario["idtipo"],
		$usuario["idclave"]
		);
		$stmt->execute();
		//$res=$stmt->get_result();
}
mysqli_close($con);
?>