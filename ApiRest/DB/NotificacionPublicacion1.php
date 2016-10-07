<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$cambios = false;
$string = file_get_contents('php://input');
$mensaje=json_decode($string,true);
$query = 'SELECT COUNT(mensaje) AS cont, publicacion.idusuario FROM publicacion WHERE enviado = 0 AND NOT idusuario  = '.$mensaje['usuario'].' AND idpaciente = '.$mensaje['paciente'].' GROUP BY publicacion.idusuario;';
$objetos = array();
//esta asignando
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) 
{ 
	if($row['cont'] > 0)
	{
		if($row['idusuario'] != $mensaje['usuario'])
		{
			$Id=$row['idusuario'];
			$cantidad=$row['cont'];
			$objeto = array('idusuario'=> $Id, 'cont'=> $cantidad);	
			$objetos[] = $objeto;
		}
	}
	//actualizar en host
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objetos,JSON_PRETTY_PRINT);
echo $json_string;
?>