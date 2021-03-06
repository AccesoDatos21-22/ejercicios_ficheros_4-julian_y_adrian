package modelo;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlType(propOrder = {"pokemones", "fechaLanzamiento", "numCartas"})
@XmlRootElement(name = "JCCPokemon")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class JCCPokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Pokemon> pokemones;
    private Date fechaLanzamiento;
    private int numCartas;

    public JCCPokemon(List<Pokemon> pokemones, Date fechaLanzamiento, int numCartas) {
        this.pokemones = pokemones;
        this.fechaLanzamiento = fechaLanzamiento;
        this.numCartas = numCartas;
    }

    public JCCPokemon() {
        this.pokemones = new ArrayList<>();
        this.fechaLanzamiento = new Date();
        this.numCartas = 0;
    }

    public List<Pokemon> getPokemones() {
        return pokemones;
    }

    public void setPokemones(List<Pokemon> pokemones) {
        this.pokemones = pokemones;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getNumCartas() {
        return numCartas;
    }

    public void setNumCartas(int numCartas) {
        this.numCartas = numCartas;
    }
}
