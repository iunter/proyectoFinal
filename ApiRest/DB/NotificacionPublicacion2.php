<?php
$con=mysqli_connect("mysql.hostinger.com.ar","u504884137_base","fjo001","u504884137_base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$cambios = false;
$string = file_get_contents('php://input');
$mensaje=json_decode($string,true);
//esta asignando
for($i=0;$i <= count($mensaje); $i++)
{
	$query = 'UPDATE publicacion SET enviado = 1 WHERE idpaciente = '.$mensaje[$i]['idpaciente'].' AND NOT idusuario = '.$mensaje[$i]['idusuario'].';';
	$result = mysqli_query($con, $query);
}
$close = mysqli_close($con);
?>