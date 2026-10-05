import java.util.ArrayList;

public class Controlador {
    private Vista vista;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Alquiler> alquileres;
    private int siguienteNumeroAlquiler;

    public Controlador(Vista vista) {
        this.vista = vista;
        vehiculos = new ArrayList<>();
        clientes = new ArrayList<>();
        alquileres = new ArrayList<>();
        siguienteNumeroAlquiler = 1;
        //queriamos unos carritos 
        cargarDatosIniciales();
    }
    private void cargarDatosIniciales() {
        ArrayList<String> licencias1 = new ArrayList<>();
        licencias1.add("A");
        licencias1.add("M");

        ArrayList<String> licencias2 = new ArrayList<>();
        licencias2.add("C");

        ArrayList<String> licencias3 = new ArrayList<>();
        licencias3.add("B");

        ArrayList<String> licencias4 = new ArrayList<>();
        licencias4.add("A");

        vehiculos.add(new Automovil("AUTO001","Toyota","Corolla",250,5,false));
        vehiculos.add(new Automovil("AUTO002","Honda","Civic",300,5,true));

        vehiculos.add(new Motocicleta("MOTO001","Honda","CB125",100,125));
        vehiculos.add(new Motocicleta("MOTO002","Yamaha","MT03",175,321));

        vehiculos.add(new CamionetaCarga("CARGA001","Toyota","Hilux",200,1.5));
        vehiculos.add(new CamionetaCarga("CARGA002","Ford","Ranger",250,2.0));

        vehiculos.add(new Microbus("MICRO001","Toyota","Hiace",450,15,true));
        vehiculos.add(new Microbus("MICRO002","Hyundai","H1",400,12,false));

        clientes.add(new ClienteIndividual("1234567890123","Ana",licencias1,3));
        clientes.add(new ClienteIndividual("9876543210987","Carlos",licencias2));

        clientes.add(new ClienteCorporativo("1234567-8","Empresa Uno",licencias3,"Empresa Uno S.A.","Maria Lopez"));
        clientes.add(new ClienteCorporativo("8765432-1","Empresa Dos",licencias4,"Empresa Dos S.A.","Juan Perez"));
    }
    
    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    registrarCliente();
                    break;
                case 3:
                    consultarFlota();
                    break;
                case 4:
                    consultarClientes();
                    break;
                case 5:
                    cotizarAlquiler();
                    break;
                case 6:
                    confirmarAlquiler();
                    break;
                case 7:
                    registrarDevolucion();
                    break;
                case 8:
                    finalizarMantenimiento();
                    break;
                case 9:
                    mostrarReportes();
                    break;
                case 0:
                    vista.mostrarMensaje("Programa finalizado.");
                    break;
                default:
                    vista.mostrarMensaje("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private void registrarVehiculo() {
        vista.mostrarMensaje("-----------------------------------------");
        vista.mostrarMensaje("1. Automovil");
        vista.mostrarMensaje("2. Motocicleta");
        vista.mostrarMensaje("3. Camioneta de carga");
        vista.mostrarMensaje("4. Microbus");
        int tipo = vista.leerEntero("Tipo: ");
        String placa = vista.leerString("Placa: ");
        String marca = vista.leerString("Marca: ");
        String modelo = vista.leerString("Modelo: ");
        double tarifa = vista.leerDouble("Tarifa diaria: ");
        vista.mostrarMensaje("-----------------------------------------");
        Vehiculo vehiculo = null;

        if (tipo == 1) {
            int pasajeros = vista.leerEntero("Cantidad de pasajeros: ");
            boolean automatica = vista.leerBoolean("Transmision automatica");
            vehiculo = new Automovil(placa,marca,modelo,tarifa,pasajeros,automatica);
        } else if (tipo == 2) {
            int cilindraje = vista.leerEntero("Cilindraje: ");
            vehiculo = new Motocicleta(placa,marca,modelo,tarifa,cilindraje);
        } else if (tipo == 3) {
            double capacidad = vista.leerDouble("Capacidad en toneladas: ");
            vehiculo = new CamionetaCarga(placa,marca,modelo,tarifa,capacidad);
        } else if (tipo == 4) {
            int pasajeros = vista.leerEntero("Cantidad de pasajeros: ");
            boolean piloto = vista.leerBoolean("Incluye piloto");
            vehiculo = new Microbus(placa,marca,modelo,tarifa,pasajeros,piloto);
        }

        if (vehiculo != null) {
            vehiculos.add(vehiculo);
            vista.mostrarMensaje("Vehiculo registrado.");
        }
    }

