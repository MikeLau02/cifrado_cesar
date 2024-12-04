/**
 * Clase que implementa el Cifrado y Descifrado de César.
 * Utiliza un alfabeto extendido que incluye letras mayúsculas y minúsculas,
 * dígitos y una variedad de símbolos especiales.
 *
 * @author laura_gonzalez
 * @version 1.0
 * @since 2024
 */

public class CesarCipher {

    /**
     * Alfabeto utilizado para el cifrado y descifrado.
     * Contiene letras (mayúsculas y minúsculas), números y caracteres especiales.
     */
    private static final String ALFABETO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;':,./<>? ";

    /**
     * Cifra un texto dado utilizando el cifrado César.
     *
     * Cada carácter del texto se desplaza un número específico de posiciones hacia adelante
     * en el alfabeto definido. Si un carácter no pertenece al alfabeto, se conserva tal cual.
     *
     * @param text El texto que se desea cifrar.
     * @param shift El número de posiciones a desplazar en el alfabeto.
     * @return El texto cifrado como una cadena de caracteres.
     */
    public String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            int index = ALFABETO.indexOf(character);
            if (index != -1) {
                int newIndex = (index + shift) % ALFABETO.length();
                encryptedText.append(ALFABETO.charAt(newIndex));
            } else {
                encryptedText.append(character);
            }
        }

        return encryptedText.toString();
    }

    /**
     * Descifra un texto previamente cifrado con el método de César.
     *
     * Cada carácter del texto se desplaza un número específico de posiciones hacia atrás
     * en el alfabeto definido. Si un carácter no pertenece al alfabeto, se conserva tal cual.
     *
     * @param text El texto cifrado que se desea descifrar.
     * @param shift El número de posiciones a desplazar en el alfabeto para revertir el cifrado.
     * @return El texto descifrado como una cadena de caracteres.
     */
    public String decrypt(String text, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            int index = ALFABETO.indexOf(character);
            if (index != -1) {
                int newIndex = (index - shift + ALFABETO.length()) % ALFABETO.length();
                decryptedText.append(ALFABETO.charAt(newIndex));
            } else {
                decryptedText.append(character);
            }
        }

        return decryptedText.toString();
    }
}
