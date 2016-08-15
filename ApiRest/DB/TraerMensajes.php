<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$string = file_get_contents('php://input');
$mensajee=json_decode($string,true);
$query = 'SELECT mensaje.*, usuario.usuario AS usuario FROM( mensaje INNER JOIN usuario ON usuario.idusuario = mensaje.idusuario )WHERE idchat = \''.$mensajee["idchat"].'\';';
$objetos = array();
//esta asignando
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) 
{ 
	$idmensaje=$row['idmensaje'];
	$mensaje = $row['mensaje'];
	$idchat=$row['idchat'];
	$idusuario=$row['idusuario'];
	$usuario=$row['usuario'];
	//actualizar en host
	$objeto = array('idmensaje'=> $idmensaje, 'mensaje'=> $mensaje, 'idchat'=> $idchat, 'idusuario'=> $idusuario, 'usuario'=> $usuario);	
	$objetos[] = $objeto;
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objetos,JSON_PRETTY_PRINT);
echo $json_string;
?>