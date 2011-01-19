<?php

$f = fopen("../data/gen.txt", "w");

if (!is_resource($f)) {
    die('asd');
}

for ($i=0; $i<=100000; $i++) {
    fputs($f, "$i ".rand(0,10000).".".rand(0,200)." ".rand(0,10000).".".rand(0,200).PHP_EOL);
}

fclose($f);



