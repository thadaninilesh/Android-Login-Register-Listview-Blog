<?php

require "init.php";

$user_name = $_POST['user_name'];
$user_pass = $_POST['user_pass'];

$sql_query = "SELECT name FROM user_info WHERE user_name like '$user_name' AND user_pass like '$user_pass'";
$result = mysqli_query($con, $sql_query);
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$name = $row['name'];
	echo "Login successful";
}
else{
	echo $sql_query;
}
?>