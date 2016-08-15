<?php
$con=mysqli_connect("localhost","root","","mydb");
if (mysqli_connect_errno()) {
	die("Failed to connect to MySQL" . mysqli_connect_error());
}

$string = file_get_contents('php://input'); 
$objeto = json_decode($string, true);
$query = "INSERT INTO objetos (nombre,precio) values (?, ?)";
$stmt = $con->prepare($query);
$stmt->bind_param(
	'ss',
	$objeto["nombre"],
	$objeto["precio"]
);
$stmt->execute();
$res = $stmt->get_result();

mysqli_close($con);

?>