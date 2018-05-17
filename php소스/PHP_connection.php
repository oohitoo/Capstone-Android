<?php
    
 $con = mysqli_connect("localhost","root","","theunit");

if(mysqli_connect_errno($con)){
    echo "Failed to connect to MYSQL ". mysqli_connect_error();
}

mysqli_set_charset($con, "utf8");

$res = mysqli_query($con, "select * from datadb");

$result = array();

while($row = mysqli_fetch_array($res)){
    array_push($result, array('id'=>$row[0],'sensor1'=>$row[1],'sensor2'=>$row[2],'sensor3'=>$row[3],'sensor4'=>$row[4],'sensor5'=>$row[5],'sensor6'=>$row[6],'posture'=>$row[7] ));
}

$test = count($result)-1; //마지막

    $les = array_slice($result, $test, $test); // $lse에 $result배열을 불러와서 저장 해서 자름
    echo json_encode(array("result"=>$les)); // $les를 가져 와서 result에 저장 시킴 그걸 인코딩해줌

mysqli_close($con);

?>