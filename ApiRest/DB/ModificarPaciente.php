<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " .mysqli_connect_error();
}
else
{		
$string = file_get_contents('php://input');
$paciente=json_decode($string,true);
$query = "update paciente set nombre = ?, apellido = ?, dni = ?, socio = ?, foto=?, lat=?, longg=?, idobrasocial = ? where paciente.idpaciente = '".$paciente["idpaciente"]."';";
$stmt=$con->prepare($query);
$stmt->bind_param(
		'ssiisiii',
		$paciente["nombre"],
		$paciente["apellido"],
		$paciente["dni"],
		$paciente["socio"],
		$paciente["foto"],
		$paciente["lat"],
		$paciente["longg"],
		$paciente["idobrasocial"]
		);
		$stmt->execute();
		//$res=$stmt->get_result();
}
mysqli_close($con);
?>