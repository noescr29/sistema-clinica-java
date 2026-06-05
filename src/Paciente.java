public class Paciente extends Persona {
    private String sintomas;

    public Paciente(String id, String nombre, int edad, String sintomas) {
        super(id, nombre, edad);
        this.sintomas = sintomas;
    }

    public String getSintomas() { return sintomas; }

    @Override
    public String toString() {
        return id + "," + nombre + "," + edad + "," + sintomas;
    }
}