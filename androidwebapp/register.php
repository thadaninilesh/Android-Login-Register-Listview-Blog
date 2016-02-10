<?php

require "init.php";
$name = $_POST['name'];
$user_name = $_POST['user_name'];
$user_pass = $_POST['user_pass'];
//these above identifiers are coming from the BackgroundTask class
$sql_query = "INSERT INTO user_info VALUES('$name', '$user_name', '$user_pass')";

if(mysqli_query($con, $sql_query)){
	//echo "Data successfully inserted";
}
else{
	//echo "Data insertion failed".mysqli_error($con);
}


?>