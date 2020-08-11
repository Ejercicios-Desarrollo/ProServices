package domain.actores.reputaciones;

import domain.Persistente;
import domain.actores.Prestador;
import domain.trabajos.Calificacion;
import domain.trabajos.Trabajo;

import javax.persistence.*;

@Entity
@Table(name = "reputacion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Reputacion extends Persistente {
    @OneToOne
    @JoinColumn(name = "prestador_id")
    protected Prestador prestador;

    public Reputacion(Prestador prestador){
        this.prestador = prestador;
    }

    public abstract void serContratado(Trabajo trabajo);
    public abstract void recibirCalificacion(Calificacion calificacion);
}
