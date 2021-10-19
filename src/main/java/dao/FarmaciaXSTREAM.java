package dao;

import com.thoughtworks.xstream.XStream;
import modelo.Empleado;
import modelo.Empresa;
import modelo.Farmacia;
import modelo.Medicamento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class FarmaciaXSTREAM implements FarmaciaDAO{

	final static String rutaFarmcia =""; // localizacion del fichero XML
	final static String rutaMedicamento ="xml/MedicamentosXTREAM.xml"; // localizacion del fichero XML

	@Override
	public Farmacia leer() {
		return null;
	}

	@Override
	public boolean guardar(Farmacia farmacia) {
		Farmacia f1 = new Farmacia();
		try {
			XStream xstream = new XStream();
			ArrayList<Medicamento> medicamentos = new ArrayList<>(farmacia.leerTodos());
			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);
			xstream.addImplicitCollection(Medicamento.class, "Medicamento");

			xstream.toXML(medicamentos, new FileOutputStream(rutaMedicamento));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Creado fichero XML....");


		return false;
	}


	public Medicamento leerMedicamento() {
		
		return null;
	}

	public Medicamento leerMedicamento(String rutaMedicamento) {
		Farmacia f1=new Farmacia();
		ArrayList<Medicamento> medicamentos = new ArrayList<>();
		try {
			Class<?>[] classes = new Class[]{Farmacia.class, Medicamento.class};
			XStream xstream = new XStream();
			xstream.alias("Farmacia", Farmacia.class);
			xstream.alias("Medicamento", Medicamento.class);
			xstream.addImplicitCollection(Farmacia.class, "Farmacia");


			medicamentos = (ArrayList<Medicamento>) xstream
					.fromXML(new FileInputStream(rutaMedicamento));
			for (Medicamento e : medicamentos) {
				System.out.println(e);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		return null;
	}
	public void guardarMedicamento(Medicamento medicamento) {
	}

	
}
