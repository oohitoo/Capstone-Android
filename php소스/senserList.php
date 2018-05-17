<?php
//     $con = mysqli_connect("localhost","root","","theunit");
//    $datap = $_POST["tv2"]; //tv2로 저장된걸 가져와서 datp로 저장
//
//    if(mysqli_connect_errno($con)){
//        echo "연결 실패".mysqli_connect_error();
//    }
//    $res = mysqli_query($con, "SELECT * FROM `datadb` WHERE date_time like '%'");
//
//    $response = array();
//
//    while($row = mysqli_fetch_array($result)){
//    array_push($respone, array('id'=>$row[0],'sensor1'=>$row[1],'sensor2'=>$row[2],'sensor3'=>$row[3],'sensor4'=>$row[4],'sensor5'=>$row[5],'sensor6'=>$row[6],'posture'=>$row[7],'date_time'=>$row[8]));
//}
////    echo json_encode(array("response"=>$response));
////    mysqli_close($con);
// echo json_encode(array("response"=>$response));
//    $test = count($response)-1; //마지막
//    echo json_encode(array_slice ($response, $test, $test));// 마지막
//    echo count($response); //카운트값
//    mysqli_close($con);
$con = mysqli_connect("localhost","root","","theunit");

if(mysqli_connect_errno($con)){
    echo "Failed to connect to MYSQL ". mysqli_connect_error();
}

mysqli_set_charset($con, "utf8");

$res = mysqli_query($con, "select * from datadb");

$response = array();

while($row = mysqli_fetch_array($res)){
    array_push($response, array('id'=>$row[0],'sensor1'=>$row[1],'sensor2'=>$row[2],'sensor3'=>$row[3],'sensor4'=>$row[4],'sensor5'=>$row[5],'sensor6'=>$row[6],'posture'=>$row[7],'date_time'=>$row[8]));
}

echo json_encode(array("response"=>$response));

//$test = count($result)-1; //마지막
//
//    $les = array_slice($result, $test, $test); // $lse에 $result배열을 불러와서 저장 해서 자름
//    echo json_encode(array("result"=>$les)); // $les를 가져 와서 result에 저장 시킴 그걸 인코딩해줌

mysqli_close($con);

?>
