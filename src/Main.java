import java.io.IOException;

/**
 * Clase principal que actúa como punto de entrada al programa.
 * Esta clase inicia la ejecución del menú principal y maneja las excepciones
 * relacionadas con la entrada y salida de archivos.
 */

public class Main {
    /**
     * Método principal del programa.
     *
     * Este método inicializa el menú interactivo.
     * @exception : Maneja posibles excepciones que pueden ocurrir
     * durante la lectura o escritura de archivos.
     */
    public static void main(String[] args){
        try {
            Menu menu = new Menu();
            menu.iniciarMenu();
        } catch (IOException e){
            System.out.println("Error al iniciar el programa: "+ e.getMessage());
        }
    }
}