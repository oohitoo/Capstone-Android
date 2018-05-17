<?php
    try{
        
        $HOST = "localhost";
        $DB_NAME = "datatest";
        $USER = "root";
        $PASSWORD = "";
        
        $PDO = new PDO("mysql:host=".$HOST.";dbname=".$DB_NAME.";charset=utf8",$USER,$PASSWORD);
        echo "database connecting success<br />\n";
        
    }catch(PDOExeption $erro){
        echo "Erro da : " . $erro->getMessage();
    }
?>
