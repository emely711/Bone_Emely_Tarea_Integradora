package modelo;

/**
 * Subclase Administrativo - hereda de Empleado.
 * Pago = horasTrabajadas × valorHora
 */
public class Administrativo extends Empleado {

    private String departamento;
    private double horasTrabajadas;
    private double valorHora;

    // Constructor
    public Administrativo(String cedula, String nombre, int edad, String telefono, String correo,
                          String departamento, double horasTrabajadas, double valorHora) {
        super(cedula, nombre, edad, telefono, correo);
        this.departamento    = departamento;
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora       = valorHora;
    }

    // Getters y Setters
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public double getHorasTrabajadas() { return horasTrabajadas; }
    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { this.valorHora = valorHora; }

    // Implementación del método abstracto - Polimorfismo
    @Override
    public double calcularPago() {
        return horasTrabajadas * valorHora;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("==============================");
        System.out.println("  TIPO          : ADMINISTRATIVO");
        System.out.println("  Cédula        : " + getCedula());
        System.out.println("  Nombre        : " + getNombre());
        System.out.println("  Edad          : " + getEdad());
        System.out.println("  Teléfono      : " + getTelefono());
        System.out.println("  Correo        : " + getCorreo());
        System.out.println("  Departamento  : " + departamento);
        System.out.println("  Horas Trabaj. : " + horasTrabajadas);
        System.out.println("  Valor/Hora    : $" + valorHora);
        System.out.println("  PAGO TOTAL    : $" + calcularPago());
        System.out.println("==============================");
    }
}
