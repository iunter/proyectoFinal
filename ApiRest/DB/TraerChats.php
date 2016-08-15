<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$string = file_get_contents('php://input');
$chat=json_decode($string,true);
$query = 'SELECT chat.* , a.usuario AS usuario1, b.usuario AS usuario2 FROM chat INNER JOIN usuario AS a ON a.idusuario = chat.idusuario1 INNER JOIN usuario AS b ON b.idusuario = chat.idusuario2 WHERE idusuario1 =\''.$chat['usuario'] .'\'OR idusuario2 =\''.$chat['usuario'].'\';';
$objetos = array();
//esta asignando
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) 
{ 
	$idchat=$row['idchat'];
	$usuario1 = $row['idusuario1'];
	$usuario2=$row['idusuario2'];
	$nusuario1 = $row['usuario1'];
	$nusuario2 = $row['usuario2'];
	//actualizar en host
	$objeto = array('idchat'=> $idchat, 'idusuario1'=> $usuario1, 'idusuario2'=> $usuario2, 'usuario1'=> $nusuario1, 'usuario2'=> $nusuario2);	
	$objetos[] = $objeto;
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objetos,JSON_PRETTY_PRINT);
echo $json_string;
?>	