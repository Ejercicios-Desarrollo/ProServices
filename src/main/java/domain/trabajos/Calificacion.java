package domain.trabajos;

import domain.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "calificacion")
public class Calificacion extends Persistente {
    @Column(name = "estrellas")
    private Integer estrellas;

    @Column(name = "opinion")
    private String opinionLibre;

    public Calificacion(){

    }

    public Calificacion(Integer estrellas){
        this.estrellas = estrellas;
    }

    public void setOpinionLibre(String opinionLibre) {
        this.opinionLibre = opinionLibre;
    }

    public Integer getEstrellas() {
        return estrellas;
    }
}
