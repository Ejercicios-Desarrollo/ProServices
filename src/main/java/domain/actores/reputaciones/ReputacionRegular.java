package domain.actores.reputaciones;

import domain.actores.Prestador;
import domain.actores.reputaciones.verificador.VerificadorDeContratacion;
import domain.trabajos.Calificacion;
import domain.trabajos.Trabajo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@DiscriminatorValue("regular")
public class ReputacionRegular extends Reputacion{
    private static Integer peridoDeMaximosEnSemanas = 1;
    private static Integer maxCantVecesPorPeriodo = 4;
    private static Integer minimoDeEstrellas = 4;
    private static Integer cantSeguidasDeBuenasEstrellasRequeridas = 4;

    @Column
    private Integer cantVecesContratadoEnPeriodo;

    @Column
    private LocalDate fechaUltimaContratacion;

    @Column
    private Integer cantDeBuenasEstrellas;

    public ReputacionRegular(Prestador prestador) {
        super(prestador);
        this.cantVecesContratadoEnPeriodo = 0;
        this.cantDeBuenasEstrellas = 0;
    }

    @Override
    public void serContratado(Trabajo trabajo) {
        if(puedeSerContratado()){
            super.prestador.agregarTrabajo(trabajo);
            this.fechaUltimaContratacion = trabajo.getFechaDeSolicitud();
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

    private Integer cantSemanasDesdeUltimaContratacion() {
        return (int) this.fechaUltimaContratacion.until(LocalDate.now(), ChronoUnit.WEEKS);
    }

    private void actualizarContador(){
        this.cantVecesContratadoEnPeriodo = crearVerificador().valorParaContadorDeContrataciones(cantSemanasDesdeUltimaContratacion());
    }

    @Override
    public void recibirCalificacion(Calificacion calificacion) {
        this.cantDeBuenasEstrellas =
                calificacion.getEstrellas() >= minimoDeEstrellas?
                        this.cantDeBuenasEstrellas + 1 :
                        0;
        cambiarDeEstadoSiEsNecesario();
    }

    private void cambiarDeEstadoSiEsNecesario(){
        if(this.cantDeBuenasEstrellas >= cantSeguidasDeBuenasEstrellasRequeridas){
        super.prestador.cambiarReputacion(new ReputacionBuena(super.prestador));
        }
    }
}
