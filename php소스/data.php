<?php
    try{
        
        $HOST = "localhost";
        $DB_NAME = "datatest";
        $USER = "root";
        $PASSWORD = "";
        
        $PDO = new PDO("mysql:host=".$HOST.";dbname=".$DB_NAME.";charset=utf8",$USER,$PASSWORD);
        echo "database connecting success<br />\n";
        
        $sensor1 = $_GET['sensor1'];
        $sensor2 = $_GET['sensor2'];
        $sensor3 = $_GET['sensor3'];
        
        $sql = 'INSERT INTO testtable (sensor1, sensor2, sensor3) VALUES (:sensor1, :sensor2, :sensor3)';
        
        $stmt = $PDO->prepare($sql);

        $stmt ->bindParam(':sensor1',$sensor1);
        $stmt ->bindParam(':sensor2',$sensor2);
        $stmt ->bindParam(':sensor3',$sensor3);
        
        if($stmt->execute()){
            echo "salvo_com_success";
        }else{
            echo "error";
        }
        
    }catch(PDOExeption $erro){
        echo "Erro da : " . $erro->getMessage();
    }
?>
