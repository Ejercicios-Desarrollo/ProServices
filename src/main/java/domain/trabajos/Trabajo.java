package domain.trabajos;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.Persistente;
import domain.actores.Consumidor;
import domain.actores.Prestador;
import domain.servicios.Tarea;
import domain.trabajos.direcciones.Direccion;
import org.hsqldb.lib.Collection;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "trabajo")
public class Trabajo extends Persistente {
    @ManyToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaDeSolicitud;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    @Embedded
    private Direccion lugar;

    @Column
    private Boolean trabajoAceptado;

    @Column(columnDefinition = "TIME")
    private LocalTime horaInicio;

    @Column(columnDefinition = "TIME")
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "estado_trabajo_id")
    private EstadoTrabajo estado;

    @Column
    private Boolean finalizadoSegunConsumidor;

    @Column
    private Boolean finalizadoSegunPrestador;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tarea> tareas;

    @OneToOne
    @JoinColumn(name = "calificacion_id")
    private Calificacion calificacion;

    public Trabajo(){

    }

    public Trabajo(Consumidor consumidor, Prestador prestador){
        this.consumidor         = consumidor;
        this.prestador          = prestador;
        this.fechaDeSolicitud   = LocalDate.now();
        this.trabajoAceptado    = false;
        this.tareas             = new ArrayList<>();
    }

    public void setFechaDeSolicitud(LocalDate fechaDeSolicitud) {
        this.fechaDeSolicitud = fechaDeSolicitud;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setLugar(Direccion lugar) {
        this.lugar = lugar;
    }

    public void setTrabajoAceptado(Boolean trabajoAceptado) {
        this.trabajoAceptado = trabajoAceptado;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setEstado(EstadoTrabajo estado) {
        this.estado = estado;
    }

    public EstadoTrabajo getEstado() {
        return estado;
    }

    public void agregarTareas(Tarea ... tareas){
        Collections.addAll(this.tareas, tareas);
    }

    public LocalDate getFechaDeSolicitud() {
        return fechaDeSolicitud;
    }

    public Integer cantSemanasHastaFechaActual(){
        return (int) this.fechaDeSolicitud.until(LocalDate.now(), ChronoUnit.WEEKS);
    }
}
