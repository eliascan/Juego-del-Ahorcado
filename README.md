# Juego-del-Ahorcado

# DESCRIPCIÓN

El programa primero debe generar una palabra para adivinar al azar.

En el campo de texto, el jugador escribe, uno a la vez, cartas para probar:
Si la carta ya se ha probado, se muestra un mensaje para recordarle ...

Si la letra está presente en la palabra oculta:
Se muestra donde sea;

Si fue la última carta para encontrar, publicamos "¡Bravo! Has adivinado la palabra »

Si la letra no está presente en la palabra oculta:
La pantalla del personaje Hangman progresa

Si hemos agotado todos los 8 errores permitidos, mostramos (showMessageDialog) "Lo siento, todas sus pruebas están agotadas ..." y, a continuación, ¿cuál fue la palabra buscada? Además, en la interfaz, debajo de la palabra oculta, uno muestra "Después de 8 errores, es el final ..."

La carta probada debe agregarse a la lista de los que ya se han probado
