# Transform TXTtoCSV and Reverse
.TXT & .PHP --> .CSV

.CSV --> .TXT & .PHP



## ¿Qué hace?
Esta java es capaz de seleccionar multiples archivos `.txt` y combinarlos en un `.csv`. También es capaz de hacerlo a la inversa. Dejo un ejemplo:

##TXT
-File1.txt
```
key1=>ABC
key2=>BCD
key3=>CDE
```
-File2.txt
```
key1=>123
key3=>234
key4=>345
```
-ouput.csv

| KEY  | File1.txt | File2.txt |
|------|-----------|-----------|
| key1 | ABC       | 123       |
| key2 | BCD       |           |
| key3 | CDE       | 234       |
| key4 |           | 345       |

Con el CSV se puede generar los 2 ficheros ".txt" pero ahora los txt tendrían las "keys" que antes no tenían

##PHP
En la misma tirada también hace ficheros `.php` en este caso deben tener un formato aquí expuesto:

-File3.php
```
$messages['php_key1']='value1';
$messages['php_key2']='value2';
$messages['php_key4']='value3';
```
-File4.php
```
$messages['php_key1']='value4';
$messages['php_key3']='value5';
$messages['php_key4']='value6';
```
Estos valores también se agregan al mismo CSV. Por eso es importante que estas 'key' tengan/contengan la palabra `php` dentro de la `key`.

-ouput.csv

| KEY      | File1.txt | File2.txt | File3.php | File4.php |
|----------|-----------|-----------|-----------|-----------|
| key1     | ABC       | 123       |           |           |
| key2     | BCD       |           |           |           |
| key3     | CDE       | 234       |           |           |
| key4     |           | 345       |           |           |
| php_key1 |           |           | value1    | value4    |
| php_key2 |           |           | value2    |           |
| php_key3 |           |           |           | value5    |
| php_key4 |           |           | value3    | value6    |
