package daoImplementaion;

import controllers.HibernateHelper;
import dao.IEmployeeDAO;
import database.Database;
import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jakarta.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeDAOImp implements IEmployeeDAO<Employee> {
    private static final Connection connection = Database.getInstance().getConnection();
    private static final SessionFactory sessionFactory = HibernateHelper.getSessionFactory();

    @Override
    public Optional<Employee> findByCode(String code) {

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            String hql = "FROM Employee E WHERE E.code = :code";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("code", code);
            Employee employee = query.uniqueResult();

            return Optional.of(employee);
        } catch (Exception e) {
            System.out.println("Error when trying to select: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> save(Employee employee) {

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return Optional.of(employee);
        } catch (Exception e) {
            System.out.println("Error when trying to insert: " + e.getMessage());
        }
        return Optional.of(employee);
    }

    @Override
    public boolean delete(String code) {
        boolean deleted = false;

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Employee employee = session.createQuery("FROM Employee WHERE code = :code", Employee.class)
                    .setParameter("code", code)
                    .uniqueResult();

            if (employee != null) {
                session.delete(employee);
                deleted = true;
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error when trying to delete: " + e.getMessage());
        }
        return deleted;
    }

    @Override
    public boolean update(Employee employee) {

        boolean updated = false;

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Employee existingEmployee = session.get(Employee.class, employee.getCode());

            if (existingEmployee != null) {
                existingEmployee.setFirstName(employee.getFirstName());
                existingEmployee.setLastName(employee.getLastName());
                existingEmployee.setBirthDate(employee.getBirthDate());
                existingEmployee.setPhoneNumber(employee.getPhoneNumber());
                existingEmployee.setEmail(employee.getEmail());
                session.update(existingEmployee);
                updated = true;
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error when trying to update: " + e.getMessage());
        }
        return updated;
    }

    @Override
    public Optional<List<Employee>> findAll() {

        List<Employee> employees = new ArrayList<>();
        try (Session session = HibernateHelper.getSessionFactory().openSession()){
            employees = session.createQuery("FROM Employee", Employee.class).list();
        } catch (Exception e) {
            System.out.println("error message: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.of(employees);
    }

    @Override
    public Optional<Employee> findByPhoneNumber(String phoneNumber) {

        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            String hql = "FROM Employee E WHERE E.phoneNumber = :phoneNumber";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("phoneNumber", phoneNumber);
            Employee employee = query.uniqueResult();

            return Optional.of(employee);
        } catch (Exception e) {
            System.out.println("Error when trying to select: " + e.getMessage());
            return Optional.empty();
        }
    }
}
