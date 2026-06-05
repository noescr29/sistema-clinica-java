public class Doctor extends Persona {
    private String especialidad;

    public Doctor(String id, String nombre, int edad, String especialidad) {
        super(id, nombre, edad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }

    @Override
    public String toString() {
        return id + "," + nombre + "," + edad + "," + especialidad;
    }
}