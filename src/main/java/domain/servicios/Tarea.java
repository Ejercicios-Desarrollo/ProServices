package domain.servicios;

import domain.Persistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tarea")
public class Tarea extends Persistente {
    @Column(name = "descripcion")
    private String descripcion;

    public Tarea(){

    }

    public Tarea(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
