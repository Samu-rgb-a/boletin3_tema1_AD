import com.google.gson.Gson;
import java.io.FileReader;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Puntuaciones {
    double imdb;
    double rt;
}

class Pelicula {
    String id;
    String titulo;
    String director;
    int estreno;
    int duracionMin;
    List<String> generos;
    Puntuaciones puntuaciones;

    public double getMediaNormalizada() {
        if (puntuaciones == null) return 0.0;
        double rtNorm = puntuaciones.rt / 10.0; // rt está en escala 1-100, se normaliza a 1-10
        return (puntuaciones.imdb + rtNorm) / 2.0;
    }
}

class PeliculasData {
    List<Pelicula> peliculas;
}

public class Ejercicio8 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("peliculas.json")) {
            PeliculasData data = new Gson().fromJson(reader, PeliculasData.class);

            // a) Calcular media y ordenar de forma descendente
            data.peliculas.sort((p1, p2) -> Double.compare(p2.getMediaNormalizada(), p1.getMediaNormalizada()));

            System.out.println("--- a) PELÍCULAS ORDENADAS POR MEDIA DESCENDENTE ---");
            for (Pelicula p : data.peliculas) {
                System.out.printf("%-26s | IMDb: %.1f | RT (norm): %.1f | Media: %.2f%n",
                        p.titulo,
                        p.puntuaciones.imdb,
                        p.puntuaciones.rt / 10.0,
                        p.getMediaNormalizada());
            }

            // b) Géneros únicos ordenados alfabéticamente
            Set<String> generosUnicos = new TreeSet<>();
            for (Pelicula p : data.peliculas) {
                if (p.generos != null) {
                    generosUnicos.addAll(p.generos);
                }
            }

            System.out.println("\n--- b) GÉNEROS DISTINTOS ORDENADOS ALFABÉTICAMENTE ---");
            for (String g : generosUnicos) {
                System.out.println("- " + g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}