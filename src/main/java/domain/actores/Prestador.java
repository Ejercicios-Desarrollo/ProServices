package domain.actores;

import domain.Persistente;
import domain.actores.reputaciones.Reputacion;
import domain.servicios.ServicioOfrecido;
import domain.trabajos.Calificacion;
import domain.trabajos.Trabajo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "prestador")
public class Prestador extends Persistente {
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // columnDefinition = "DATE" aplica el convertidor creado
    @Column(name = "fechaDeNacimiento", columnDefinition = "DATE")
    private LocalDate fechaDeNacimiento;

    @Column(name = "foto")
    private String foto;

    @Column
    private String cuitCuil;

    @Enumerated(EnumType.STRING)
    private TipoDeDocumento tipoDeDocumento;

    @Column
    private Integer numeroDeIdentificacion;

    @ElementCollection
    @CollectionTable(name = "email", joinColumns = @JoinColumn(name = "prestador_id"))
    @Column(name = "email")
    private Set<String> emails;

    @ElementCollection
    @CollectionTable(name = "telefono", joinColumns = @JoinColumn(name = "prestador_id"))
    @Column(name = "telefono")
    private Set<Integer> telefonos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "prestador_id")
    private List<Disponibilidad> disponibilidades;

    // mappedBy: encontrame en ServicioOfrecido como "prestador"
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prestador")
    private List<ServicioOfrecido> servicioOfrecidos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prestador")
    private List<Trabajo> trabajos;

    @OneToOne
    private Reputacion reputacion;

    public Prestador(){

    }

    public Prestador(String nombre, String apellido, Usuario usuario){
        this.init(nombre, apellido);
        this.usuario = usuario;
    }

    public Prestador(String nombre, String apellido){
        this.init(nombre, apellido);
    }

    private void init(String nombre, String apellido){
        this.nombre     = nombre;
        this.apellido   = apellido;
        this.emails     = new LinkedHashSet<>();
        this.telefonos  = new LinkedHashSet<>();
        this.disponibilidades = new ArrayList<>();
        this.servicioOfrecidos = new ArrayList<>();
        this.trabajos = new ArrayList<>();
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

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public void setCuitCuil(String cuitCuil) {
        this.cuitCuil = cuitCuil;
    }

    public void setTipoDeDocumento(TipoDeDocumento tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public void setNumeroDeIdentificacion(Integer numeroDeIdentificacion) {
        this.numeroDeIdentificacion = numeroDeIdentificacion;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public void setTelefonos(Set<Integer> telefonos) {
        this.telefonos = telefonos;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void agregarEmails(String ... emails){
        Collections.addAll(this.emails, emails);
    }

    public Set<Integer> getTelefonos() {
        return telefonos;
    }

    public void agregarTelefonos(Integer ... telefonos){
        Collections.addAll(this.telefonos, telefonos);
    }

    public void agregarDisponibilidades(Disponibilidad ... disponibilidades){
        Collections.addAll(this.disponibilidades, disponibilidades);
    }

    public void agregarServiciosOfrecidos(ServicioOfrecido ... serviciosOfrecidos){
        Collections.addAll(this.servicioOfrecidos, serviciosOfrecidos);
    }

    public List<ServicioOfrecido> getServicioOfrecidos() {
        return servicioOfrecidos;
    }

    public void serContratado(Trabajo trabajo){
        this.reputacion.serContratado(trabajo);
    }

    public void recibirCalificacion(Calificacion calificacion){
        this.reputacion.recibirCalificacion(calificacion);
    }

    public void agregarTrabajo(Trabajo trabajo){
        this.trabajos.add(trabajo);
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void cambiarReputacion(Reputacion reputacion){
        this.reputacion = reputacion;
    }
}
