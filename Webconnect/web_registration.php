<?php

define('DBHOST', 'localhost');
define('DBUSER', 'root');
define('DBPASS', '');
define('DBNAME', 'User');

$conn = mysqli_connect(DBHOST,DBUSER,DBPASS,DBNAME);

$data = file_get_contents('php://input');
$json_data = json_decode($data , true);

$name=$json_data['name'];
$age=$json_data['age'];
$blood_group=$json_data['blood_group'];
$email=$json_data['email'];
$pass=$json_data['pass'];

/*$age="16";
$blood_group="A+";
$email="Tahsin8014@gmail.com";
$pass="123456";*/

$q="SELECT email FROM user WHERE email='$email'";
$query=mysqli_query($conn,$q);

      if(!$query)
      {
        $response = array('message'=>'Email Address Already Exists');
        echo json_encode($response);
      }

      else
      {
        $sql="INSERT INTO user(name,age,blood_group,email,pass) VALUES('$name',$age,'$blood_group','$email','$pass')";
        $res=mysqli_query($conn,$sql);

        if($res)
        {
    	     $response = array('message'=>'Registration Successful');
           echo json_encode($response);
        }

        else
        {
          $response = array('message'=>'Registration Failed');
          echo json_encode($response);
        }
      }

?>
