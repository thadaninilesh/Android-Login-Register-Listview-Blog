<?php
require "init.php";
$name = $_POST['name'];
$email = $_POST['email'];
$mobile = $_POST['mobile'];

$sql = "INSERT INTO contact_info VALUES('$name','$email','$mobile');";

if(mysqli_query($con,$sql)){
	echo $name." with email ".$email." and mobile no. ".$mobile." is inserted";
}
else{
	echo "Error in insertion".mysqli_error($con);
}
?>