<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$string = file_get_contents('php://input');
$usuario=json_decode($string,true);
$query = 'SELECT * FROM usuario WHERE usuario = \''.$usuario[usuario].'\';';
$objetos = array();
//esta asignando
$result = mysqli_query($con, $query);
while($row = mysqli_fetch_array($result)) 
{ 
	$idusuario=$row['idusuario'];
	$usuario = $row['usuario'];
	$nombre=$row['nombre'];
	$dni=$row['dni'];
	$foto = $row['foto'];
	$matricula = $row['matricula'];
	$idtipo = $row['idtipo'];
	$idclave = $row['idclave'];
	//actualizar en host
	$objeto = array('idusuario'=> $idusuario, 'usuario'=> $usuario, 'nombre'=> $nombre, 'dni'=> $dni, 'foto'=> $foto, 'matricula'=> $matricula, 'idtipo'=> $idtipo);	
	
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objeto,JSON_PRETTY_PRINT);
echo $json_string;
?>