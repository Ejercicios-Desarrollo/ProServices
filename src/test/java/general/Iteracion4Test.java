package general;

import com.google.common.hash.Hashing;
import domain.actores.Prestador;
import domain.actores.TipoDeDocumento;
import domain.actores.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repositories.Repository;
import repositories.daos.DAOHibernate;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;


public class Iteracion4Test {
    Repository<Prestador> repositorioDePrestadores;

    @Before
    public void init(){
        this.repositorioDePrestadores = new Repository<>(new DAOHibernate(), Prestador.class);
    }

    @Test
    public void JoseCastilloContraseniaTest(){
        Prestador joseCastillo = new Prestador("Jose", "Castillo");
        joseCastillo.setFechaDeNacimiento(LocalDate.of(1983, 8, 20));
        joseCastillo.setTipoDeDocumento(TipoDeDocumento.DNI);
        joseCastillo.setNumeroDeIdentificacion(31777999);
        joseCastillo.setCuitCuil("21-31777999-8");

        Usuario jCastillo = new Usuario("jcastillo");
        jCastillo.setContrasenia("qaz741wsx852");
        joseCastillo.setUsuario(jCastillo);

        this.repositorioDePrestadores.insert(joseCastillo);
        Integer idJoseCastillo = joseCastillo.getId();
        joseCastillo = null;

        Prestador supuestoJoseCastillo = this.repositorioDePrestadores.find(idJoseCastillo);
        Usuario supuestoUsuarioJoseCastillo = supuestoJoseCastillo.getUsuario();
        String contraseniaHasheada = Hashing.sha256().hashString("qaz741wsx852", StandardCharsets.UTF_8).toString();
        Assert.assertEquals(contraseniaHasheada, supuestoUsuarioJoseCastillo.getContrasenia());
    }

    @Test
    public void JoseCastilloEmailsTelefonosTest(){
        Prestador joseCastillo = new Prestador("Jose", "Castillo");
        joseCastillo.setFechaDeNacimiento(LocalDate.of(1983, 8, 20));
        joseCastillo.setTipoDeDocumento(TipoDeDocumento.DNI);
        joseCastillo.setNumeroDeIdentificacion(31777999);
        joseCastillo.setCuitCuil("21-31777999-8");

        Usuario jCastillo = new Usuario("jcastillo");
        jCastillo.setContrasenia("qaz741wsx852");
        joseCastillo.setUsuario(jCastillo);

        joseCastillo.agregarEmails("jcastillo@gmail.com", "jcastillo@outlook.com", "jcastillo@msn.com");
        joseCastillo.agregarTelefonos(1157889963, 20575963);

        this.repositorioDePrestadores.insert(joseCastillo);
        Integer idJoseCastillo = joseCastillo.getId();
        joseCastillo = null;

        Prestador supuestoJoseCastillo = this.repositorioDePrestadores.find(idJoseCastillo);

        Assert.assertEquals(3, supuestoJoseCastillo.getEmails().size());
        Assert.assertEquals(2, supuestoJoseCastillo.getTelefonos().size());
    }
}
