import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Menu {
    private final FileHandler fileHandler;
    private final CesarCipher cipher;
    private final Scanner scanner;

    public Menu() throws IOException {
        this.fileHandler = new FileHandler();
        this.cipher = new CesarCipher();
        this.scanner = new Scanner(System.in);
    }

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
