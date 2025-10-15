import java.util.Scanner;

/**
 * Clase que invierte un nombre introducido por el usuario y lo muestra por consola.
 * <p>
 * Este programa utiliza la clase {@link Scanner} para leer la entrada del usuario
 * y una función auxiliar para invertir el texto.
 * </p>
 *
 * @author Víctor Gómez Tejada
 * @version 1.0
 */
public class NombreInvertido {

    /**
     * Método principal que solicita un nombre por consola y muestra su versión invertida.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce tu nombre: ");
        String nombre = scanner.nextLine();

        String nombreInvertido = invertirTexto(nombre);
        System.out.println("Tu nombre al revés es: " + nombreInvertido);

        scanner.close();
    }

    /**
     * Invierte el texto recibido como parámetro.
     *
     * @param texto El texto que se desea invertir.
     * @return Una cadena con los caracteres del texto original en orden inverso.
     */
    public static String invertirTexto(String texto) {
        return new StringBuilder(texto).reverse().toString();
    }
}