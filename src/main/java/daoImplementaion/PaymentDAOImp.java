package daoImplementaion;

import dao.IPaymentDAO;
import database.Database;
import entities.Payment;
import enums.paymentType;
import java.sql.*;
import java.util.Optional;

public class PaymentDAOImp implements IPaymentDAO<Payment> {

    private static final Connection connection = Database.getInstance().getConnection();
    private static final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
    private static final CurrentAccountDAOImp currentAccountDAOImp = new CurrentAccountDAOImp();
    private static final SavingAccountDAOImp savingAccountDAOImp = new SavingAccountDAOImp();


    /**
     * @param payment 
     * @return
     */
    @Override
    public Optional<Payment> saveForCA(Payment payment) {
        String sql = "INSERT INTO payment (number, created_at, price, payment, employee_code, source_current_account_number, destination_current_account_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, payment.getNumber());
            preparedStatement.setObject(2, payment.getCreatedAt());
            preparedStatement.setDouble(3, payment.getPrice());
            preparedStatement.setObject(4, payment.getPayment(), Types.OTHER);
            preparedStatement.setString(5, payment.getEmployee().getCode());
            preparedStatement.setString(6, payment.getAccount().getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(payment);
    }

    /**
     * @param payment 
     * @return
     */
    @Override
    public Optional<Payment> saveForSA(Payment payment) {
        String sql = "INSERT INTO operation (number, created_at, price, payment, employee_code, source_saving_account_number, destination_saving_account_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, payment.getNumber());
            preparedStatement.setObject(2, payment.getCreatedAt());
            preparedStatement.setDouble(3, payment.getPrice());
            preparedStatement.setObject(4, payment.getPayment(), Types.OTHER);
            preparedStatement.setString(5, payment.getEmployee().getCode());
            preparedStatement.setString(6, payment.getAccount().getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(payment);
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public boolean delete(String number) {
        // ???
        boolean deleted = false;
        String sql = "DELETE FROM payment WHERE number = ?";

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
    public Optional<Payment> findByNumber(String number) {

        Payment payment = new Payment();
        String sql = "SELECT * FROM payment WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                payment.setNumber(rs.getString(1));
                payment.setCreatedAt(rs.getTimestamp(2).toLocalDateTime());
                payment.setPrice(rs.getDouble(3));
                payment.setPayment((paymentType) rs.getObject(4) );
                payment.setEmployee(employeeDAOImp.findByCode(rs.getString(5)).get());
                payment.setAccount(currentAccountDAOImp.findByNumber(rs.getString(6)).get());
                payment.setAccount(savingAccountDAOImp.findByNumber(rs.getString(7)).get());
                payment.setDestinationAccount(currentAccountDAOImp.findByNumber(rs.getString(6)).get());
                payment.setDestinationAccount(savingAccountDAOImp.findByNumber(rs.getString(7)).get());
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(payment);

    }
}
