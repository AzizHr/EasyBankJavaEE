package daoImplementaion;

import dao.IAgencyDAO;
import database.Database;
import entities.Agency;
import entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgencyDAOImp implements IAgencyDAO<Agency> {

    private static final Connection connection = Database.getInstance().getConnection();
    /**
     * @param agency 
     * @return
     */
    @Override
    public Optional<Agency> save(Agency agency) {
        String sql = "INSERT INTO agency (code, name, address, phone_number) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, agency.getCode());
            preparedStatement.setString(2, agency.getName());
            preparedStatement.setString(3, agency.getAdress());
            preparedStatement.setString(4, agency.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(agency);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public boolean delete(String code) {
        boolean deleted = false;
        String sql = "DELETE FROM agency WHERE code = ?";

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
     * @return 
     */
    @Override
    public Optional<List<Agency>> findAll() {
        List<Agency> agencies = new ArrayList<>();

        String sql = "SELECT * FROM agency";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Agency agency = new Agency();
                agency.setCode(rs.getString(1));
                agency.setName(rs.getString(2));
                agency.setAdress(rs.getString(3));
                agency.setPhoneNumber(rs.getString(4));
                agencies.add(agency);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(agencies);
    }

    /**
     * @param agency 
     * @return
     */
    @Override
    public boolean update(Agency agency) {
        // UPDATE
        boolean updated = false;

        String sql = "UPDATE agency SET name = ?, adress = ?, phone_number = ? WHERE code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, agency.getName());
            preparedStatement.setString(2, agency.getAdress());
            preparedStatement.setObject(3, agency.getPhoneNumber());
            preparedStatement.setString(4, agency.getCode());
            updated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updated;
    }

    /**
     * @param code 
     * @return
     */
    @Override
    public Optional<Agency> findByCode(String code) {
        Agency agency = new Agency();
        String sql = "SELECT * FROM agency WHERE code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                agency.setCode(rs.getString(1));
                agency.setName(rs.getString(2));
                agency.setAdress(rs.getString(3));
                agency.setPhoneNumber(rs.getString(4));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(agency);
    }

    /**
     * @param adress 
     * @return
     */
    @Override
    public Optional<Agency> findByAdress(String adress) {
        // Find By Adress
        Agency agency = new Agency();
        String sql = "SELECT * FROM agency WHERE adress = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adress);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                agency.setCode(rs.getString(1));
                agency.setName(rs.getString(2));
                agency.setAdress(rs.getString(3));
                agency.setPhoneNumber(rs.getString(4));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(agency);
    }

    /**
     * @param employeeCode
     * @return
     */
    @Override
    public Optional<Agency> findByEmployee(String employeeCode) {

        Agency agency= new Agency();
        String sql = "SELECT * FROM agency WHERE code IN (SELECT code FROM employee WHERE code = ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeCode);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                agency.setCode(rs.getString(1));
                agency.setName(rs.getString(2));
                agency.setAdress(rs.getString(3));
                agency.setPhoneNumber(rs.getString(4));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(agency);
    }
}
