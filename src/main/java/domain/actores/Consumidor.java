package domain.actores;

import domain.Persistente;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "consumidor")
public class Consumidor extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "fechaDeNacimiento", columnDefinition = "DATE")
    private LocalDate fechaDeNacimiento;

    public Consumidor(){

    }

    public Consumidor(String nombre, String apellido, Usuario usuario){
        this.init(nombre, apellido);
        this.usuario = usuario;
    }

    public Consumidor(String nombre, String apellido){
        this.init(nombre, apellido);
    }

    private void init(String nombre, String apellido){
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
