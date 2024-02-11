package pl.edu.wszib.library.management.api.dao.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.management.api.dao.impl.IClientDAO;
import pl.edu.wszib.library.management.api.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
@Component
public class ClientDAO implements IClientDAO {
    private final String GET_BY_ID = "FROM pl.edu.wszib.library.management.api.model.Client WHERE id = :id";
    private final String GET_ALL = "FROM pl.edu.wszib.library.management.api.model.Client";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<Client> getClientById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Client> query = session.createQuery(GET_BY_ID, Client.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Client> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Client> query = session.createQuery(GET_ALL, Client.class);
        List<Client> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Client(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void persist(Client client) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
