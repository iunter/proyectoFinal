<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$string = file_get_contents('php://input');
$mensaje=json_decode($string,true);
$query = 'SELECT COUNT(mensaje) AS cont FROM mensaje WHERE idchat = \''.$mensaje["idchat"].'\';';
$objetos = array();
//esta asignando
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) 
{ 
	$cont=$row['cont'];
	//actualizar en host
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
if($cont == $mensaje["cont"])
{
	$objeto = array('cambios'=> false);	
	$json_string = json_encode($objeto,JSON_PRETTY_PRINT);
	echo $json_string;
}
else
{
	$objeto = array('cambios'=> true);	
	$json_string = json_encode($objeto,JSON_PRETTY_PRINT);
	echo $json_string;
}
?>