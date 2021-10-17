package dao;

import modelo.Medicamento;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoAleatorio implements MedicamentoDAO {

    public final static int TAM_NOMBRE = 50;
    public final static int TAM_REGISTRO = 128;

    private final static String RUTA_FICHERO_MEDICAMENTOS = "medicamentos.dat";

    @Override
    public boolean guardar(Medicamento medicamento) {
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO_MEDICAMENTOS, "rw")) {
            fichero.seek((long) medicamento.getCod() * TAM_REGISTRO);
            fichero.writeChars(medicamento.getNombre());
            fichero.writeDouble(medicamento.getPrecio());
            fichero.writeInt(medicamento.getCod());
            fichero.writeInt(medicamento.getStock());
            fichero.writeInt(medicamento.getStockMaximo());
            fichero.writeInt(medicamento.getStockMinimo());
            fichero.writeInt(medicamento.getCodProveedor());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Medicamento buscar(String nombre) throws Exception {
        var medicamentos = leerTodos();
        medicamentos.removeIf(a -> !a.getNombre().trim().replaceAll("\u0000", "").equals(nombre));
        if (medicamentos.size() > 0) {
            return medicamentos.get(0);
        } else {
           throw new Exception("No existe el medicamento");
        }
    }

    @Override
    public boolean actualizar(Medicamento medicamento) {

        return false;
    }

    @Override
    public boolean borrar(Medicamento medicamento) {
        var medicamentos = leerTodos();
        medicamentos.removeIf(a -> a.getCod()==(medicamento.getCod()));
        try {
            Files.deleteIfExists(Path.of(RUTA_FICHERO_MEDICAMENTOS));
            Files.createFile(Path.of(RUTA_FICHERO_MEDICAMENTOS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Medicamento medicamento1 : medicamentos) {
            guardar(medicamento1);
        }
        return true;
    }


    @Override
    public List<Medicamento> leerTodos() {
        List<Medicamento> medicamentos = new ArrayList<>();
        int posicionactual = 0;
        byte[] nombreB = new byte[TAM_NOMBRE*2];
        String nombre;
        double precio;
        int cod;
        int stock;
        int stockMaximo;
        int stockMinimo;
        int codProveedor;
        try (RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO_MEDICAMENTOS, "r")) {
            while (fichero.length() > (long) posicionactual * TAM_REGISTRO) {
                fichero.seek((long) posicionactual * TAM_REGISTRO);

                fichero.readFully(nombreB);
                nombre = new String(nombreB);
                precio = fichero.readDouble();
                cod = fichero.readInt();
                stock = fichero.readInt();
                stockMaximo = fichero.readInt();
                stockMinimo = fichero.readInt();
                codProveedor = fichero.readInt();
                posicionactual++;
                if (!nombre.trim().replaceAll("\u0000", "").isBlank()) {
                    medicamentos.add(new Medicamento(nombre, precio, stock, stockMaximo, stockMinimo,cod,codProveedor));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }

}
