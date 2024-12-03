import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            Menu menu = new Menu();
            menu.iniciarMenu();
        } catch (IOException e){
            System.out.println("Error al iniciar el programa: "+ e.getMessage());
        }
    }
}
