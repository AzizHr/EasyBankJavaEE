package daoImplementaion;

import controllers.HibernateUtil;
import dao.IEmployeeDAO;
import database.Database;
import entities.Client;
import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EmployeeDAOImp implements IEmployeeDAO<Employee> {

    private static final Connection connection = Database.getInstance().getConnection();
    private static final SessionFactory sessionFactory = HibernateUtil.grabSessionFactory();
    /**
     * @param code
     * @return
     */
    @Override
    public Optional<Employee> findByCode(String code) {
        Employee employee = new Employee();
        String sql = "SELECT * FROM employee WHERE code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                employee.setCode(rs.getString(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setBirthDate(rs.getDate(4).toLocalDate());
                employee.setPhoneNumber(rs.getString(5));
                employee.setEmail(rs.getString(6));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(employee);
    }

    /**
     * @param employee 
     * @return
     */
    @Override
    public Optional<Employee> save(Employee employee) {
        String sql = "INSERT INTO employee (code, first_name, last_name, birth_date, phone_number, email) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getCode());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setObject(4, employee.getBirthDate());
            preparedStatement.setString(5, employee.getPhoneNumber());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(employee);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public boolean delete(String code) {
        boolean deleted = false;
        String sql = "DELETE FROM employee WHERE code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error when trying to delete");
        }
        return deleted;
    }

    /**
     * @param employee 
     * @return
     */
    @Override
    public boolean update(Employee employee) {

        boolean updated = false;

        String sql = "UPDATE employee SET first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, email = ? WHERE code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setObject(3, employee.getBirthDate());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setString(6, employee.getCode());
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updated;
    }

    /**
     * @return 
     */
    @Override
    public Optional<List<Employee>> findAll() {

//        List<Employee> employees = new ArrayList<>();
//
//        String sql = "SELECT * FROM employee";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                Employee employee = new Employee();
//                employee.setCode(rs.getString(1));
//                employee.setFirstName(rs.getString(2));
//                employee.setLastName(rs.getString(3));
//                employee.setBirthDate(rs.getDate(4).toLocalDate());
//                employee.setPhoneNumber(rs.getString(5));
//                employee.setEmail(rs.getString(6));
//                employees.add(employee);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return Optional.of(employees);

        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Employee";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            return Optional.of(query.list());
        }
    }

    /**
     * @param phoneNumber 
     * @return
     */
    @Override
    public Optional<Employee> findByPhoneNumber(String phoneNumber) {
        Employee employee = new Employee();
        String sql = "SELECT * FROM employee WHERE phone_number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                employee.setCode(rs.getString(1));
                employee.setFirstName(rs.getString(2));
                employee.setLastName(rs.getString(3));
                employee.setBirthDate(rs.getDate(4).toLocalDate());
                employee.setPhoneNumber(rs.getString(5));
                employee.setEmail(rs.getString(6));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(employee);
    }
}
