package daoImplementaion;

import controllers.HibernateHelper;
import dao.IClientDAO;
import database.Database;
import entities.Client;
import entities.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImp implements IClientDAO<Client> {

    private static final Connection connection = Database.getInstance().getConnection();

    private static final SessionFactory sessionFactory = HibernateHelper.getSessionFactory();


    /**
     * @param code
     * @return
     */
    @Override
    public Optional<Client> findByCode(String code) {
        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            String hql = "FROM Client C WHERE C.code = :code";
            Query<Client> query = session.createQuery(hql, Client.class);
            query.setParameter("code", code);
            Client client = query.uniqueResult();

            return Optional.of(client);
        } catch (Exception e) {
            System.out.println("Error when trying to select: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param client
     * @return
     */
//    @Override
//    public Optional<Client> save(Client client) {
//
//        String sql = "INSERT INTO client (code, first_name, last_name, birth_date, phone_number, address, employee_code) VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, client.getCode());
//            preparedStatement.setString(2, client.getFirstName());
//            preparedStatement.setString(3, client.getLastName());
//            preparedStatement.setObject(4, client.getBirthDate());
//            preparedStatement.setString(5, client.getPhoneNumber());
//            preparedStatement.setString(6, client.getAddress());
//            preparedStatement.setString(7, client.getEmployee().getCode());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return Optional.of(client);
//    }

    @Override
    public Optional<Client> save(Client client) {
        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
            return Optional.of(client);
        } catch (Exception e) {
            System.out.println("Error when trying to save: " + e.getMessage());
            return Optional.empty();
        }
    }


    /**
     * @param code
     * @return
     */
    @Override
    public boolean delete(String code) {
        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Client> query = session.createQuery("DELETE FROM Client C WHERE C.code = :code", Client.class);
            query.setParameter("code", code);
            int rowCount = query.executeUpdate();
            transaction.commit();
            return rowCount > 0;
        } catch (Exception e) {
            System.out.println("Error when trying to delete: " + e.getMessage());
            return false;
        }
    }


    /**
     * @param client
     * @return
     */
//    @Override
//    public boolean update(Client client) {
//        boolean updated = false;
//
//        String sql = "UPDATE client SET first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, address = ? WHERE code = ?";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, client.getFirstName());
//            preparedStatement.setString(2, client.getLastName());
//            preparedStatement.setObject(3, client.getBirthDate());
//            preparedStatement.setString(4, client.getPhoneNumber());
//            preparedStatement.setString(5, client.getAddress());
//            preparedStatement.setString(6, client.getCode());
//            updated = preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return updated;
//    }


    public boolean update(Client client) {
        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error when trying to update: " + e.getMessage());
            return false;
        }
    }


    /**
     * @return
     */
//    @Override
//    public Optional<List<Client>> findAll() {
//
//        List<Client> clients = new ArrayList<>();
//
//        String sql = "SELECT * FROM client";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                Client client = new Client();
//                client.setCode(rs.getString(1));
//                client.setFirstName(rs.getString(2));
//                client.setLastName(rs.getString(3));
//                client.setBirthDate(rs.getDate(4).toLocalDate());
//                client.setPhoneNumber(rs.getString(5));
//                client.setAddress(rs.getString(6));
//                clients.add(client);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return Optional.of(clients);
//    }

    @Override
    public Optional<List<Client>> findAll() {

        List<Client> clients = new ArrayList<>();
        try (Session session = HibernateHelper.getSessionFactory().openSession()){
            clients = session.createQuery("FROM Client", Client.class).list();
        } catch (Exception e) {
            System.out.println("error message: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.of(clients);
    }


    /**
     * @param address
     * @return
     */
    @Override
    public Optional<Client> findByAdress(String address) {
        Client client = new Client();
        String sql = "SELECT * FROM client WHERE address = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                client.setCode(rs.getString(1));
                client.setFirstName(rs.getString(2));
                client.setLastName(rs.getString(3));
                client.setBirthDate(rs.getDate(4).toLocalDate());
                client.setPhoneNumber(rs.getString(5));
                client.setAddress(rs.getString(6));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(client);
    }

//    @Override
//    public Optional<Client> findByAdress(String address) {
//        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
//            Root<Client> root = query.from(Client.class);
//            query.select(root).where(criteriaBuilder.equal(root.get("address"), address));
//
//            Client client = session.createQuery(query).uniqueResult();
//
//            if (client != null) {
//                return Optional.of(client);
//            } else {
//                return Optional.empty();
//            }
//        } catch (Exception e) {
//            System.out.println("Error when trying to find by address: " + e.getMessage());
//            return Optional.empty();
//        }
//    }

}
