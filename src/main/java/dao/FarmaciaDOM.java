package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import modelo.Farmacia;
import modelo.Medicamento;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class FarmaciaDOM{

	private static final String DOM1_XML_FILE = "xml/MedicamentosDOM.xml";

	/**
	 * Lee los medicamentos de la farmacia de un fichero xml
	 * mediante DOM
	 * @param farmacia
	 * @return
	 */
	public boolean leer(Path farmaciaXML) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(farmaciaXML.toFile());
			document.getDocumentElement().normalize();
			// Obtenemos la lista de nodos con nombre empleado de todo el documento
			NodeList medicamento = document.getElementsByTagName("medicamento");
			for (int i = 0; i < medicamento.getLength(); i++) {
				Node medi = medicamento.item(i); // obtener un nodo
				if (medi.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) medi; // tipo de nodo
					System.out.println("Nombre Medicamento: " + getNodo("Nombremedicamento", elemento));
					System.out.println("Precio: " + getNodo("Precio", elemento));
					System.out.println("Stock: " + getNodo("Stock", elemento));
					System.out.println("Stock Maximo: " + getNodo("StockMaximo", elemento));
					System.out.println("Stock Minimo: " + getNodo("StockMinimo", elemento));
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}


		return false;
		
	}

	public static String getNodo(String etiqueta, Element elem) {
		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valornodo = (Node) nodo.item(0);
		return valornodo.getNodeValue(); // devuelve el valor del nodo
	}

	/**
	 * Guarda los medicamentos de la farmacia en un fichero XML 
	 * mediamente DOM
	 * @param farmacia
	 * @return
	 */
	static void CrearElemento(String datomedicamento, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datomedicamento);
		Text text = document.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
	public boolean guardar(Farmacia farmacia) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Medicamento", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro XML
			MedicamentoAleatorioTest m1=new MedicamentoAleatorioTest();
			ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>(farmacia.leerTodos());

			for (int i = 0; i < medicamentos.size(); i++) {
				Element raiz = document.createElement("medicamento");
				document.getDocumentElement().appendChild(raiz);
				CrearElemento("Nombremedicamento",medicamentos.get(i).getNombre().trim().replaceAll("\u0000", ""),raiz,document);
				CrearElemento("Precio",String.valueOf(medicamentos.get(i).getPrecio()),raiz,document);
				CrearElemento("Stock",String.valueOf(medicamentos.get(i).getStock()),raiz,document);
				CrearElemento("StockMaximo",String.valueOf(medicamentos.get(i).getStockMaximo()),raiz,document);
				CrearElemento("StockMinimo",String.valueOf(medicamentos.get(i).getStockMinimo()),raiz,document);
				// Creamos la fuente XML a partir del documento
				Source source = new DOMSource(document);
				// Creamos el resultado en el fichero Empleados.xml
				Result result = new StreamResult(new java.io.File(DOM1_XML_FILE));
				// Obtenemos un TransformerFactory
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				// Le damos formato y realizamos la transformación del documento a fichero
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				transformer.transform(source, result);
			}
		} catch (ParserConfigurationException | TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
