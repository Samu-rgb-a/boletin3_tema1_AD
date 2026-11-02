import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio3 {
    public static void main(String[] args) {
        try {
            File file = new File("biblioteca.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            NodeList libros = doc.getElementsByTagName("libro");

            System.out.println("--- a) TÍTULO DE TODOS LOS LIBROS ---");
            Map<String, Integer> conteoGeneros = new HashMap<>();

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
                System.out.println("- " + titulo);

                NodeList generos = libro.getElementsByTagName("genero");
                for (int j = 0; j < generos.getLength(); j++) {
                    String genero = generos.item(j).getTextContent();
                    conteoGeneros.put(genero, conteoGeneros.getOrDefault(genero, 0) + 1);
                }
            }

            System.out.println("\n--- b) CONTEO DE LIBROS POR GÉNERO ---");
            for (Map.Entry<String, Integer> entry : conteoGeneros.entrySet()) {
                System.out.printf("%-15s: %d libro(s)%n", entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}