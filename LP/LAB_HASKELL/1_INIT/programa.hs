factorial :: Integer -> Integer
    --aixi s'escriuen comentaris
factorial 0 = 1
factorial n = n * factorial (n-1)


--el tipus en aquesta funcio esta inferint el tipus
doblar x = 2 * x