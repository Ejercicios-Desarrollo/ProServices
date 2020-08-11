package domain.actores.reputaciones;

import domain.actores.Prestador;
import domain.actores.reputaciones.verificador.VerificadorDeContratacion;
import domain.trabajos.Calificacion;
import domain.trabajos.Trabajo;
import org.hsqldb.lib.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.Comparator;

@Entity
@DiscriminatorValue("mala")
public class ReputacionMala extends Reputacion{
    private static Integer peridoDeMaximosEnSemanas = 1;
    private static Integer maxCantVecesPorPeriodo = 1;

    @Column
    private Integer cantVecesContratadoEnPeriodo;


    public ReputacionMala(Prestador prestador) {
        super(prestador);
        this.cantVecesContratadoEnPeriodo = 0;
    }

    @Override
    public void serContratado(Trabajo trabajo) {
        if(puedeSerContratado()){
            super.prestador.agregarTrabajo(trabajo);
            actualizarContador();
        }
        else throw new RuntimeException("El prestador no puede ser contratado más de " + maxCantVecesPorPeriodo + " veces por cada " + peridoDeMaximosEnSemanas + " semanas.");
    }

    private VerificadorDeContratacion crearVerificador(){
        return new VerificadorDeContratacion(peridoDeMaximosEnSemanas, maxCantVecesPorPeriodo, this.cantVecesContratadoEnPeriodo);
    }

    private Boolean puedeSerContratado(){
        return crearVerificador().prestadorPuedeSerContratado(cantSemanasDesdeUltimaContratacion());
    }

    private Integer cantSemanasDesdeUltimaContratacion(){
        return Collections.min(
          super.prestador.getTrabajos(),
                Comparator.comparing(Trabajo::cantSemanasHastaFechaActual)
        ).cantSemanasHastaFechaActual();
    }

    private void actualizarContador(){
        this.cantVecesContratadoEnPeriodo = crearVerificador().valorParaContadorDeContrataciones(cantSemanasDesdeUltimaContratacion());
    }

    @Override
    public void recibirCalificacion(Calificacion calificacion) {

    }
}
