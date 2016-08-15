<?php
$con=mysqli_connect("mysql.hostinger.com.ar","base","fjo001","base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$string = file_get_contents('php://input');
$turno=json_decode($string,true);
$query = 'call TraerUnTurno('.$turno["idturno"].')';
$objetos = array();
//esta asignando
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) 
{ 
	$Id=$row['idturno'];
	$Fecha=$row['Fecha'];
	$Hora=$row['Hora'];
	$idpaciente = $row['idpaciente'];
	$idcentro = $row['idcentro'];
	//actualizar en host
	$objeto = array('Id'=> $Id, 'Fecha'=> $Fecha, 'Hora'=> $Hora, 'idpaciente'=> $idpaciente, '$idcentro'=> $idcentro);	
	
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objeto,JSON_PRETTY_PRINT);
echo $json_string;
?>