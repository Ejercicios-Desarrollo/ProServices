package domain.trabajos;

import domain.Persistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "estado_trabajo")
public class EstadoTrabajo extends Persistente {
    @Column
    String nombre;

    public EstadoTrabajo(){

    }

    public EstadoTrabajo(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
