package daoImplementaion;

import controllers.HibernateHelper;
import dao.IDemandDAO;
import database.Database;
import entities.Demand;
import entities.Employee;
import entities.Simulation;
import enums.DemandStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DemandDAOImp implements IDemandDAO<Simulation> {

    private static final Connection connection = Database.getInstance().getConnection();

    /**
     * @param simulation 
     * @return
     */
    @Override
    public Optional<Simulation> save(Simulation simulation) {
        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(simulation);
            transaction.commit();
            return Optional.of(simulation);
        } catch (Exception e) {
            System.out.println("Error when trying to save: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param number
     * @return
     */
    @Override
    public boolean delete(int number) {
        boolean deleted = false;

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Simulation demand = session.createQuery("FROM Demand WHERE number = :number", Demand.class)
                    .setParameter("number", number)
                    .uniqueResult();

            if (demand != null) {
                session.delete(demand);
                deleted = true;
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error when trying to delete: " + e.getMessage());
        }
        return deleted;
    }

    /**
     * @return 
     */
    @Override
    public Optional<List<Simulation>> findAll() {
        List<Simulation> demands = new ArrayList<>();
        try (Session session = HibernateHelper.getSessionFactory().openSession()){
            demands = session.createQuery("FROM Demand", Simulation.class).list();
        } catch (Exception e) {
            System.out.println("error message: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.of(demands);
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public Optional<Simulation> findByNumber(int number) {

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            String hql = "FROM Demand D WHERE D.number = :number";
            Query<Demand> query = session.createQuery(hql, Demand.class);
            query.setParameter("number", number);
            Demand demand = query.uniqueResult();

            return Optional.of(demand);
        } catch (Exception e) {
            System.out.println("Error when trying to select: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * @param status 
     * @param number
     * @return
     */
    @Override
    public boolean updateStatus(DemandStatus status, int number) {
        boolean updated = false;

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Demand> query = session.createQuery("FROM Demand WHERE number = :number", Demand.class);
            query.setParameter("number", number);

            List<Demand> results = query.list();

            if (!results.isEmpty()) {
                Demand demand = results.get(0);
                demand.setStatus(status);
                session.saveOrUpdate(demand);
                transaction.commit();
                updated = true;
            } else {
                transaction.rollback();
            }
        } catch (HibernateException e) {
            throw new RuntimeException("Failed to update demand status", e);
        }

        return updated;
    }
}
