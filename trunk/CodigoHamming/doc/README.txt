EQUIPO:

- LA. Emiliano Anastasio Landa
- ISC. Sinesio Ivan Carrillo Heredia




FECHA:

30/03/2014




DESCRIPCI�N DEL PROGRAMA:

Aplicaci�n multiplataforma orientada a la detecci�n de errores de c�digos por medio del c�digo Hamming, en referencia al libro:
"Duck, Michael & Read, Richard (2003). Data Communications and Computer Networks, 2nd. Edition, Prentice Hall, p�gs.86-92"




CONSIDERACIONES T�CNICAS:

Si no se tiene acualizado el JDK en la versi�n 7 (o posterior), ser� necesario hacerlo para su compilaci�n y ejecuci�n.
Puede descargarse la versi�n correspondiente a la de su sistema operativo en el siguiente enlace:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html




COMPILACI�N:

En el IDE NetBeans, una vez agregado el proyecto, y teniendolo seleccionado, en el men� superior, hacer clic en la opci�n "Run" y seleccionar enseguida "Clean And Build Project".




EJECUCI�N:

En el IDE NetBeans, una vez agregado el proyecto, y teniendolo seleccionado, en el men� superior, hacer clic en la opci�n "Run" y seleccionar enseguida "Run Project".



USO DEL PROGRAMA:

1. Inicialmente se muestra en la parte superior 2 botones para seleccionar la cantidad de bits que se desean transmitir. Seleccionar uno, deshabilita el otro. Debajo de estos 2 botones, se encontrar�n los botones que representan los bits para enviar; basta con hacer clic encima de ellos para cambiar sus valores, y definirlos hasta obtener el codigo deseado (el cual se va mostrando a su vez, en la caja de texto "Codigo Original"). Los botones de "Generadora" y "Verificadora" dependen directamente de la cantidad de bits, para mostrar las matrices correspondientes a ser ocupadas para los procesos de Hamming.


2. Al hacer clic en el bot�n "Generar Hamming" ser�n desplegados 2 cuadros de di�logo, uno tras otro. El primero mostrar� la multiplicaci�n del vector orignal (el ingresado por el usuario) por su matriz generadora respectiva. El segundo realiza las ecuaciones de modulo2-addition para la obtenci�n del codeword. Despu�s de esto, ser� desplegado en el cuadro de texto "Codeword" el resultado obtenido (con color negro los bits ingresados, y en gris, los bits generados de paridad), y tambi�n se habilitar� el bot�n "Ver Hamming Corrupto" y el combo "Posici�n de error".

3. Seleccionamos en el combo la posici�n de error que queremos consultar y enseguida hacemos clic en "Ver Hamming Corrupto" para definirlo; en el campo de texto "C�dgio corrupto" se mostrar� el c�digo correspondiente (con color rojo la posici�n erronea).

4. Al hacer clic sobre el bot�n "Detectar Error" ser�n desplegados 3 cuadros de di�logo, uno tras otro. El primero mostrar� la multiplicaci�n de la matriz de verificaci�n por el codeword corrupto. El segundo realiza las ecuaciones de modulo2-addition para la obtenci�n del syndrome. El tercero finalmente informa la posici�n de error calculada, misma que debe coincidir con la que el usuario ingres� en el paso anterior.

NOTAS:
a. En le men� "Archivo", la opci�n "reiniciar" restaurar� los valores de la aplicaci�n, a los que son desplegados originalmente al ser abierta.
b. Si se cambian los valores del vector en los botones de bits de la parte superior, ser�n limpiados los campos de "codeword", "codeword corrupto", y "syndrome"; adem�s se deshabilita el combo de "Posicion de error" y los botones de "Ver Hamming Corrupto" y "Detectar error".