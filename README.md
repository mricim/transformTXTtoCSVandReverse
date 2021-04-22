# transformTXTtoCSVandReverse
.TXT & .PHP --> .CSV

.CSV --> .TXT & .PHP



## ¿Que hace?
Este java es capaz de seleccionar multiples archivos ".txt" y combinarlos en un ".csv". Tambien es capaz de hacerlo a la inversa. Dejo un ejejmplo:

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

Con el CSV se puede generar los 2 ficheros ".txt" pero ahora los txt tendrian las "keys" que antes no tenian

##PHP
en la misma tirada tambien hace ficheros `.php` en este caso deben tener un formato aquí expuesto:

-File3.php
```
$messages['php_key1']='value1';
$messages['php_key2']='value2';
$messages['php_key3']='value3';
```
Estos valores tambien se expondran en el mismo CSV. Por eso es importante que estas 'key' tengan/contengan la palabra `php`