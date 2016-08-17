<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$string = file_get_contents('php://input');
$publicacion=json_decode($string,true); 
$query = 'SELECT publicacion.*, usuario.usuario AS usuario FROM publicacion inner join usuario on usuario.idusuario = publicacion.idusuario where publicacion.idpaciente = '.$publicacion[idpaciente].';';
$result = mysqli_query($con, $query);
$objetos = array();
//esta asignando
while($row = mysqli_fetch_array($result)) 
{ 
	$Id=$row['idpublicacion'];
	$idusuario=$row['idusuario'];
	$idpaciente = $row['idpaciente'];
	$mensaje = $row['mensaje'];
	$usuario = $row['usuario'];
	$objeto = array('idpublicacion'=> $Id, 'idusuario'=> $idusuario, 'idpaciente'=>$idpaciente, 'mensaje' => $mensaje, 'usuario' => $usuario);	
    $objetos[] = $objeto;
	
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objetos,JSON_PRETTY_PRINT);
echo $json_string;
?>