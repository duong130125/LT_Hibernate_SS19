package ra.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ra.entity.Customer;

import java.util.List;

@Repository
public class CustomerRepoImpl implements CustomerRepo {

    private final SessionFactory sessionFactory;

    public CustomerRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    @Override
    public Customer findById(int id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public boolean save(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, id);
            if (customer == null) {
                return false;
            }
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Customer> findByName(String name) {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery(
                "FROM Customer WHERE lastName LIKE :name", Customer.class);
        query.setParameter("name", "%" + name + "%");
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }
}
