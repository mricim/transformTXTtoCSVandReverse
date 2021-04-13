# transformTXTtoCSVandReverse
initial commit

## ¿Que hace?
Este java es capaz de seleccionar multiples archivos ".txt" y combinarlos en un ".csv". Tambien es capaz de hacerlo a la inversa. Dejo un ejejmplo:

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
