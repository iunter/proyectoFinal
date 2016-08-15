<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
   
$query = 'SELECT * FROM centro;';
$result = mysqli_query($con, $query);
$objetos = array();
//esta asignando
while($row = mysqli_fetch_array($result)) 
{ 
	$Id=$row['idcentro'];
	$nombre=$row['nombre'];
	$objeto = array('idcentro'=> $Id, 'nombre'=> $nombre);	
    $objetos[] = $objeto;
	
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objetos,JSON_PRETTY_PRINT);
echo $json_string;
?>