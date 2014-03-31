EQUIPO:

- LA. Emiliano Anastasio Landa
- ISC. Sinesio Ivan Carrillo Heredia




FECHA:

30/03/2014




DESCRIPCIÓN DEL PROGRAMA:

Aplicación multiplataforma orientada a la detección de errores de códigos por medio del código Hamming, en referencia al libro:
"Duck, Michael & Read, Richard (2003). Data Communications and Computer Networks, 2nd. Edition, Prentice Hall, págs.86-92"




CONSIDERACIONES TÉCNICAS:

Si no se tiene acualizado el JDK en la versión 7 (o posterior), será necesario hacerlo para su compilación y ejecución.
Puede descargarse la versión correspondiente a la de su sistema operativo en el siguiente enlace:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html




COMPILACIÓN:

En el IDE NetBeans, una vez agregado el proyecto, y teniendolo seleccionado, en el menú superior, hacer clic en la opción "Run" y seleccionar enseguida "Clean And Build Project".




EJECUCIÓN:

En el IDE NetBeans, una vez agregado el proyecto, y teniendolo seleccionado, en el menú superior, hacer clic en la opción "Run" y seleccionar enseguida "Run Project".



USO DEL PROGRAMA:

1. Inicialmente se muestra en la parte superior 2 botones para seleccionar la cantidad de bits que se desean transmitir. Seleccionar uno, deshabilita el otro. Debajo de estos 2 botones, se encontrarán los botones que representan los bits para enviar; basta con hacer clic encima de ellos para cambiar sus valores, y definirlos hasta obtener el codigo deseado (el cual se va mostrando a su vez, en la caja de texto "Codigo Original"). Los botones de "Generadora" y "Verificadora" dependen directamente de la cantidad de bits, para mostrar las matrices correspondientes a ser ocupadas para los procesos de Hamming.


2. Al hacer clic en el botón "Generar Hamming" serán desplegados 2 cuadros de diálogo, uno tras otro. El primero mostrará la multiplicación del vector orignal (el ingresado por el usuario) por su matriz generadora respectiva. El segundo realiza las ecuaciones de modulo2-addition para la obtención del codeword. Después de esto, será desplegado en el cuadro de texto "Codeword" el resultado obtenido (con color negro los bits ingresados, y en gris, los bits generados de paridad), y también se habilitará el botón "Ver Hamming Corrupto" y el combo "Posición de error".

3. Seleccionamos en el combo la posición de error que queremos consultar y enseguida hacemos clic en "Ver Hamming Corrupto" para definirlo; en el campo de texto "Códgio corrupto" se mostrará el código correspondiente (con color rojo la posición erronea).

4. Al hacer clic sobre el botón "Detectar Error" serán desplegados 3 cuadros de diálogo, uno tras otro. El primero mostrará la multiplicación de la matriz de verificación por el codeword corrupto. El segundo realiza las ecuaciones de modulo2-addition para la obtención del syndrome. El tercero finalmente informa la posición de error calculada, misma que debe coincidir con la que el usuario ingresó en el paso anterior.

NOTAS:
a. En le menú "Archivo", la opción "reiniciar" restaurará los valores de la aplicación, a los que son desplegados originalmente al ser abierta.
b. Si se cambian los valores del vector en los botones de bits de la parte superior, serán limpiados los campos de "codeword", "codeword corrupto", y "syndrome"; además se deshabilita el combo de "Posicion de error" y los botones de "Ver Hamming Corrupto" y "Detectar error".