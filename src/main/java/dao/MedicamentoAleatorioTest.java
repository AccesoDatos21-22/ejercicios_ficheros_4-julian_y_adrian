package dao;

import modelo.Medicamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicamentoAleatorioTest {
    final String FICHEROTEST = "Jtest";
    Medicamento medicamento;
    MedicamentoAleatorio medicamentoAleatorio;

    @BeforeEach
    void setUp() {
        medicamento = new Medicamento("Jtest", 20, 20, 20, 20);
        medicamentoAleatorio = new MedicamentoAleatorio();
    }

    @Test
    void guardarYleer() {
        try {
            Files.createFile(Path.of(FICHEROTEST));
            try (RandomAccessFile fichero = new RandomAccessFile(FICHEROTEST, "rw")) {
                fichero.seek((long) medicamento.getCod() * MedicamentoAleatorio.TAM_REGISTRO);
                fichero.writeChars(medicamento.getNombre());
                fichero.writeDouble(medicamento.getPrecio());
                fichero.writeInt(medicamento.getCod());
                fichero.writeInt(medicamento.getStock());
                fichero.writeInt(medicamento.getStockMaximo());
                fichero.writeInt(medicamento.getStockMinimo());
                fichero.writeInt(medicamento.getCodProveedor());
            } catch (IOException e) {
                e.printStackTrace();
            }
            var x = leerTodosTest();
            for (Medicamento med : x) {
                assertEquals(medicamento.getNombre().trim().replaceAll("\u0000", ""), med.getNombre().trim().replaceAll("\u0000", ""));
                assertEquals(medicamento.getCod(), med.getCod());
                assertEquals(medicamento.getPrecio(), med.getPrecio());
                assertEquals(medicamento.getStock(), med.getStock());
                assertEquals(medicamento.getCodProveedor(), med.getCodProveedor());
                assertEquals(medicamento.getStockMinimo(), med.getStockMinimo());
                assertEquals(medicamento.getStockMaximo(), med.getStockMaximo());
            }
            Files.deleteIfExists(Path.of(FICHEROTEST));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void buscar() throws IOException {
        try {
            Files.createFile(Path.of(FICHEROTEST));
            var test = buscarTest("Jtest");
            assertEquals("Jtest", test.getNombre());
            Files.deleteIfExists(Path.of(FICHEROTEST));
        } catch (Exception e) {
            e.printStackTrace();
            Files.deleteIfExists(Path.of(FICHEROTEST));
        }
    }

    @Test
    void actualizar() {
    }

    @Test
    void borrar() {
        try {
            Files.createFile(Path.of(FICHEROTEST));
            try (RandomAccessFile fichero = new RandomAccessFile(FICHEROTEST, "rw")) {
                fichero.seek((long) medicamento.getCod() * MedicamentoAleatorio.TAM_REGISTRO);
                fichero.writeChars(medicamento.getNombre());
                fichero.writeDouble(medicamento.getPrecio());
                fichero.writeInt(medicamento.getCod());
                fichero.writeInt(medicamento.getStock());
                fichero.writeInt(medicamento.getStockMaximo());
                fichero.writeInt(medicamento.getStockMinimo());
                fichero.writeInt(medicamento.getCodProveedor());
            } catch (IOException e) {
                e.printStackTrace();
            }
            borrarTest(medicamento);
            var x = leerTodosTest();
            Files.deleteIfExists(Path.of(FICHEROTEST));
            Assertions.assertTrue(x.size() == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private List<Medicamento> leerTodosTest() {
        List<Medicamento> medicamentos = new ArrayList<>();
        int posicionactual = 0;
        byte[] nombreB = new byte[MedicamentoAleatorio.TAM_NOMBRE * 2];
        String nombre;
        double precio;
        int cod;
        int stock;
        int stockMaximo;
        int stockMinimo;
        int codProveedor;
        try (RandomAccessFile fichero = new RandomAccessFile(FICHEROTEST, "r")) {
            while (fichero.length() > (long) posicionactual * MedicamentoAleatorio.TAM_REGISTRO) {
                fichero.seek((long) posicionactual * MedicamentoAleatorio.TAM_REGISTRO);

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
                    medicamentos.add(new Medicamento(nombre, precio, stock, stockMaximo, stockMinimo, cod, codProveedor));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }

    private boolean borrarTest(Medicamento medicamento2) {
        var medicamentos = leerTodosTest();
        medicamentos.removeIf(a -> a.getCod() == (medicamento2.getCod()));
        try {
            Files.deleteIfExists(Path.of(FICHEROTEST));
            Files.createFile(Path.of(FICHEROTEST));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Medicamento medicamento1 : medicamentos) {
            medicamentoAleatorio.guardar(medicamento1);
        }
        return true;
    }

    public Medicamento buscarTest(String nombre) throws Exception {
        var medicamentos = leerTodosTest();
        medicamentos.removeIf(a -> !a.getNombre().trim().replaceAll("\u0000", "").equals(nombre));
        if (medicamentos.size() > 0) {
            return medicamentos.get(0);
        } else {
            throw new Exception("No existe el medicamento");
        }
    }
}