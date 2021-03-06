package modelo;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Medicamento")
@XmlType(propOrder = {"nombre", "precio", "stock", "stockMaximo", "stockMinimo"})
@XmlAccessorType(XmlAccessType.PROPERTY)

public class Medicamento {
    public final static float IVA = 0.04f;
    private static int cont = 0;
    StringBuilder sb;
    private String nombre; // tama�o 30, 60 bytes
    private double precio; // 8 bytes
    private int cod; // 4 bytes
    private int stock; // 4 bytes
    private int stockMaximo; // 4 bytes
    private int stockMinimo; // 4 bytes
    private int codProveedor; // 4 bytes

    public Medicamento() {
    }

    public Medicamento(String nombre, double precio, int stock, int stockMaximo, int stockMinimo, int cod, int codProveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.cod = cod;
        this.stock = stock;
        this.stockMaximo = stockMaximo;
        this.stockMinimo = stockMinimo;
        this.codProveedor = codProveedor;
    }

    public Medicamento(String nombre, double precio, int stock, int stockMaximo, int stockMinimo) {
        this.nombre = nombre;
        this.precio = precio;
        this.cod = cont;
        this.stock = stock;
        this.stockMaximo = stockMaximo;
        this.stockMinimo = stockMinimo;
        this.codProveedor = cont;
        cont++;
    }

    public static float getIva() {
        return IVA;
    }

    public String getNombre() {
        sb = new StringBuilder(this.nombre);
        sb.setLength(50);
        return sb.toString();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(int codProveedor) {
        this.codProveedor = codProveedor;
    }

    @Override
    public String toString() {
        return "codigo= " + cod + "\n" + "nombre= " + nombre.trim().replaceAll("\u0000", "") + "\n" + "precio= " + precio + "\n" + "stock= " + stock + "\n" + "Proveedor= " + codProveedor;
    }


}