    private void registrarCliente() {
        vista.mostrarMensaje("1. Cliente individual");
        vista.mostrarMensaje("2. Cliente corporativo");
        int tipo = vista.leerEntero("Tipo: ");
        String identificador = vista.leerString("Identificador: ");
        String nombre = vista.leerString("Nombre: ");
        ArrayList<String> licencias = new ArrayList<>();
        int cantidad = vista.leerEntero("Cantidad de licencias: ");

        for (int i = 0; i < cantidad; i++) {
            licencias.add(vista.leerString("Licencia: "));
        }

        if (tipo == 1) {
            Cliente cliente = new ClienteIndividual(identificador,nombre,licencias);
            clientes.add(cliente);
        } else if (tipo == 2) {
            String empresa = vista.leerString("Nombre de empresa: ");
            String contacto = vista.leerString("Nombre de contacto: ");
            Cliente cliente = new ClienteCorporativo(identificador,nombre,licencias,empresa,contacto);
            clientes.add(cliente);
        }

        vista.mostrarMensaje("Cliente registrado.");
    }

    private Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        return null;
    }

    private Cliente buscarCliente(String identificador) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador().equalsIgnoreCase(identificador)) {
                return cliente;
            }
        }
        return null;
    }

    private Alquiler buscarAlquilerActivo(Vehiculo vehiculo) {
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getVehiculo() == vehiculo && alquiler.isActivo()) {
                return alquiler;
            }
        }
        return null;
    }

    private void consultarFlota() {
        for (Vehiculo vehiculo : vehiculos) {
            vista.mostrarMensaje(vehiculo.toString());
        }
    }

    private void consultarClientes() {
        for (Cliente cliente : clientes) {
            vista.mostrarMensaje(cliente.toString());
        }
    }

    private void cotizarAlquiler() {
        String placa = vista.leerString("Placa: ");
        String id = vista.leerString("Cliente: ");
        int dias = vista.leerEntero("Dias: ");
        Vehiculo vehiculo = buscarVehiculo(placa);
        Cliente cliente = buscarCliente(id);

        if (vehiculo == null || cliente == null) {
            vista.mostrarMensaje("Vehiculo o cliente no encontrado.");
            return;
        }

        double subtotal = vehiculo.calcularSubtotal(dias);
        double descuento = cliente.calcularDescuento(subtotal);
        double total = subtotal - descuento;

        vista.mostrarMensaje("Vehiculo: " + vehiculo.getDescripcion());
        vista.mostrarMensaje("Estado: " + vehiculo.getEstado());
        vista.mostrarMensaje("Subtotal: Q" + String.format("%.2f",subtotal));
        vista.mostrarMensaje("Descuento: Q" + String.format("%.2f",descuento));
        vista.mostrarMensaje("Total: Q" + String.format("%.2f",total));

        if (!vehiculo.estaDisponible()) {
            vista.mostrarMensaje("No puede alquilar: vehiculo no disponible.");
        }
        if (!vehiculo.aceptaLicencia(cliente.getLicencias())) {
            vista.mostrarMensaje("No puede alquilar: licencia inadecuada.");
        }
        if (!cliente.puedeAlquilar()) {
            vista.mostrarMensaje("No puede alquilar: limite de alquileres alcanzado.");
        }
    }

    private void confirmarAlquiler() {
        String placa = vista.leerString("Placa: ");
        String id = vista.leerString("Cliente: ");
        int dias = vista.leerEntero("Dias: ");
        Vehiculo vehiculo = buscarVehiculo(placa);
        Cliente cliente = buscarCliente(id);

        if (vehiculo == null || cliente == null) {
            vista.mostrarMensaje("Vehiculo o cliente no encontrado.");
            return;
        }

        if (!vehiculo.estaDisponible() || !vehiculo.aceptaLicencia(cliente.getLicencias()) || !cliente.puedeAlquilar()) {
            vista.mostrarMensaje("No se puede realizar el alquiler.");
            return;
        }

        double subtotal = vehiculo.calcularSubtotal(dias);
        double descuento = cliente.calcularDescuento(subtotal);
        double total = subtotal - descuento;

        vista.mostrarMensaje("Subtotal: Q" + String.format("%.2f",subtotal));
        vista.mostrarMensaje("Descuento: Q" + String.format("%.2f",descuento));
        vista.mostrarMensaje("Total: Q" + String.format("%.2f",total));

        boolean confirmar = vista.confirmar("Confirmar alquiler");

        if (!confirmar) {
            vista.mostrarMensaje("Alquiler cancelado.");
            return;
        }

        Alquiler alquiler = new Alquiler(siguienteNumeroAlquiler,cliente,vehiculo,dias,subtotal,descuento,total);
        alquileres.add(alquiler);
        siguienteNumeroAlquiler++;
        vehiculo.marcarAlquilado();
        cliente.incrementarAlquileresActivos();
        cliente.registrarAlquilerConfirmado();
        vista.mostrarMensaje("Alquiler confirmado.");
    }

    private void registrarDevolucion() {
        String placa = vista.leerString("Placa: ");
        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            vista.mostrarMensaje("Vehiculo no encontrado.");
            return;
        }

        Alquiler alquiler = buscarAlquilerActivo(vehiculo);

        if (alquiler == null) {
            vista.mostrarMensaje("No tiene un alquiler activo.");
            return;
        }

        alquiler.finalizar();
        alquiler.getCliente().disminuirAlquileresActivos();
        vehiculo.registrarDevolucion(alquiler.getDias());
        vista.mostrarMensaje("Devolucion registrada.");
        vista.mostrarMensaje("Estado: " + vehiculo.getEstado());
    }

    private void finalizarMantenimiento() {
        String placa = vista.leerString("Placa: ");
        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            vista.mostrarMensaje("Vehiculo no encontrado.");
            return;
        }

        vehiculo.finalizarMantenimiento();
        vista.mostrarMensaje("Mantenimiento finalizado.");
    }

    private void mostrarReportes() {
        vista.mostrarMensaje("1. Flota");
        vista.mostrarMensaje("2. Ingresos");
        vista.mostrarMensaje("3. Descuentos");
        vista.mostrarMensaje("4. Alquileres activos");
        vista.mostrarMensaje("5. Historial de cliente");
        int opcion = vista.leerEntero("Opcion: ");

        if (opcion == 1) {
            reporteFlota();
        } else if (opcion == 2) {
            reporteIngresos();
        } else if (opcion == 3) {
            reporteDescuentos();
        } else if (opcion == 4) {
            reporteAlquileresActivos();
        } else if (opcion == 5) {
            reporteHistorialCliente();
        }
    }

    private void reporteIngresos() {
        double total = 0;

        for (Alquiler alquiler : alquileres) {
            total += alquiler.getTotal();
        }

        vista.mostrarMensaje("Ingresos totales: Q" + String.format("%.2f",total));
    }

    private void reporteDescuentos() {
        double total = 0;

        for (Alquiler alquiler : alquileres) {
            total += alquiler.getDescuento();
        }

        vista.mostrarMensaje("Descuentos totales: Q" + String.format("%.2f",total));
    }

    private void reporteFlota() {
        int disponibles = 0;
        int alquilados = 0;
        int mantenimiento = 0;

        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getEstado().equals("Disponible")) {
                disponibles++;
            } else if (vehiculo.getEstado().equals("Alquilado")) {
                alquilados++;
            } else if (vehiculo.getEstado().equals("En mantenimiento")) {
                mantenimiento++;
            }
        }

        vista.mostrarMensaje("Total de vehiculos: " + vehiculos.size());
        vista.mostrarMensaje("Disponibles: " + disponibles);
        vista.mostrarMensaje("Alquilados: " + alquilados);
        vista.mostrarMensaje("En mantenimiento: " + mantenimiento);
    }

    private void reporteAlquileresActivos() {
        for (Alquiler alquiler : alquileres) {
            if (alquiler.isActivo()) {
                vista.mostrarMensaje("Alquiler #" + alquiler.getNumero() + " - Vehiculo: " + alquiler.getVehiculo().getPlaca() + " - Cliente: " + alquiler.getCliente().getNombre() + " - Total: Q" + String.format("%.2f",alquiler.getTotal()));
            }
        }
    }
    
    private ArrayList<String> obtenerImpedimentos(Vehiculo vehiculo,Cliente cliente) {
        ArrayList<String> impedimentos = new ArrayList<>();

        if (!vehiculo.estaDisponible()) {
            impedimentos.add("Vehiculo no disponible.");
        }

        if (!vehiculo.aceptaLicencia(cliente.getLicencias())) {
            impedimentos.add("Licencia inadecuada.");
        }

        if (!cliente.puedeAlquilar()) {
            impedimentos.add("Limite de alquileres activos alcanzado.");
        }

        return impedimentos;
    }

    private void reporteHistorialCliente() {
        String id = vista.leerString("Identificador del cliente: ");
        Cliente cliente = buscarCliente(id);

        if (cliente == null) {
            vista.mostrarMensaje("Cliente no encontrado.");
            return;
        }

        double totalPagado = 0;

        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente() == cliente) {
                vista.mostrarMensaje("Alquiler #" + alquiler.getNumero() + " - Vehiculo: " + alquiler.getVehiculo().getPlaca() + " - Total: Q" + String.format("%.2f",alquiler.getTotal()));
                totalPagado += alquiler.getTotal();
            }
        }

        vista.mostrarMensaje("Total pagado: Q" + String.format("%.2f",totalPagado));
    }
}