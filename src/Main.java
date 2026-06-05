import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Doctor> doctores = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Cita> citas = new ArrayList<>();

    public static void main(String[] args) {
        verificarYGenerarBaseDatos();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE CLÍNICA ---");
            System.out.println("1. Registrar Doctor");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Agendar Cita");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.print("ID del Doctor: ");
                    String idDoc = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nomDoc = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edadDoc = Integer.parseInt(scanner.nextLine());
                    System.out.print("Especialidad: ");
                    String especialidad = scanner.nextLine();

                    Doctor nuevoDoctor = new Doctor(idDoc, nomDoc, edadDoc, especialidad);
                    doctores.add(nuevoDoctor);
                    guardarEnArchivo("db/doctores.csv", nuevoDoctor.toString());
                    System.out.println("Doctor registrado con éxito.");
                    break;

                case 2:
                    System.out.print("ID del Paciente: ");
                    String idPac = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nomPac = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edadPac = Integer.parseInt(scanner.nextLine());
                    System.out.print("Síntomas: ");
                    String sintomas = scanner.nextLine();

                    Paciente nuevoPaciente = new Paciente(idPac, nomPac, edadPac, sintomas);
                    pacientes.add(nuevoPaciente);
                    guardarEnArchivo("db/pacientes.csv", nuevoPaciente.toString());
                    System.out.println("Paciente registrado con éxito.");
                    break;

                case 3:
                    if (doctores.isEmpty() || pacientes.isEmpty()) {
                        System.out.println("Error: Debes tener al menos 1 Doctor y 1 Paciente registrados para agendar una cita.");
                        break;
                    }
                    System.out.print("ID de la Cita: ");
                    String idCita = scanner.nextLine();
                    System.out.print("Fecha (DD/MM/AAAA): ");
                    String fecha = scanner.nextLine();
                    System.out.print("Hora (HH:MM): ");
                    String hora = scanner.nextLine();

                    // Para simplificar, tomamos el primer doctor y paciente de la lista.
                    // En un sistema avanzado se buscarían por ID.
                    Doctor docAsignado = doctores.get(0);
                    Paciente pacAsignado = pacientes.get(0);

                    Cita nuevaCita = new Cita(idCita, fecha, hora, docAsignado, pacAsignado);
                    citas.add(nuevaCita);
                    nuevaCita.agendarCita();
                    guardarEnArchivo("db/citas.csv", nuevaCita.toString());
                    break;

                case 4:
                    System.out.println("Cerrando el sistema. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    // Método para validar la creación de la base de datos
    public static void verificarYGenerarBaseDatos() {
        File directorioDB = new File("db");
        if (!directorioDB.exists()) {
            directorioDB.mkdir();
            System.out.println("Carpeta 'db' generada.");
        }

        String[] archivos = {"doctores.csv", "pacientes.csv", "citas.csv"};
        for (String nombreArchivo : archivos) {
            File archivo = new File(directorioDB, nombreArchivo);
            if (!archivo.exists()) {
                try {
                    archivo.createNewFile();
                } catch (IOException e) {
                    System.out.println("Error al crear " + nombreArchivo + ": " + e.getMessage());
                }
            }
        }
    }

    // Método reutilizable para guardar datos en los CSV
    private static void guardarEnArchivo(String ruta, String datos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(datos);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }
}