<?php
function squares($n) {
    $pairs = array();

    $x = (int)sqrt($n) + 1;

    while ( $x-- > sqrt($n / 2) ) {
        $y = sqrt($n - $x * $x);
        while ($x * $x + $y * $y <= $n) {
            if ($y > $x)
                break;
            if ($x * $x + $y * $y == $n)
                array_push($pairs, array($x, $y));
            ++$y;
        }
    }

    echo count($pairs) . PHP_EOL;
}

$data = file("./input.txt");
unset($data[0]);

foreach($data as $n) {
    squares((int)$n);
}

