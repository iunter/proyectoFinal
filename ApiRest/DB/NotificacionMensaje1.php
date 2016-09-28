<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$cambios = false;
$string = file_get_contents('php://input');
$mensaje=json_decode($string,true);
$query = 'SELECT COUNT(mensaje) AS cont, mensaje.idchat, chat.idusuario1 as idusuario1, chat.idusuario2 as idusuario2  FROM mensaje INNER JOIN chat ON chat.idchat = mensaje.idchat WHERE enviado = 0 AND idusuario NOT ='.$mensaje['usuario'].'GROUP BY mensaje.idchat;';
$objetos = array();
//esta asignando
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) 
{ 
	if($row['cont'] > 0)
	{
		if($row['idusuario1'] == $mensaje['usuario'] || $row['idusuario2'] == $mensaje['usuario'])
		{
			$Id=$row['idchat'];
			$cantidad=$row['cont'];
			$objeto = array('idchat'=> $Id, 'cont'=> $cantidad);	
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