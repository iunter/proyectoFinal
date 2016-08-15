<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$string = file_get_contents('php://input');
$paciente=json_decode($string,true);
$query = 'SELECT * FROM paciente WHERE idpaciente ='. $paciente["idpaciente"]. ';';
$result = mysqli_query($con, $query);
$objetos = array();
//esta asignando
while($row = mysqli_fetch_array($result)) 
{ 
	$Id=$row['idpaciente'];
	$nombre=$row['nombre'];
	$apellido=$row['apellido'];
	$dni = $row['dni'];
	$socio=$row['socio'];
	$foto=$row['foto'];
	$lat=$row['lat'];
	$longg=$row['longg'];
	$idobrasocial=$row['idobrasocial'];
	$objeto = array('idpaciente'=> $Id, 'nombre'=> $nombre, 'apellido'=> $apellido, 'dni'=> $dni, 'socio'=> $socio, 'foto'=> $foto, 'lat'=> $lat, 'longg'=> $longg, 'idobrasocial'=> $idobrasocial);	
	
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objeto,JSON_PRETTY_PRINT);
echo $json_string;
?>