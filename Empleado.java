public class Empleado {
    private String ID;
    private String primerNombre;
    private String apellidoPaterno;
    private double salario;
    private String departamento;

    public Empleado(String ID, String primerNombre, String apellidoPaterno, double salario, String departamento) {
        this.ID = ID;
        this.primerNombre = primerNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.salario = salario;
        this.departamento = departamento;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "ID='" + ID + '\'' +
                ", primerNombre='" + primerNombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", salario=" + salario +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
