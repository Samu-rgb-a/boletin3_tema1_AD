import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Empleado {
    private String id;
    private String nombre;
    private String departamento;
    private double salario;

    public Empleado(String id, String nombre, String departamento, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }
}

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            File file = new File("empleados.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("empleado");
            List<Empleado> empleados = new ArrayList<>();

            for (int i = 0; i < lista.getLength(); i++) {
                Element el = (Element) lista.item(i);
                String id = el.getAttribute("id");
                String nombre = el.getElementsByTagName("nombre").item(0).getTextContent();
                String depto = el.getElementsByTagName("departamento").item(0).getTextContent();
                double salario = Double.parseDouble(el.getElementsByTagName("salario").item(0).getTextContent());

                empleados.add(new Empleado(id, nombre, depto, salario));
            }

            Map<String, List<Double>> stats = new HashMap<>();
            for (Empleado e : empleados) {
                stats.putIfAbsent(e.getDepartamento(), new ArrayList<>());
                stats.get(e.getDepartamento()).add(e.getSalario());
            }

            System.out.printf("%-15s | %-12s | %-15s%n", "Departamento", "N° empleados", "Salario medio");
            System.out.println("--------------------------------------------------");
            for (Map.Entry<String, List<Double>> entry : stats.entrySet()) {
                String depto = entry.getKey();
                int totalEmp = entry.getValue().size();
                double media = entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                System.out.printf("%-15s | %-12d | %-15.2f €%n", depto, totalEmp, media);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}