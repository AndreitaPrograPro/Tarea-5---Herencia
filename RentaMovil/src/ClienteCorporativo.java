import java.util.ArrayList;
public class ClienteCorporativo extends Cliente {

    private String nombreEmpresa;
    private String nombreContacto;

    public ClienteCorporativo(String nit,
                              String nombre,
                              ArrayList<String> licencias,
                              String nombreEmpresa,
                              String nombreContacto) {

        super(nit, nombre, licencias);

        if (nombreEmpresa == null
                || nombreEmpresa.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre de la empresa no puede estar vacio.");
        }

        if (nombreContacto == null
                || nombreContacto.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El nombre del contacto no puede estar vacio.");
        }

        this.nombreEmpresa = nombreEmpresa.trim();
        this.nombreContacto = nombreContacto.trim();
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    @Override
    public double calcularDescuento(double subtotal) {

        if (subtotal < 0) {
            throw new IllegalArgumentException(
                    "El subtotal no puede ser negativo.");
        }

        return subtotal * 0.10;
    }

    @Override
    public int getLimiteAlquileres() {
        return 3;
    }

    @Override
    public void registrarAlquilerConfirmado() {
    }

    @Override
    public String getDescripcion() {
        return "Cliente corporativo"
                + " | Empresa: " + nombreEmpresa
                + " | Contacto: " + nombreContacto;
    }
}