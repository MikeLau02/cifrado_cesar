import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Clase principal para implementar el menú interactivo del usuario.
 * Permite cifrar y descifrar archivos de texto utilizando el cifrado César.
 * También gestiona el manejo de archivos y propiedades asociadas.
 *
 *  @author laura_gonzalez
 * @version 1.0
 * @since 2024
 */
public class Menu {
    private final FileHandler fileHandler;
    private final CesarCipher cipher;
    private final Scanner scanner;

    /**
     * Constructor que inicializa los componentes del menú.
     * @throws IOException Sí ocurre un error al inicializar los componentes.
     */
    public Menu() throws IOException {
        this.fileHandler = new FileHandler();
        this.cipher = new CesarCipher();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Método principal que gestiona la interacción del usuario a través del menú.
     * El programa seguirá ejecutándose hasta que el usuario seleccione la opción "Salir".
     */
    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Cifrar archivo");
            System.out.println("2. Desencriptar archivo");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> cifrarArchivo();
                    case 2 -> desencriptarArchivo();
                    case 3 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = -1;
            }
        } while (opcion != 3);
    }

    /**
     * Cifra el contenido de un archivo .txt utilizando el cifrado César.
     *
     * El usuario debe especificar el nombre del archivo a cifrar y proporcionar
     * un desplazamiento para realizar el cifrado. El archivo cifrado se guardará
     * con el nombre predeterminado 'archivoEncriptado.txt'.
     *
     * @see CesarCipher
     */
    private void cifrarArchivo() {
        try {
            System.out.print("Ingrese el nombre del archivo a cifrar (.txt): ");
            String fileName = scanner.nextLine();

            if (!fileHandler.fileExists(fileName)) {
                System.out.println("El archivo no existe.");
                return;
            }

            String content = fileHandler.readFile(fileName);
            System.out.print("Ingrese el desplazamiento para cifrar: ");
            int shift = Integer.parseInt(scanner.nextLine());

            String encryptedContent = cipher.encrypt(content, shift);

            String encryptedFileName = "archivoEncriptado.txt";
            fileHandler.writeToFile(encryptedFileName, encryptedContent);

            fileHandler.saveProperties("propiedades.properties", fileName, encryptedFileName, shift);

            System.out.println("Archivo cifrado guardado como 'archivoEncriptado.txt'.");
        } catch (IOException e) {
            System.out.println("Error al manejar archivos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: El desplazamiento debe ser un número entero.");
        }
    }

    /**
     /**
     * Descifra un archivo previamente cifrado utilizando las propiedades almacenadas.
     *
     * El archivo descifrado se guardará con el nombre predeterminado 'archivoDesencriptado.txt'.
     * Este método utiliza el desplazamiento y los nombres de archivo originales almacenados
     * en un archivo de propiedades para realizar la operación de desencriptado.
     *
     * @see CesarCipher
     */

    private void desencriptarArchivo() {
        try {
            System.out.print("Ingrese el nombre del archivo a desencriptar (.txt): ");
            String encryptedFile = scanner.nextLine();

            if (!fileHandler.fileExists(encryptedFile)) {
                System.out.println("El archivo especificado no existe.");
                return;
            }

            Properties props = fileHandler.loadProperties("propiedades.properties");
            int decryptionShift = Integer.parseInt(props.getProperty("desplazamiento"));

            String encryptedContent = fileHandler.readFile(encryptedFile);
            String decryptedText = cipher.decrypt(encryptedContent, decryptionShift);

            fileHandler.writeToFile("archivoDesencriptado.txt", decryptedText);

            System.out.println("Archivo desencriptado y guardado como 'archivoDesencriptado.txt'.");
        } catch (IOException e) {
            System.out.println("Error al manejar archivos: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Las propiedades del archivo son inválidas.");
        }
    }
}