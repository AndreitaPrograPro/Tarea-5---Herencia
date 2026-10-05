import java.util.ArrayList;
public class ClienteIndividual extends Cliente {

    private int alquileresConfirmados;

    public ClienteIndividual(String dpi, String nombre,
                             ArrayList<String> licencias) {

        this(dpi, nombre, licencias, 0);
    }

    public ClienteIndividual(String dpi, String nombre,
                             ArrayList<String> licencias,
                             int alquileresConfirmados) {

        super(validarDPI(dpi), nombre, licencias);

        if (alquileresConfirmados < 0) {
            throw new IllegalArgumentException(
                    "Los alquileres confirmados no pueden ser negativos.");
        }

        this.alquileresConfirmados = alquileresConfirmados;
    }

    private static String validarDPI(String dpi) {

        if (dpi == null || !dpi.matches("\\d{13}")) {
            throw new IllegalArgumentException(
                    "El DPI debe contener exactamente 13 digitos.");
        }

        return dpi;
    }

    public int getAlquileresConfirmados() {
        return alquileresConfirmados;
    }

    @Override
    public double calcularDescuento(double subtotal) {

        if (subtotal < 0) {
            throw new IllegalArgumentException(
                    "El subtotal no puede ser negativo.");
        }

      
        if (alquileresConfirmados >= 3) {
            return subtotal * 0.05;
        }

        return 0;
    }

    @Override
    public int getLimiteAlquileres() {
        return 1;
    }

    @Override
    public void registrarAlquilerConfirmado() {
        alquileresConfirmados++;
    }

    @Override
    public String getDescripcion() {
        return "Cliente individual"
                + " | Alquileres confirmados: "
                + alquileresConfirmados;
    }
}