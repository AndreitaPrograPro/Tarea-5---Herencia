import java.util.Scanner;

public class Vista {

    private Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }
    public void mostrarMenu() {

        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("          RENTAMOVIL");
        System.out.println("---------------------------------");
        System.out.println("1. Registrar vehiculo");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Consultar flota");
        System.out.println("4. Consultar clientes");
        System.out.println("5. Cotizar alquiler");
        System.out.println("6. Confirmar alquiler");
        System.out.println("7. Registrar devolucion");
        System.out.println("8. Finalizar mantenimiento");
        System.out.println("9. Reportes");
        System.out.println("0. Salir");
        System.out.println("---------------------------------");
    }

    public int leerEntero(String mensaje) {
        while (true) {

            System.out.print(mensaje);

            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: debe ingresar un numero entero.");
            }
        }
    }

    public double leerDouble(String mensaje) {
        while (true) {

            System.out.print(mensaje);

            String entrada = scanner.nextLine().trim();

            try {
                return Double.parseDouble(entrada);

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: debe ingresar un numero valido.");
            }
        }
    }

    public String leerString(String mensaje) {
        while (true) {

            System.out.print(mensaje);

            String entrada = scanner.nextLine().trim();

            if (!entrada.isEmpty()) {
                return entrada;
            }

            System.out.println(
                    "Error: el valor no puede estar vacio.");
        }
    }

    public boolean leerBoolean(String mensaje) {
        while (true) {

            String respuesta =
                    leerString(mensaje + " (S/N): ");

            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println(
                    "Error: ingrese S para si o N para no.");
        }
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public boolean confirmar(String mensaje) {
        return leerBoolean(mensaje);
    }
}