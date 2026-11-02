import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            File file = new File("pedidos.xml");
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();

            System.out.print("Introduce el ID del pedido (ej. P-1001, P-1002): ");
            String idBuscado = sc.nextLine().trim();

            NodeList listaPedidos = doc.getElementsByTagName("pedido");
            boolean encontrado = false;

            for (int i = 0; i < listaPedidos.getLength(); i++) {
                Element pedido = (Element) listaPedidos.item(i);
                if (pedido.getAttribute("id").equalsIgnoreCase(idBuscado)) {
                    encontrado = true;
                    System.out.println("\n--- Items del Pedido " + idBuscado + " ---");

                    NodeList items = pedido.getElementsByTagName("item");
                    double totalCalculado = 0.0;

                    for (int j = 0; j < items.getLength(); j++) {
                        Element item = (Element) items.item(j);
                        String desc = item.getElementsByTagName("descripcion").item(0).getTextContent();
                        int cant = Integer.parseInt(item.getElementsByTagName("cantidad").item(0).getTextContent());
                        double precio = Double.parseDouble(item.getElementsByTagName("precioUnitario").item(0).getTextContent());

                        double subtotal = cant * precio;
                        totalCalculado += subtotal;

                        System.out.printf(" - %-25s | Cant: %d | Precio: %.2f € | Subtotal: %.2f €%n", desc, cant, precio, subtotal);
                    }

                    double totalXml = Double.parseDouble(pedido.getElementsByTagName("total").item(0).getTextContent());

                    System.out.println("-------------------------------------------------");
                    System.out.printf("Total recalculado: %.2f €%n", totalCalculado);
                    System.out.printf("Total en XML:      %.2f €%n", totalXml);

                    if (Math.abs(totalCalculado - totalXml) < 0.001) {
                        System.out.println("Resultado: Coinciden correctamente.");
                    } else {
                        System.out.println("Resultado: NO coinciden.");
                    }
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró ningún pedido con el ID: " + idBuscado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}