import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

class Nota {
    String asignatura;
    double nota;
}

class Alumno {
    String id;
    String nombre;
    boolean matriculado;
    String fechaNacimiento;
    Nota[] notas;

    public double getMedia() {
        if (notas == null || notas.length == 0) return 0.0;
        double suma = 0;
        for (Nota n : notas) suma += n.nota;
        return suma / notas.length;
    }
}

public class Ejercicio6 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("alumnos.json")) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Alumno>>(){}.getType();
            List<Alumno> alumnos = gson.fromJson(reader, listType);

            // a) Alumno y la asignatura con la nota más alta
            String mejorAlumnoAsig = "";
            String mejorAsignatura = "";
            double maxNotaGlobal = -1;

            // b) Alumno con la nota media más alta
            Alumno mejorAlumnoMedia = null;
            double maxMedia = -1;

            for (Alumno a : alumnos) {
                if (a.notas != null) {
                    for (Nota n : a.notas) {
                        if (n.nota > maxNotaGlobal) {
                            maxNotaGlobal = n.nota;
                            mejorAlumnoAsig = a.nombre;
                            mejorAsignatura = n.asignatura;
                        }
                    }
                }

                double media = a.getMedia();
                if (media > maxMedia) {
                    maxMedia = media;
                    mejorAlumnoMedia = a;
                }
            }

            System.out.println("--- a) Alumno y asignatura con la nota más alta ---");
            System.out.printf("Alumno: %s | Asignatura: %s | Nota: %.2f%n", mejorAlumnoAsig, mejorAsignatura, maxNotaGlobal);

            System.out.println("\n--- b) Alumno con la nota media más alta ---");
            if (mejorAlumnoMedia != null) {
                System.out.printf("Alumno: %s | Nota Media: %.2f%n", mejorAlumnoMedia.nombre, maxMedia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}