package modelo;

import java.util.ArrayList;
import java.util.List;

import dao.MedicamentoDAO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "Farmacia")
@XmlType(propOrder = {"medicamentos"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Farmacia implements MedicamentoDAO {
	private List<Medicamento> medicamentos;

	/**
	 * Constructor de la farmacia
	 */
	public Farmacia() {
		this.medicamentos=new ArrayList<>();
	}

	@Override
	public boolean guardar(Medicamento medicamento) {
		medicamentos.add(medicamento);
		return false;
	}

	@Override
	public boolean borrar(Medicamento medicamento) {

		return false;
	}

	@Override
	public List<Medicamento> leerTodos() {
		return medicamentos;
	}

	@Override
	public Medicamento buscar(String nombre) {
		return null;
	}

	@Override
	public boolean actualizar(Medicamento medicamento) {
		return false;
	}
}
