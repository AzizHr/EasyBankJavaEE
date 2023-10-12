package daoImplementaion;

import dao.ICurrentAccountDAO;
import database.Database;
import entities.Client;
import entities.CurrentAccount;
import entities.Employee;
import entities.Person;
import enums.accountStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrentAccountDAOImp implements ICurrentAccountDAO<CurrentAccount> {

    private static final Connection connection = Database.getInstance().getConnection();
    private static final AgencyDAOImp agencyDAOImp = new AgencyDAOImp();
    private static final ClientDAOImp clientDAOImp = new ClientDAOImp();
    private static final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();

    /**
     * @param currentAccount 
     * @return
     */
    @Override
    public Optional<CurrentAccount> save(CurrentAccount currentAccount) {

        String sql = "INSERT INTO current_account (number, balance, created_at, account_status, overdraft, agency_code, client_code, employee_code) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currentAccount.getNumber());
            preparedStatement.setDouble(2, currentAccount.getBalance());
            preparedStatement.setObject(3, currentAccount.getCreatedAt());
            preparedStatement.setObject(4, accountStatus.Active, Types.OTHER);
            preparedStatement.setDouble(5, currentAccount.getOverdraft());
            preparedStatement.setString(6, currentAccount.getAgency().getCode());
            preparedStatement.setString(7, currentAccount.getClient().getCode());
            preparedStatement.setString(8, currentAccount.getEmployee().getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(currentAccount);
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public boolean delete(String number) {
        boolean deleted = false;
        String sql = "DELETE FROM current_account WHERE number = ?";

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
     * @param currentAccount 
     * @return
     */
    @Override
    public boolean update(CurrentAccount currentAccount) {
        boolean updated = false;
        String sql = "UPDATE current_account SET balance = ?, overdraft = ? WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, currentAccount.getBalance());
            preparedStatement.setDouble(2, currentAccount.getOverdraft());
            preparedStatement.setString(3, currentAccount.getAgency().getCode());
            preparedStatement.setString(3, currentAccount.getNumber());
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updated;
    }

    /**
     * @param status 
     * @return
     */
    @Override
    public boolean updateStatus(accountStatus status, String number) {
        boolean updated = false;
        String sql = "UPDATE current_account SET account_status = ? WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, status, Types.OTHER);
            preparedStatement.setString(2, number);
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
    public Optional<List<CurrentAccount>> findAll() {
        List<CurrentAccount> currentAccounts = new ArrayList<>();

        String sql = "SELECT * FROM current_account";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setNumber(rs.getString(1));
                currentAccount.setBalance(rs.getDouble(2));
                currentAccount.setCreatedAt(rs.getDate(3).toLocalDate());
                currentAccount.setStatus(accountStatus.valueOf(rs.getString(4)));
                currentAccount.setOverdraft(rs.getDouble(5));
                currentAccount.setAgency(agencyDAOImp.findByCode(rs.getString(6)).get());
                currentAccount.setClient(clientDAOImp.findByCode(rs.getString(7)).get());
                currentAccount.setEmployee(employeeDAOImp.findByCode(rs.getString(8)).get());
                currentAccounts.add(currentAccount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(currentAccounts);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Optional<List<CurrentAccount>> findByClient(String code) {
        List<CurrentAccount> currentAccounts = new ArrayList<>();

        String sql = "SELECT * FROM current_account WHERE client_code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setNumber(rs.getString(1));
                currentAccount.setBalance(rs.getDouble(2));
                currentAccount.setCreatedAt(rs.getDate(3).toLocalDate());
                currentAccount.setStatus(accountStatus.valueOf(rs.getString(4)));
                currentAccount.setOverdraft(rs.getDouble(5));
                currentAccount.setAgency(agencyDAOImp.findByCode(rs.getString(6)).get());
                currentAccount.setClient(clientDAOImp.findByCode(rs.getString(7)).get());
                currentAccount.setEmployee(employeeDAOImp.findByCode(rs.getString(8)).get());
                currentAccounts.add(currentAccount);
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(currentAccounts);
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public Optional<CurrentAccount> findByNumber(String number) {
        CurrentAccount currentAccount = new CurrentAccount();

        String sql = "SELECT * FROM current_account WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                currentAccount.setNumber(rs.getString(1));
                currentAccount.setBalance(rs.getDouble(2));
                currentAccount.setCreatedAt(rs.getDate(3).toLocalDate());
                currentAccount.setStatus(accountStatus.valueOf(rs.getString(4)));
                currentAccount.setOverdraft(rs.getDouble(5));
                currentAccount.setAgency(agencyDAOImp.findByCode(rs.getString(6)).get());
                currentAccount.setClient(clientDAOImp.findByCode(rs.getString(7)).get());
                currentAccount.setEmployee(employeeDAOImp.findByCode(rs.getString(8)).get());
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(currentAccount);
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public Optional<CurrentAccount> findByOperationNumber(String number) {

        CurrentAccount currentAccount = new CurrentAccount();

        String sql = "SELECT * FROM current_account WHERE number IN (SELECT current_account_number FROM operation WHERE number = ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                currentAccount.setNumber(rs.getString(1));
                currentAccount.setBalance(rs.getDouble(2));
                currentAccount.setCreatedAt(rs.getDate(3).toLocalDate());
                currentAccount.setStatus(accountStatus.valueOf(rs.getString(4)));
                currentAccount.setOverdraft(rs.getDouble(5));
                currentAccount.setAgency(agencyDAOImp.findByCode(rs.getString(6)).get());
                currentAccount.setClient(clientDAOImp.findByCode(rs.getString(7)).get());
                currentAccount.setEmployee(employeeDAOImp.findByCode(rs.getString(8)).get());
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(currentAccount);
    }

    /**
     * @param balance 
     * @param number
     * @return
     */
    @Override
    public boolean deposit(double balance, String number) {
        boolean updated = false;
        String sql = "UPDATE current_account SET balance = balance + ? WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, number);
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error when trying to update");
        }
        return updated;
    }

    /**
     * @param balance 
     * @param number
     * @return
     */
    @Override
    public boolean withdraw(double balance, String number) {
        boolean updated = false;
        String sql = "UPDATE current_account SET balance = balance - ? WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, number);
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error when trying to update");
        }
        return updated;
    }
}
