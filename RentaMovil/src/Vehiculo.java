import java.util.ArrayList;
public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private String estado;
    private int diasDesdeMantenimiento;

    public Vehiculo(String placa, String marca, String modelo,
                    double tarifaDiaria) {

        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede estar vacia.");
        }

        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacia.");
        }

        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacio.");
        }

        if (tarifaDiaria <= 0) {
            throw new IllegalArgumentException(
                    "La tarifa diaria debe ser mayor que cero.");
        }

        this.placa = placa.trim();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.tarifaDiaria = tarifaDiaria;

        // Todo vehiculo nuevo inicia disponible.
        this.estado = "Disponible";
        this.diasDesdeMantenimiento = 0;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public String getEstado() {
        return estado;
    }

    public int getDiasDesdeMantenimiento() {
        return diasDesdeMantenimiento;
    }

    public boolean estaDisponible() {
        return estado.equals("Disponible");
    }

    public void marcarAlquilado() {

        if (!estaDisponible()) {
            throw new IllegalStateException(
                    "El vehiculo no se encuentra disponible.");
        }

        estado = "Alquilado";
    }

    public void registrarDevolucion(int dias) {

        if (!estado.equals("Alquilado")) {
            throw new IllegalStateException(
                    "El vehiculo no esta alquilado.");
        }

        if (dias <= 0) {
            throw new IllegalArgumentException(
                    "Los dias deben ser mayores que cero.");
        }

        diasDesdeMantenimiento += dias;

        if (diasDesdeMantenimiento >= getUmbralMantenimiento()) {
            estado = "En mantenimiento";
        } else {
            estado = "Disponible";
        }
    }

    public void finalizarMantenimiento() {

        if (!estado.equals("En mantenimiento")) {
            throw new IllegalStateException(
                    "El vehiculo no se encuentra en mantenimiento.");
        }

        diasDesdeMantenimiento = 0;
        estado = "Disponible";
    }
    public abstract double calcularSubtotal(int dias);

    public abstract boolean aceptaLicencia(ArrayList<String> licencias);

    public abstract int getUmbralMantenimiento();

    public abstract String getDescripcion();
    @Override
    public String toString() {
        return "Placa: " + placa
                + " | Marca: " + marca
                + " | Modelo: " + modelo
                + " | Tarifa diaria: Q"
                + String.format("%.2f", tarifaDiaria)
                + " | Estado: " + estado
                + " | Dias desde mantenimiento: "
                + diasDesdeMantenimiento
                + " | " + getDescripcion();
    }
}