# TEMA 5. PROCESADOR SEGMENTADO MULTICICLO

Existen ciertos casos donde unificar la segmentación no es lo mas adecuado (difícil implementación, demasiado riesgos estructurales, aritmética que tarda mas de un ciclo).

Para ello se ramifican los caminos de datos, en función de cada instrucción y se utilizan técnicas de transformación de código.

[TOC]



### Instrucciones en coma flotante

![image-20200103214312002](C:\Users\corre\Documents\FIB Q7\AC2\TEO\t51.jpg)

Se usara un banco de registros de coma flotante y @MEM con load y store para pasar datos entre los bancos. ra simboliza el numero de registro del BR Coma flotante y rb el BR normal.

### Segmentación en etapas

