package general;

import domain.actores.Consumidor;
import domain.actores.Prestador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repositories.Repository;
import repositories.daos.DAOHibernate;

public class Iteracion3Test {
    Repository<Consumidor> consumidorRepository;
    Repository<Prestador> prestadorRepository;

    @Before
    public void init(){
        this.consumidorRepository = new Repository<>(new DAOHibernate(), Consumidor.class);
        this.prestadorRepository = new Repository<>(new DAOHibernate(), Prestador.class);
    }

    @Test
    public void JuanRodriguezTest(){
        Prestador juanRodriguez = new Prestador("Juan", "Rodriguez");
        this.prestadorRepository.insert(juanRodriguez);
        Integer idJuanRodriguez = juanRodriguez.getId();
        juanRodriguez = null;
        Prestador supuestoJuanRodriguez = this.prestadorRepository.find(idJuanRodriguez);
        Assert.assertEquals(juanRodriguez.getNombre(), supuestoJuanRodriguez.getNombre());
    }

    @Test
    public void modificoAMelisaSinImpactoEnBaseTest(){
        Consumidor melisaVolpe = new Consumidor("Melisa", "Volpe");
        this.consumidorRepository.insert(melisaVolpe);
        melisaVolpe.setNombre("Melisa Jesica");
        melisaVolpe.setApellido("Contreras");

        Integer idMelisaVolpe = melisaVolpe.getId();

        Consumidor supuestaMelisaVolpe = this.consumidorRepository.find(idMelisaVolpe);
        Assert.assertFalse(melisaVolpe.equals(supuestaMelisaVolpe));
        Assert.assertEquals("Melisa", supuestaMelisaVolpe.getNombre());
    }

    @Test
    public void modificoAMelisaImpactandoEnBaseTest(){
        Consumidor melisaVolpe = new Consumidor("Melisa", "Volpe");
        this.consumidorRepository.insert(melisaVolpe);
        melisaVolpe.setNombre("Melisa Jesica");
        melisaVolpe.setApellido("Contreras");

        this.consumidorRepository.update(melisaVolpe);
        Integer idMelisaVolpe = melisaVolpe.getId();

        Consumidor supuestaMelisaVolpe = this.consumidorRepository.find(idMelisaVolpe);
        Assert.assertEquals("Melisa Jesica", supuestaMelisaVolpe.getNombre());
    }
}
