package Main;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import dao.FarmaciaDOM;
import dao.FarmaciaXSTREAM;
import dao.JCCPokemonJAXB;
import dao.MedicamentoAleatorio;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import modelo.*;
import modelo.Marvel.Marvel;
import modelo.Marvel.ResultsItem;
import modelo.Tiempo.Tiempo;
import modelo.TiempoXml.Weatherdata;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Main {

    private static final String JAXB_XML_FILE = "xml/EmpresaJAXB.xml";
    private static final String XSTREAM_XML_FILE = "xml/EmpresaXTREAM.xml";
    private static final String XSTREAM2_XML_FILE = "xml/MedicamentosXTREAM.xml";
    private static final String DOM_XML_FILE = "xml/EmpleadosDOM.xml";
    private static final String DOM1_XML_FILE = "xml/MedicamentosDOM.xml";

    public static void main(String[] args) {
        pruebaEjercicio2();
//        Ejercicio3 ir a dao.MecicamentoAleatorioTest
        pruebaEjercicios4();
        pruebaEjercicios5();
        pruebaEjercicios6();
        pruebaEjercicios7();
        pruebaEjercicios8();
        pruebaEjercicio9();

    }

    private static void pruebaEjercicios8() {
        try {
            OkHttpClient okHttp = new OkHttpClient();
            Request request = new Request.Builder().url("https://api.openweathermap.org/data/2.5/forecast/daily?q=Galapagar&units=metric&appid=479092b77bcf850403cb2aeb1a302425").build();
            Response response = okHttp.newCall(request).execute();
            String info = response.body().string();
            Gson gson = new Gson();
            Tiempo t1 = gson.fromJson(info, Tiempo.class);
            System.out.println(t1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void pruebaEjercicios7() {
        try {
            OkHttpClient okHttp = new OkHttpClient();
            Request request = new Request.Builder().url("https://api.openweathermap.org/data/2.5/forecast/daily?q=Galapagar&units=metric&mode=xml&appid=479092b77bcf850403cb2aeb1a302425").build();
            Response response = okHttp.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            JAXBContext jaxbContext = JAXBContext.newInstance(Weatherdata.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Weatherdata weatherdata = (Weatherdata) unmarshaller.unmarshal(inputStream);
            System.out.println(weatherdata.getLocation().toString().replace("null", ""));
            weatherdata.getForecast().getTime().forEach(System.out::println);
            System.out.println(weatherdata.getSun().toString().replace("null", ""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static void pruebaEjercicios6() {
        Farmacia f1 = new Farmacia();
        Medicamento m1 = new Medicamento("pepe", 20.0, 211202, 10, 20);
        Medicamento m2 = new Medicamento("dfrf", 100, 211202, 10, 20);
        f1.guardar(m1);
        f1.guardar(m2);
        FarmaciaXSTREAM fx = new FarmaciaXSTREAM();
        fx.guardar(f1);
        fx.leerMedicamento(XSTREAM2_XML_FILE);
    }

    private static void pruebaEjercicios4() {
        Farmacia f1 = new Farmacia();
        Medicamento m1 = new Medicamento("pepe", 20.0, 211202, 10, 20);
        Medicamento m2 = new Medicamento("dfrf", 100, 211202, 10, 20);
        f1.guardar(m1);
        f1.guardar(m2);
        FarmaciaDOM fdom = new FarmaciaDOM();
        fdom.guardar(f1);
        fdom.leer(Path.of(DOM1_XML_FILE));
    }

    private static void pruebaEjercicios5() {
        List<Pokemon> x = new ArrayList<>();
        JCCPokemon jccPokemon = new JCCPokemon();
        JCCPokemonJAXB j1 = new JCCPokemonJAXB();
        j1.guardar(jccPokemon);
        JCCPokemon jccPokemon2 = j1.leer();
    }

    private static void pruebaEjercicio2() {
        Medicamento m1 = new Medicamento("pepe", 20.0, 211202, 10, 20);
        Medicamento m2 = new Medicamento("dfrf", 100, 211202, 10, 20);
        MedicamentoAleatorio aleatorio = new MedicamentoAleatorio();
        aleatorio.guardar(m1);
        aleatorio.guardar(m2);
        aleatorio.leerTodos().forEach(System.out::println);
    }

    private static void pruebaEjercicio9() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("https://gateway.marvel.com/v1/public/characters?ts=1&name=Iron%20Man&apikey=4ad7fd50f7f028b3f6f75045c3f53e60&hash=e8898161bd736e5c8b517cbbdf5267af").build();
            Response marvel = okHttpClient.newCall(request).execute();
            String Iron = marvel.body().string();
            Gson gson = new Gson();
            Marvel ironman = gson.fromJson(Iron, Marvel.class);
            for (ResultsItem result : ironman.getData().getResults()) {
                result.getSeries().getItems().forEach(a -> System.out.println(a.getName()));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ejemploEscribirXSTREAM() {

        try {

            System.out.println("Comienza el proceso de creaci??n del fichero a XML...");

            XStream xstream = new XStream();

            long time = System.currentTimeMillis();
            System.out.println("Inicio: " + new Date(time));
            Empresa cc = new Empresa();
            cc.setIdEmpresa(1);
            cc.setDireccion("En la nube");
            cc.setNombreEmpresa("IES");
            cc.setNumEmpleados(10);

            ArrayList<Empleado> alCU = new ArrayList<Empleado>();
            int init = 20000;
            for (int i = 1; i < 10; i++) {
                Empleado cu = new Empleado();
                cu.setId(i);
                cu.setActivo(true);
                cu.setNumeroEmpl(init++);
                cu.setNombre("Empleado " + i);
                cu.setTitulo("SW Architect");
                cu.setFechaAlta(new Date(System.currentTimeMillis()));

                alCU.add(cu);
            }

            cc.setEmpleados(alCU);

            // cambiar de nombre a las etiquetas XML
            xstream.alias("Empleado", Empleado.class);
            xstream.alias("Empresa", Empresa.class);

            // quitar etiqueta lista (Atributo de la clase ListaEmpleados)
            xstream.addImplicitCollection(Empresa.class, "Empresa");
            // Insertar los objetos en XML
            xstream.toXML(cc, new FileOutputStream(XSTREAM_XML_FILE));
            System.out.println("Creado fichero XML....");

        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    private static void ejemploLeerXSTREAM() {
        Empresa empresa = new Empresa();
        try {
            Class<?>[] classes = new Class[]{Empresa.class, Empleado.class};

            XStream xstream = new XStream();
            //XStream.setupDefaultSecurity(xstream);
            //xstream.allowTypes(classes);

            xstream.alias("Empresa", Empresa.class);
            xstream.alias("Empleado", Empleado.class);
            xstream.addImplicitCollection(Empresa.class, "Empresa");

            empresa = (Empresa) xstream
                    .fromXML(new FileInputStream(XSTREAM_XML_FILE));

            for (Empleado e : empresa.getEmpleados()) {
                System.out.println(e);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e);
        }

    }

    private static void ejemploLeerDOM() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File(DOM_XML_FILE));
            document.getDocumentElement().normalize();

            // Obtenemos la lista de nodos con nombre empleado de todo el documento
            NodeList empleados = document.getElementsByTagName("empleado");

            for (int i = 0; i < empleados.getLength(); i++) {
                Node emple = empleados.item(i); // obtener un nodo
                if (emple.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) emple; // tipo de nodo
                    System.out.println("ID: " + getNodo("id", elemento));
                    System.out.println("Apellido: " + getNodo("nombre", elemento));
                    System.out.println("Departamento: " + getNodo("dep", elemento));
                    System.out.println("Salario: " + getNodo("salario", elemento));
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // obtener informaci??n de un nodo
    public static String getNodo(String etiqueta, Element elem) {
        NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue(); // devuelve el valor del nodo
    }

    private static void ejemploEscribirDOM() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0"); // asignamos la version de nuestro XML

            for (int i = 1; i < 10; i++) {
                Element raiz = document.createElement("empleado");

                document.getDocumentElement().appendChild(raiz);

                CrearElemento("id", Integer.toString(i), raiz, document);
                CrearElemento("nombre", "Empleado " + i, raiz, document);
                CrearElemento("dep", "01", raiz, document);
                CrearElemento("salario", "1000.0", raiz, document);
            }

            // Creamos la fuente XML a partir del documento
            Source source = new DOMSource(document);
            // Creamos el resultado en el fichero Empleados.xml
            Result result = new StreamResult(new java.io.File(DOM_XML_FILE));
            // Obtenemos un TransformerFactory
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Le damos formato y realizamos la transformaci??n del documento a fichero
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
            // Mostramos el documento por pantalla especificando el canal de salida el
            // System.out
            Result console = new StreamResult(System.out);

            transformer.transform(source, console);
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    static void CrearElemento(String datoEmple, String valor, Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }

    private static void ejemploJaxb() {
        long time = System.currentTimeMillis();
        System.out.println("Inicio: " + new Date(time));
        Empresa cc = new Empresa();
        cc.setIdEmpresa(1);
        cc.setDireccion("En la nube");
        cc.setNombreEmpresa("IES");
        cc.setNumEmpleados(10);

        ArrayList<Empleado> alCU = new ArrayList<Empleado>();
        int init = 20000;
        for (int i = 1; i < 10; i++) {
            Empleado cu = new Empleado();
            cu.setId(i);
            cu.setActivo(true);
            cu.setNumeroEmpl(init++);
            cu.setNombre("Empleado " + i);
            cu.setTitulo("SW Architect");
            cu.setFechaAlta(new Date(System.currentTimeMillis()));

            alCU.add(cu);
        }

        cc.setEmpleados(alCU);

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Empresa.class);

            // Si las clases a serializar est??n en otro paquete se indica el paquete
            // al crear el marshall
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Provincia provincia = fillProvincia();
            // Mostramos el documento XML generado por la salida estandar
            marshaller.marshal(cc, System.out);
            // guardamos el objeto serializado en un documento XML
            marshaller.marshal(cc, Files.newOutputStream(Paths.get(JAXB_XML_FILE)));
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // Deserealizamos a partir de un documento XML
            Empresa empresa = (Empresa) unmarshaller.unmarshal(Files.newInputStream(Paths.get(JAXB_XML_FILE)));
            System.out.println("********* Empresa cargado desde fichero XML***************");
            // Mostramos por linea de comandos el objeto Java obtenido
            // producto de la deserialziacion
            marshaller.marshal(empresa, System.out);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}