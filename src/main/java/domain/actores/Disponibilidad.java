package domain.actores;

import converters.DiaDeLaSemanaConverter;
import domain.Persistente;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "disponibilidad")
public class Disponibilidad extends Persistente {
    @Column(columnDefinition = "TIME")
    private LocalTime horaInicio;

    @Column(columnDefinition = "TIME")
    private LocalTime horaFin;

    @Convert(converter = DiaDeLaSemanaConverter.class)
    private DayOfWeek dia;

    public Disponibilidad(){

    }

    public Disponibilidad(LocalTime horaInicio, LocalTime horaFin, DayOfWeek dia){
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public DayOfWeek getDia() {
        return dia;
    }
}
