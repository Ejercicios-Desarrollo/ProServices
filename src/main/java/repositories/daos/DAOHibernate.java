package repositories.daos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DAOHibernate implements IDAO{

    @Override
    public boolean exist(int id, Class clase) {
        return EntityManagerHelper.entityManager().find(clase, id) != null;
    }

    @Override
    public Object find(int id, Class clase) {
        return EntityManagerHelper.entityManager().find(clase, id);
    }

    @Override
    public List findAll(Class clase) {
        Query query = EntityManagerHelper.createQuery("from " + clase.getName());
        return query.getResultList();
    }

    @Override
    public void delete(Object object) {
        EntityManagerHelper.remove(object);
    }

    @Override
    public void update(Object object) {
        EntityManagerHelper.merge(object);
    }

    @Override
    public void insert(Object object) {
        EntityManagerHelper.persist(object);
    }
}