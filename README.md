# Estructuras de datos en las elecciones del aula

## Enunciado

Se elige delegado de aula. Asisten alumnos a clase. Durante el proceso llegan alumnos tarde y otros se marchan; solo pueden proponerse y votar quienes están presentes en ese momento. De entre los presentes se proponen candidatos. Mientras el plazo de candidaturas está abierto, un candidato puede retirarse y pueden proponerse otros; al cerrar el plazo, los candidatos quedan fijados. Después el profesor elige un secretario. El profesor es el presidente: abre y cierra la votación, recoge las papeletas, anota el recuento en la pizarra y proclama el resultado. Cada alumno vota una vez en papel. El presidente deja las papeletas recogidas una sobre otra, formando un montón. El secretario toma las papeletas una a una y canta cada voto en voz alta. Una papeleta sin nombre cuenta como voto en blanco y una con un nombre que no es candidato cuenta como nulo. El presidente escribe en la pizarra el nombre de cada candidato, más las opciones en blanco y nulo, y añade un palito en su columna por cada voto cantado. Si se equivoca al anotar, borra el palito.

Indique qué estructura de datos (array, lista, pila o cola) emplearía para cada dato del proceso. Para cada dato, indique la estructura elegida, la propiedad que la justifica, la alternativa que descarta y la limitación que asume.

## Solución A: papeletas como cola

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
|-|-|-|-|-|
|Alumnos presentes en el aula | Lista |<sub>La operación es comprobar que quien se propone o entrega papeleta consta como presente.<br>El tamaño no se conoce de antemano y cambia: llegan alumnos tarde y otros se marchan.<br>La lista admite altas y bajas en cualquier posición. | <sub>Un array de matriculados con un indicador de presencia también serviría; se descarta porque la lista refleja directamente quién está en el aula, sin arrastrar a los ausentes. | <sub>Comprobar la presencia exige recorrer la lista (coste lineal), aceptable con el tamaño de un aula. |
| Candidaturas mientras el plazo está abierto | Lista, que pasa a array al cerrar el plazo | <sub>Se proponen alumnos de entre los presentes y pueden retirarse o añadirse otros, de modo que el tamaño varía.<br>Al cerrar el plazo el número queda fijo y el requisito pasa de tamaño variable a tamaño fijo. | <sub>Un array desde el inicio: el número final de candidatos no se conoce hasta cerrar el plazo. | <sub>Hay que copiar los elementos al array en el momento del cierre. |
| Papeletas recogidas pendientes de lectura | Cola | <sub>El secretario las toma de una en una sin elegir cuál, así que solo se usa un extremo del conjunto y el acceso a posiciones arbitrarias sobra.<br>El orden de lectura no altera el recuento, así que el requisito no distingue entre cola y pila.<br>Se elige la cola para que la lectura respete el orden de recogida, lo que facilita reconstruir el proceso si hay que revisarlo. | <sub>Una pila daría el mismo recuento, pero leería primero la última papeleta recogida, de modo que la lectura no seguiría el orden del proceso. Un array o una lista ofrecen acceso a cualquier posición, capacidad que el procedimiento no usa. | <sub>Una papeleta leída sale de la cola, de modo que revisar una papeleta discutida o la anterior exige conservar las leídas en otro lugar. |
| Nombres de candidatos (más blanco y nulo) y votos de cada opción | Dos arrays paralelos: uno de cadenas con los nombres y otro de enteros donde la posición `i` acumula los votos de la opción `i` | <sub>El tamaño es fijo desde el cierre del plazo y no hay inserciones ni borrados.<br>Incrementar una posición ya conocida cuesta tiempo constante, con tres o con treinta candidatos.<br>Corregir un error es decrementar el contador de esa posición. | <sub>Una lista añadiría una flexibilidad que ya no se necesita. Una pila o una cola no permiten llegar a un candidato concreto sin extraer los anteriores. | <sub>El secretario canta el nombre, no el índice, y localizar la posición recorriendo el array de nombres es lineal. Es aceptable con pocos candidatos. Una estructura que asocie nombre y contador lo evitaría, aunque no se haya estudiado. |

> La estructura sigue a la propiedad del dato: tamaño variable mientras el conjunto está abierto (listas), acceso secuencial por un extremo con orden de recogida para las papeletas (cola) y tamaño fijo con acceso por posición para el recuento (arrays). El paso de lista a array al cerrar las candidaturas refleja que el requisito del dato cambió de variable a fijo.

<div align=center>

