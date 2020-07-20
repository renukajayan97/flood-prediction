<?php 
set_time_limit(0);


$head="RainFall,WaterLevel";
$data="$_POST[rain],$_POST[water]";
/*echo "Headings : ".$head;
echo "<br>";
echo "Datas : ".$data;*/

$myFile = "test.csv";
$fh = fopen($myFile, 'w') or die("can't open file");

$stringData = "$head\n$data";
fwrite($fh, $stringData);
fclose($fh);

$python=`python test.py`;


echo $python

?>

