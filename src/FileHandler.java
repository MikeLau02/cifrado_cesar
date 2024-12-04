import java.io.*;
import java.util.Properties;


public class FileHandler {

    /**
     * Escribe texto en un archivo. Si el archivo no existe, se crea.
     * @param fileName : nombre del archivo donde se escribirá el texto
     * @param content : texto a escribir en el archivo.
     * @throws IOException : Si ocurre un error al escribir el archivo
     */

    public void writeToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }

    /**
     * Lee el contenido de un archivo, lo devuelve como una cadena.
     * @param fileName :
     * @return Devuelve el contenido c
     */

    public String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }

    public boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public void saveProperties(String fileName, String originalFile, String encryptedFile, int shift) throws IOException {
        Properties props = new Properties();
        props.setProperty("desplazamiento", String.valueOf(shift));
        props.setProperty("nombreArchivoOriginal", originalFile);
        props.setProperty("nombreArchivoEncriptado", encryptedFile);

        try (FileOutputStream out = new FileOutputStream(fileName)) {
            props.store(out, "Propiedades del cifrado");
        }
    }

    public Properties loadProperties(String fileName) throws IOException {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
        }
        return props;
    }
}
