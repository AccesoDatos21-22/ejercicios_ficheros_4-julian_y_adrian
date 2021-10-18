package modelo;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "Pokemon")
@XmlType(propOrder = {"nombre", "nivel", "vida", "ataque", "defensa", "ataqueEspecial", "defensaEspecial", "velocidad"})
@XmlAccessorType(XmlAccessType.PROPERTY)

public class Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private int nivel, vida, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad;

    public Pokemon() {
        this.nombre = "default";
        this.nivel = 1;
        this.vida = 1;
        this.ataque = 1;
        this.defensa = 1;
        this.ataqueEspecial = 1;
        this.defensaEspecial = 1;
        this.velocidad = 1;
    }

    public Pokemon(String nombre, int nivel, int vida, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }

    public int getVelocidad() {
        return velocidad;
    }


    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", vida=" + vida +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", ataqueEspecial=" + ataqueEspecial +
                ", defensaEspecial=" + defensaEspecial +
                ", velocidad=" + velocidad +
                '}';
    }
}
