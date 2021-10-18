package modelo;

import java.util.ArrayList;
import java.util.List;

import dao.MedicamentoDAO;

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
