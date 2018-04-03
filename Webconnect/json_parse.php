<?php

define('DBHOST', 'localhost');
define('DBUSER', 'root');
define('DBPASS', '');
define('DBNAME', 'User');

$conn = mysqli_connect(DBHOST,DBUSER,DBPASS,DBNAME);

$data = file_get_contents('php://input');
$json_data = json_decode($data , true);

$sql= "SELECT * FROM user";
$Pres=mysqli_query($conn,$sql);
$outp = array();
$outp = $Pres->fetch_all(MYSQLI_ASSOC);
echo json_encode($outp);
?>
