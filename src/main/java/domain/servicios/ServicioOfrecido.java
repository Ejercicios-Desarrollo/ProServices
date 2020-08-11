package domain.servicios;

import domain.Persistente;
import domain.actores.Prestador;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "servicio_ofrecido")
public class ServicioOfrecido extends Persistente {
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @Column
    private String experiencia;

    @ElementCollection
    @CollectionTable(name = "foto_servicio_ofrecido", joinColumns = @JoinColumn(name = "servicio_ofrecido_id"))
    @Column(name = "foto")
    private Set<String> fotos;

    public ServicioOfrecido(){

    }

    public ServicioOfrecido(Servicio servicio, Prestador prestador){
        this.servicio = servicio;
        this.prestador = prestador;
        this.fotos = new LinkedHashSet<>();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public Set<String> getFotos() {
        return fotos;
    }
}
