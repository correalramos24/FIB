## Descripción del Algoritmo avanzado

En el algoritmo mejorado se ha aplicado una heurística del tablero actual en función del número de jugadas posibles del rey contrario. Se aplica la misma heurística pero simulando todos los movimientos posibles que tiene el jugador en esa instancia. El diferencial de los dos valores obtenidos se ordenan en un vector empezando por los movimientos que han reducido la mayor cantidad de movimientos posibles del rey contrario y se hace el mimimax siguiendo este orden. Por lo tanto se comprobarán si tienen solución primero las jugadas que hayan restringido lo máximo posible los movimientos del rey contrario.

