<?php

    $lok2 ='10.021305';
    $lok1 = '76.307290';

$url = 'http://maps.google.com/maps/geo?q='.$lok2.','.$lok1.'&output=json';
    $data = @file_get_contents($url);
    $jsondata = json_decode($data,true);
    echo $jsondata;

    if(is_array($jsondata )&& $jsondata ['Status']['code']==200)
    {
          $addr = $jsondata ['Placemark'][0]['AddressDetails']['Country']['CountryName'];
          $addr2 = $jsondata ['Placemark'][0]['AddressDetails']['Country']['Locality']['LocalityName'];
          $addr3 = $jsondata ['Placemark'][0]['AddressDetails']['Country']['Locality']['DependentLocality']['DependentLocalityName'];
    }
    echo "Country: " . $addr . " | Region: " . $addr2 . " | City: " . $addr3;

?>