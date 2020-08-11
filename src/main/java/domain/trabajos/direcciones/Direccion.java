package domain.trabajos.direcciones;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
    @Column
    private String localidad;

    @Column
    private Integer codigoPostal;

    @Column
    private String calle;

    @Column
    private Integer numero;

    @Column
    private Integer piso;

    @Column
    private String depto;

    @Column
    private String referencia;

    public Direccion(){

    }
}
