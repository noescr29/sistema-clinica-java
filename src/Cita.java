public class Cita implements GestorCitas {
    private String idCita;
    private String fecha;
    private String hora;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String idCita, String fecha, String hora, Doctor doctor, Paciente paciente) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public void agendarCita() {
        System.out.println("Cita agendada correctamente para el paciente: " + paciente.getNombre() + " con el Dr. " + doctor.getNombre());
    }

    @Override
    public void cancelarCita() {
        System.out.println("Cita cancelada.");
    }

    @Override
    public String toString() {
        return idCita + "," + fecha + "," + hora + "," + doctor.getId() + "," + paciente.getId();
    }
}