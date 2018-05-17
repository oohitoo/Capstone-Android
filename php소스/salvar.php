<?php
    include('con1.php');

    $sensor1 = $_GET['sensor1'];
    $sensor2 = $_GET['sensor2'];
    $sensor3 = $_GET['sensor3'];
    $sensor4 = $_GET['sensor4'];
    $sensor5 = $_GET['sensor5'];
    $sensor6 = $_GET['sensor6'];
    $posture = $_GET['posture'];
//    if($sensor3 == 5){
//        	include_once 'push_notification.php';
//    }
    
    $sql = 'INSERT INTO datadb(`sensor1`, `sensor2`, `sensor3`, `sensor4`, `sensor5`, `sensor6`, `posture`) VALUES (:sensor1,:sensor2,:sensor3,:sensor4,:sensor5,:sensor6,:posture)';
        
    $stmt = $PDO->prepare($sql);

    $stmt ->bindParam(':sensor1',$sensor1);
    $stmt ->bindParam(':sensor2',$sensor2);
    $stmt ->bindParam(':sensor3',$sensor3);
    $stmt ->bindParam(':sensor4',$sensor4);
    $stmt ->bindParam(':sensor5',$sensor5);
    $stmt ->bindParam(':sensor6',$sensor6);
    $stmt ->bindParam(':posture',$posture);

    
//    $stmt->execute();
    if($stmt->execute()){
        echo "salvo_com_success";
    }else{
        echo "error";
    }
?>