/**
 * Clase encargada de manejar excepciones que puedan ocurrir durante la ejecución del programa.
 *
 * Proporciona un mecanismo centralizado para capturar y mostrar mensajes de error,
 * facilitando la gestión y depuración de problemas en la aplicación.
 *
 * @author laura_gonzalez
 * @version 1.0
 * @since 2024
 */

public class ExceptionManager {
    /**
     * Maneja excepciones capturadas durante la ejecución.
     *
     * Imprime un mensaje de error en la salida de error estándar
     * con la descripción del problema proporcionada por la excepción.
     *
     * @param e La excepción que se desea manejar.
     */
    public void handleException(Exception e) {
        System.err.println("Ocurrió un error: " + e.getMessage());
    }
}
