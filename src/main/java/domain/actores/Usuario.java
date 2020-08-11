package domain.actores;

import com.google.common.hash.Hashing;
import domain.Persistente;
import domain.trabajos.Calificacion;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario extends Persistente {
    @Column
    private String nombreDeUsuario;

    @Column
    private String contrasenia;

    @Column(name = "fechaDeAlta", columnDefinition = "DATE")
    private LocalDate fechaDeAlta;

    @Column(name = "fechaActivo", columnDefinition = "DATE")
    private LocalDate fechaActivo;

    @OneToOne
    @JoinColumn(name = "calificacion_id")
    private Calificacion calificacion;

    public Usuario(){

    }

    public Usuario(String nombreDeUsuario){
        this.nombreDeUsuario = nombreDeUsuario;
        this.fechaDeAlta = LocalDate.now();
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = Hashing.sha256().hashString(contrasenia, StandardCharsets.UTF_8).toString();
    }
}
