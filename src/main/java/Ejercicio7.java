import com.google.gson.Gson;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

class Ubicacion {
    int pasillo;
    String estante;
}

class Producto {
    String id;
    String nombre;
    int stock;
    double precio;
    List<String> tags;
    Ubicacion ubicacion;
}

class InventarioData {
    String almacen;
    String actualizado;
    List<Producto> productos;
}

public class Ejercicio7 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             FileReader reader = new FileReader("inventario.json")) {

            InventarioData data = new Gson().fromJson(reader, InventarioData.class);

            System.out.print("Introduce el ID o nombre del producto (ej. PR-100, RAM 16GB DDR4): ");
            String busqueda = sc.nextLine().trim();

            Producto encontrado = null;
            for (Producto p : data.productos) {
                if (p.id.equalsIgnoreCase(busqueda) || p.nombre.equalsIgnoreCase(busqueda)) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null && encontrado.ubicacion != null) {
                System.out.printf("Producto: %s (%s)%n", encontrado.nombre, encontrado.id);
                System.out.printf("Ubicación -> Pasillo: %d | Estante: %s%n",
                        encontrado.ubicacion.pasillo, encontrado.ubicacion.estante);
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}