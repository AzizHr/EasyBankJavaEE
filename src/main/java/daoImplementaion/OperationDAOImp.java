package daoImplementaion;

import dao.IOperationDAO;
import database.Database;
import entities.CurrentAccount;
import entities.Operation;
import enums.paymentType;

import java.sql.*;
import java.util.Optional;

public class OperationDAOImp implements IOperationDAO<Operation> {

    private static final Connection connection = Database.getInstance().getConnection();
    private static final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
    private static final CurrentAccountDAOImp currentAccountDAOImp = new CurrentAccountDAOImp();
    private static final SavingAccountDAOImp savingAccountDAOImp = new SavingAccountDAOImp();

    /**
     * @param operation
     * @return
     */
    @Override
    public Optional<Operation> saveForCA(Operation operation) {
        String sql = "INSERT INTO operation (number, created_at, price, payment, employee_code, current_account_number) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, operation.getNumber());
            preparedStatement.setObject(2, operation.getCreatedAt());
            preparedStatement.setDouble(3, operation.getPrice());
            preparedStatement.setObject(4, operation.getPayment(), Types.OTHER);
            preparedStatement.setString(5, operation.getEmployee().getCode());
            preparedStatement.setString(6, operation.getAccount().getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(operation);
    }

    /**
     * @param operation
     * @return
     */
    @Override
    public Optional<Operation> saveForSA(Operation operation) {
        String sql = "INSERT INTO operation (number, created_at, price, payment, employee_code, saving_account_number) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, operation.getNumber());
            preparedStatement.setObject(2, operation.getCreatedAt());
            preparedStatement.setDouble(3, operation.getPrice());
            preparedStatement.setObject(4, operation.getPayment(), Types.OTHER);
            preparedStatement.setString(5, operation.getEmployee().getCode());
            preparedStatement.setString(6, operation.getAccount().getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(operation);
    }

    /**
     * @param number
     * @return
     */
    @Override
    public boolean delete(String number) {
        boolean deleted = false;
        String sql = "DELETE FROM operation WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error when trying to delete");
        }
        return deleted;
    }

    /**
     * @param number
     * @return
     */
    @Override
    public Optional<Operation> findByNumber(String number) {
        Operation operation = new Operation();
        String sql = "SELECT * FROM operation WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                operation.setNumber(rs.getString(1));
                operation.setCreatedAt(rs.getTimestamp(2).toLocalDateTime());
                operation.setPrice(rs.getDouble(3));
                operation.setPayment((paymentType) rs.getObject(4) );
                operation.setEmployee(employeeDAOImp.findByCode(rs.getString(5)).get());
                operation.setAccount(currentAccountDAOImp.findByNumber(rs.getString(6)).get());
                operation.setAccount(savingAccountDAOImp.findByNumber(rs.getString(6)).get());
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(operation);
    }
}
