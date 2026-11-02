import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("empleados.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList listaEmpleados = doc.getElementsByTagName("empleado");

            for (int i = 0; i < listaEmpleados.getLength(); i++) {
                Element emp = (Element) listaEmpleados.item(i);
                String id = emp.getAttribute("id");
                String nombre = emp.getElementsByTagName("nombre").item(0).getTextContent();
                String salario = emp.getElementsByTagName("salario").item(0).getTextContent();

                System.out.printf("ID: %-5s | Nombre: %-15s | Salario: %s €%n", id, nombre, salario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}