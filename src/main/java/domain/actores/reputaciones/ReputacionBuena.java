package domain.actores.reputaciones;

import domain.actores.Prestador;
import domain.trabajos.Calificacion;
import domain.trabajos.Trabajo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("buena")
public class ReputacionBuena extends Reputacion{
    public ReputacionBuena(Prestador prestador) {
        super(prestador);
    }

    @Override
    public void serContratado(Trabajo trabajo) {
        super.prestador.agregarTrabajo(trabajo);
    }

    @Override
    public void recibirCalificacion(Calificacion calificacion) {

    }
}
