package general;

import domain.actores.*;
import domain.servicios.Servicio;
import domain.servicios.ServicioOfrecido;
import domain.servicios.Tarea;
import domain.trabajos.EstadoTrabajo;
import domain.trabajos.Trabajo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repositories.Repository;
import repositories.daos.DAOHibernate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Iteracion5Test {
    Repository<Servicio> repositorioDeServicios;
    Repository<Prestador> repositorioDePrestadores;
    Repository<Consumidor> repositorioDeConsumidores;
    Prestador joseCastillo;
    Usuario jCastillo;
    Servicio instalacionReparacionAiresAcondicionados;
    Servicio mantenimientoDeJardin;
    Consumidor melisaContreras;
    Usuario mContreras;
    Repository<EstadoTrabajo> repositorioEstadoTrabajo;
    ServicioOfrecido servicioOfrecido;
    Integer idJoseCastillo;
    Tarea cargaDeGas;
    Repository<Trabajo> repositorioDeTrabajos;
    Integer idTrabajo;

    @Before
    public void init(){
        this.repositorioDeServicios = new Repository<>(new DAOHibernate(), Servicio.class);
        this.instalacionReparacionAiresAcondicionados = new Servicio("Instalación y reparacion de Aires Acondicionados");
        this.cargaDeGas = new Tarea("Carga de gas");
        instalacionReparacionAiresAcondicionados.agregarTareas(cargaDeGas, new Tarea("Cambio de\n" +
                "mangueras"), new Tarea("Limpieza de filtros"), new Tarea("Reparación del motor"),
                new Tarea("Instalación de equipo"));

        this.mantenimientoDeJardin = new Servicio("Mantenimiento de jardín");
        mantenimientoDeJardin.agregarTareas(new Tarea("Cortar el pasto"), new Tarea("Podar los árboles"), new Tarea("Regar las\n" +
                "plantas"), new Tarea("Remover la tierra"), new Tarea("Decorar el jardín"));

        repositorioDeServicios.insert(instalacionReparacionAiresAcondicionados);
        repositorioDeServicios.insert(mantenimientoDeJardin);

        EstadoTrabajo enEspera = new EstadoTrabajo("En espera de confirmación");
        this.repositorioEstadoTrabajo = new Repository<>(new DAOHibernate(), EstadoTrabajo.class);
        repositorioEstadoTrabajo.insert(enEspera);
        repositorioEstadoTrabajo.insert(new EstadoTrabajo("Confirmado"));
        repositorioEstadoTrabajo.insert(new EstadoTrabajo("Rechazado"));
        repositorioEstadoTrabajo.insert(new EstadoTrabajo("No realizado"));
        repositorioEstadoTrabajo.insert(new EstadoTrabajo("Realizado"));

        this.repositorioDePrestadores = new Repository<>(new DAOHibernate(), Prestador.class);
        this.joseCastillo = new Prestador("Jose", "Castillo");
        joseCastillo.setFechaDeNacimiento(LocalDate.of(1983, 8, 20));
        joseCastillo.setTipoDeDocumento(TipoDeDocumento.DNI);
        joseCastillo.setNumeroDeIdentificacion(31777999);
        joseCastillo.setCuitCuil("21-31777999-8");

        this.jCastillo = new Usuario("jcastillo");
        jCastillo.setContrasenia("qaz741wsx852");
        joseCastillo.setUsuario(jCastillo);

        joseCastillo.agregarEmails("jcastillo@gmail.com", "jcastillo@outlook.com", "jcastillo@msn.com");
        joseCastillo.agregarTelefonos(1157889963, 20575963);

        LocalTime hora8 = LocalTime.of(8,0,0,0);
        LocalTime hora12 = LocalTime.of(12,0,0,0);
        LocalTime hora16 = LocalTime.of(16,0,0,0);
        Disponibilidad disponibilidadL = new Disponibilidad(hora8, hora12, DayOfWeek.MONDAY);
        Disponibilidad disponibilidadM = new Disponibilidad(hora8, hora12, DayOfWeek.WEDNESDAY);
        Disponibilidad disponibilidadMa = new Disponibilidad(hora12, hora16, DayOfWeek.TUESDAY);
        Disponibilidad disponibilidadJ = new Disponibilidad(hora12, hora16, DayOfWeek.THURSDAY);
        joseCastillo.agregarDisponibilidades(disponibilidadL, disponibilidadMa, disponibilidadM, disponibilidadJ);

        this.servicioOfrecido = new ServicioOfrecido(instalacionReparacionAiresAcondicionados, joseCastillo);

        servicioOfrecido.setExperiencia("Brindo soluciones de Instalación y Mantenimiento para equipos\n" +
                "de aire acondicionado y de refrigeración, tanto a nivel residencial como\n" +
                "comercial. Soy profesional matriculado en la Cámara Argentina de\n" +
                "Calefacción.");

        joseCastillo.agregarServiciosOfrecidos(servicioOfrecido);

        this.repositorioDePrestadores.insert(joseCastillo);
        this.idJoseCastillo = joseCastillo.getId();

        this.repositorioDeConsumidores = new Repository<>(new DAOHibernate(), Consumidor.class);

        this.melisaContreras = new Consumidor("Melisa Jesica", "Contreras");
        this.mContreras = new Usuario("mjcontreras");
        mContreras.setContrasenia("edc741rfv852");
        melisaContreras.setUsuario(mContreras);
        repositorioDeConsumidores.insert(melisaContreras);

        this.repositorioDeTrabajos = new Repository<>(new DAOHibernate(), Trabajo.class);
        Trabajo trabajo = new Trabajo(melisaContreras, joseCastillo);
        trabajo.agregarTareas(cargaDeGas);
        //trabajo.setLugar("Avenida del Libertador 3580 CABA");
        trabajo.setFecha(LocalDate.of(2020,9,14));
        trabajo.setHoraInicio(LocalTime.of(8,0,0,0));
        trabajo.setHoraFin(LocalTime.of(10,0,0,0));
        trabajo.setEstado(enEspera);
        this.repositorioDeTrabajos.insert(trabajo);
        this.idTrabajo = trabajo.getId();
    }

    @Test
    public void setJoseCastilloNoMantieneJardinTest(){
        Prestador supuestoJoseCastillo = this.repositorioDePrestadores.find(idJoseCastillo);
        supuestoJoseCastillo.getServicioOfrecidos().stream().forEach(s -> Assert.assertNotEquals("Mantenimiento de jardín", s.getServicio().getNombre()));
    }

    @Test
    public void pactarTrabajoTest(){
        Trabajo supuestoTrabajo = this.repositorioDeTrabajos.find(idTrabajo);
        Assert.assertEquals("En espera de confirmación", supuestoTrabajo.getEstado().getNombre());
    }
}