|Implementación: [solucionA](elecciones/solucionA/)||
|-|-|
|[Ejemplo.java](elecciones/solucionA/Ejemplo.java)|Desarrollo del proceso: presentes, candidaturas, votación, lectura y proclamación|
|[Queue.java](elecciones/solucionA/Queue.java)|Papeletas pendientes de lectura|
|[Pizarra.java](elecciones/solucionA/Pizarra.java)|Arrays paralelos de nombres y votos|
|[List.java](elecciones/solucionA/List.java)|Presentes y candidaturas|
|[Node.java](elecciones/solucionA/Node.java)|Nodo de la lista y de la cola|

</div>

## Solución B: papeletas como pila

| Dato | Decisión | Justificación | Alternativa descartada | Limitación asumida |
|-|-|-|-|-|
| Alumnos presentes en el aula | Lista | <sub>La operación es comprobar que quien se propone o entrega papeleta consta como presente.<br>El tamaño no se conoce de antemano y cambia: llegan alumnos tarde y otros se marchan.<br>La lista admite altas y bajas en cualquier posición. | <sub>Un array de matriculados con un indicador de presencia también serviría; se descarta porque la lista refleja directamente quién está en el aula, sin arrastrar a los ausentes. | <sub>Comprobar la presencia exige recorrer la lista (coste lineal), aceptable con el tamaño de un aula. |
| Candidaturas mientras el plazo está abierto | Lista, que pasa a array al cerrar el plazo | <sub>Se proponen alumnos de entre los presentes y pueden retirarse o añadirse otros, de modo que el tamaño varía.<br>Al cerrar el plazo el número queda fijo y el requisito pasa de tamaño variable a tamaño fijo. | <sub>Un array desde el inicio: el número final de candidatos no se conoce hasta cerrar el plazo. | <sub>Hay que copiar los elementos al array en el momento del cierre. |
| Papeletas recogidas pendientes de lectura | Pila | <sub>El presidente las va dejando una sobre otra y el secretario toma siempre la de encima, así que solo se usa un extremo del conjunto y el acceso a posiciones arbitrarias sobra.<br>La pila modela ese montón sin necesidad de gestionar el orden de recogida (último en entrar, primero en salir). | <sub>Una cola daría el mismo recuento, porque el orden de lectura no altera el resultado, pero obligaría a tomar siempre la de abajo del montón para respetar el orden de recogida, un esfuerzo que no aporta nada al resultado. Un array o una lista ofrecen acceso a cualquier posición, capacidad que el procedimiento no usa. | <sub>La lectura invierte el orden de recogida y una papeleta leída sale de la pila, de modo que revisar una papeleta discutida exige conservar las leídas en otro lugar. |
| Nombres de candidatos (más blanco y nulo) y votos de cada opción | Dos arrays paralelos: uno de cadenas con los nombres y otro de enteros donde la posición `i` acumula los votos de la opción `i` | <sub>El tamaño es fijo desde el cierre del plazo y no hay inserciones ni borrados.<br>Incrementar una posición ya conocida cuesta tiempo constante, con tres o con treinta candidatos.<br>Corregir un error es decrementar el contador de esa posición. | <sub>Una lista añadiría una flexibilidad que ya no se necesita. Una pila o una cola no permiten llegar a un candidato concreto sin extraer los anteriores. | <sub>El secretario canta el nombre, no el índice, y localizar la posición recorriendo el array de nombres es lineal. Es aceptable con pocos candidatos. Una estructura que asocie nombre y contador lo evitaría, aunque no se haya estudiado. |

> La estructura sigue a la propiedad del dato: tamaño variable mientras el conjunto está abierto (listas), acceso secuencial por un extremo reflejando el montón físico para las papeletas (pila) y tamaño fijo con acceso por posición para el recuento (arrays). El paso de lista a array al cerrar las candidaturas refleja que el requisito del dato cambió de variable a fijo.

<div align=center>

|Implementación: [solucionB](elecciones/solucionB/)||
|-|-|
|[Ejemplo.java](elecciones/solucionB/Ejemplo.java)|Desarrollo del proceso: presentes, candidaturas, votación, lectura y proclamación|
|[Stack.java](elecciones/solucionB/Stack.java)|Papeletas pendientes de lectura|
|[Pizarra.java](elecciones/solucionB/Pizarra.java)|Arrays paralelos de nombres y votos|
|[List.java](elecciones/solucionB/List.java)|Presentes y candidaturas|
|[Node.java](elecciones/solucionB/Node.java)|Nodo de la lista y de la pila|

</div>

## Ejecución

Desde la raíz del repositorio:

```
javac -encoding UTF-8 elecciones/solucionA/*.java
java elecciones.solucionA.Ejemplo

javac -encoding UTF-8 elecciones/solucionB/*.java
java elecciones.solucionB.Ejemplo
```

Ambas soluciones proclaman el mismo resultado, aunque leen las papeletas en orden distinto: el orden de lectura no altera el recuento.