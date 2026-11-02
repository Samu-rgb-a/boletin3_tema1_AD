import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

class AlumnoSimple {
    String nombre;
    String fechaNacimiento;
}

public class Ejercicio5 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("alumnos.json")) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<AlumnoSimple>>(){}.getType();
            List<AlumnoSimple> alumnos = gson.fromJson(reader, listType);

            System.out.printf("%-20s | %-12s%n", "Nombre", "Fecha Nacimiento");
            System.out.println("-------------------------------------");
            for (AlumnoSimple a : alumnos) {
                System.out.printf("%-20s | %-12s%n", a.nombre, a.fechaNacimiento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}