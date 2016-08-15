<?php
$con=mysqli_connect("mysql.hostinger.com.ar","base","fjo001","base");
if (mysqli_connect_errno()) {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
   
$query = 'SELECT turno.*, centro.nombre AS centro, medico.nombre AS medico FROM turno INNER JOIN centro ON centro.idcentro = turno.idcentro INNER JOIN medico ON turno.idmedico = medico.idmedico ORDER BY turno.idturno;';
$result = mysqli_query($con, $query);
$objetos = array();
//esta asignando
while($row = mysqli_fetch_array($result)) 
{ 
	$Id=$row['idturno'];
	$Fecha=$row['Fecha'];
	$Hora=$row['Hora'];
	$idpaciente = $row['idpaciente'];
	$idcentro=$row['idcentro'];
	$centro = $row['centro'];
	$medico = $row['medico'];
	$objeto = array('Id'=> $Id, 'Fecha'=> $Fecha, 'Hora'=> $Hora, 'idpaciente'=> $idpaciente, 'idcentro'=> $idcentro, 'centro'=> $centro, 'medico'=> $medico);	
    $objetos[] = $objeto;
	
}
$close = mysqli_close($con) 
or die("Ha sucedido un error inesperado en la desconexion de la base de datos");
header("Content-Type: application/json");
$json_string = json_encode($objetos,JSON_PRETTY_PRINT);
echo $json_string;
?>