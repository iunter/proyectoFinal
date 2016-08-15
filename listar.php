<?php
$con=mysqli_connect("mysql.hostinger.com.ar","base","fjo001","base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
   
$query = 'call TraerTurno';
$result = mysqli_query($con, $query);
$objetos = array();
//esta asignando
while($row = mysqli_fetch_array($result)) 
{ 
	$Id=$row['idturno'];
	$Fecha=$row['Fecha'];
	$Hora=$row['Hora'];
	$idpaciente = $row['idpaciente'];
	$idcentro=$row['idcentro'];
	$objeto = array('Id'=> $Id, 'Fecha'=> $Fecha, 'Hora'=> $Hora, 'idpaciente'=> $idpaciente, '$idcentro'=> $idcentro);	
    $objetos[] = $objeto;
	
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objetos,JSON_PRETTY_PRINT);
echo $json_string;
?>