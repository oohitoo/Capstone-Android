<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>NodeMCU and MySQL</title>
</head>

<body>
    <form action="" method="POST">
        <input type="date" name="data">
        <input type="submit" name="submit" value="확인">
    </form>

    <?php
    include('con1.php');
        
    if($_SERVER['REQUEST_METHOD'] == "POST"){
//        echo "<h1> 날짜date: " . $_POST['date']  . "</h1>";
        $dataP = $_POST['data'];
        
//        $dataArray = explode("_", $dataP);     
//        $dataP = $dataArray[0];
//                
//        echo "Data Example(Pesquisa) : " . $dataP
        
        $sql = "SELECT * FROM datadb WHERE date_time LIKE '%". $dataP ."%'";
//        테이블에 날짜 집어 넣기 '%2018-05-02%처럼
    }
    else{
//        echo "<h1>Date 를 선택하지 않음</h1>";
        $dataAtual = date('Y-m-d');
        
        echo "현재 날짜 : ".$dataAtual;
            
        $sql = "SELECT * FROM datadb WHERE date_time LIKE '%". $dataAtual ."%' ";

    }
    
    $stmt = $PDO->prepare($sql);
    $stmt->execute();
    
    echo "<table border=\"1\">";
    
    echo "<tr> <th>Sensor 1</th> <th>Sensor 2</th> <th>Sensor 3</th> <th>Sensor 4</th> <th>Sensor 5</th> <th>Sensor 6</th> <th>Date/Time</th></tr>";
        
        while($linha = $stmt->fetch(PDO::FETCH_OBJ)){
            echo "<tr>";
            echo"<td>". $linha->sensor1 ."</td>";
            echo"<td>". $linha->sensor2  ."</td>";
            echo"<td>". $linha->sensor3  ."</td>";
            echo"<td>". $linha->sensor4 ."</td>";
            echo"<td>". $linha->sensor5  ."</td>";
            echo"<td>". $linha->sensor6  ."</td>";
            echo"<td>". $linha->date_time  ."</td>";
            echo"</tr>";
        }
        
    echo "</table>";
    ?>
        
<!--
    GET용
    <table border="1">
        <tr>
           <th>Senser 1</th>
           <th>Senser 2</th>
           <th>Senser 3</th>
           <th>Data/Hora</th>
        </tr>
    </table>
-->

</body>

</html>
