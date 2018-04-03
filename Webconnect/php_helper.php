<?php
	define('DBHOST', 'localhost');
	define('DBUSER', 'root');
	define('DBPASS', '');
	define('DBNAME', 'User');

	$conn = mysqli_connect(DBHOST,DBUSER,DBPASS,DBNAME);

	if ( !$conn ) {
		//$response = array('status' => false, 'message' => 'Connection failed');
	}
	else {
		//echo "Connected";
	}

	    $data = file_get_contents('php://input');
	    $json_data = json_decode($data , true);

	    //code to process data

	    //if ($data == "" || empty($json_data['user_id']) || empty($json_data['password']))
	   // {
	   //     $response = array('status' => false, 'message' => 'Invalid Values');
	  // }
				//$id=$jason_data['user_id'];

			// $id=
			 // $id= $json_data['name'];
 			 $email= $json_data['email'];
 			  //$email= "shakibuzzaman3104@gmail.com";

 			// $sql = "SELECT `pass` FROM `user` WHERE `user_id`=$id";

 			  $sql = "SELECT pass FROM user WHERE email='$email'";

 	    	$q=mysqli_query($conn,$sql);

 				$row	= mysqli_fetch_assoc($q);

 				 // echo $row['pass'];
 				  $res=$row['pass'];
 	        if($res==null || $json_data['pass']!=$res)
 					{
 						// || $json_data['pass']!=$q
 						$response = array('status'=>false,'message'=>'Username or password is not valid');
 						 echo json_encode($response);
 					}

 	        else
 					{
 						$response = array('status'=>true,'message'=>"login Successful!!");
 						echo json_encode($response);
 					}


?>
