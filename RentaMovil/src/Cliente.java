import java.util.ArrayList;
public abstract class Cliente {
    private String identificador;
    private String nombre;
    private ArrayList<String> licencias;
    private int alquileresActivos;

    public Cliente(String identificador, String nombre,
                   ArrayList<String> licencias) {

        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El identificador no puede estar vacio.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacio.");
        }

        if (licencias == null || licencias.isEmpty()) {
            throw new IllegalArgumentException(
                    "El cliente debe presentar al menos una licencia.");
        }

        for (String licencia : licencias) {
            if (!licenciaValida(licencia)) {
                throw new IllegalArgumentException(
                        "Tipo de licencia invalido: " + licencia);
            }
        }

        this.identificador = identificador.trim();
        this.nombre = nombre.trim();
        this.licencias = new ArrayList<>();

        for (String licencia : licencias) {
            String licenciaMayuscula = licencia.toUpperCase();

            if (!this.licencias.contains(licenciaMayuscula)) {
                this.licencias.add(licenciaMayuscula);
            }
        }

        this.alquileresActivos = 0;
    }

    private boolean licenciaValida(String licencia) {

        if (licencia == null) {
            return false;
        }

        String l = licencia.toUpperCase();

        return l.equals("A")
                || l.equals("B")
                || l.equals("C")
                || l.equals("M");
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getLicencias() {
        return new ArrayList<>(licencias);
    }

    public int getAlquileresActivos() {
        return alquileresActivos;
    }

    public void incrementarAlquileresActivos() {

        if (!puedeAlquilar()) {
            throw new IllegalStateException(
                    "El cliente alcanzo su limite de alquileres activos.");
        }

        alquileresActivos++;
    }

    public void disminuirAlquileresActivos() {

        if (alquileresActivos <= 0) {
            throw new IllegalStateException(
                    "El cliente no tiene alquileres activos.");
        }

        alquileresActivos--;
    }

    public boolean puedeAlquilar() {
        return alquileresActivos < getLimiteAlquileres();
    }

    public abstract double calcularDescuento(double subtotal);

    public abstract int getLimiteAlquileres();

    public abstract void registrarAlquilerConfirmado();

    public abstract String getDescripcion();

    @Override
    public String toString() {
        return "ID: " + identificador
                + " | Nombre: " + nombre
                + " | Licencias: " + licencias
                + " | Alquileres activos: " + alquileresActivos
                + " | " + getDescripcion();
    }
}