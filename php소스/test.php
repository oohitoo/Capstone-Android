<?php
    
    $con = mysqli_connect("localhost","root","","theunit");
    $datap = $_POST["data"]; //tv2로 저장된걸 가져와서 datp로 저장

    if(mysqli_connect_errno($con)){
        echo "연결 실패".mysqli_connect_error();
    }
    $res = mysqli_query($con, "SELECT * FROM `datadb` WHERE posture like '?%'");

    $result = array();
    $result["success"]= false;

    while($row = mysqli_fetch_array($res)){
        $result["success"]=true;
    array_push($result, array('id'=>$row[0],'sensor1'=>$row[1],'sensor2'=>$row[2],'sensor3'=>$row[3],'sensor4'=>$row[4],'sensor5'=>$row[5],'sensor6'=>$row[6],'posture'=>$row[7], 'date_time'=>$row[8]));
}
    echo json_encode(array("result"=>$result));
    mysqli_close($con);
 ?>