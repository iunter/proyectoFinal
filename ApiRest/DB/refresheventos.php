<?php
//conexion a la base de datos
$con=mysqli_connect("localhost","root","","mydb");

if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
   
$query = 'SELECT * FROM eventos';
$result = mysqli_query($con, $query);

$eventos = array();

while($row = mysqli_fetch_array($result)) 
{ 
	$id=$row['idEvento'];
	$nombre=$row['nombre'];
	$fecha=$row['fecha'];
	$descripcion=$row['descripcion'];
	$lugar=$row['lugar'];
	
	$evento = array('idEvento'=> $id, 'nombre'=> $nombre, 'fecha'=> $fecha, 'lugar'=> $lugar,'descripcion'=> $descripcion);
	
    $eventos[] = $evento;
	
	// array_push
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");

header("Content-Type: application/json");
$json_string = json_encode($eventos,JSON_PRETTY_PRINT);
echo $json_string;
?>
