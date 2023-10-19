package daoImplementaion;

import dao.IDemandDAO;
import database.Database;
import entities.Demand;
import entities.Employee;
import entities.Simulation;
import enums.demandStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;
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
        String sql = "INSERT INTO demand (number, date, status, price, duration) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, simulation.getNumber());
            preparedStatement.setObject(2, simulation.getCreateAt());
            preparedStatement.setObject(3, simulation.getStatus(), Types.OTHER);
            preparedStatement.setDouble(4, simulation.getPrice());
            preparedStatement.setInt(5, simulation.getDuration());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(simulation);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public boolean delete(String code) {
        return false;
    }

    /**
     * @return 
     */
    @Override
    public Optional<List<Simulation>> findAll() {
        return Optional.empty();
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public Optional<Simulation> findByNumber(String number) {
        Demand demand = new Demand();
        String sql = "SELECT * FROM demand WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                demand.setNumber(rs.getString(1));
                demand.setCreateAt(rs.getDate(2).toLocalDate());
                demand.setStatus(demandStatus.valueOf(rs.getString(3)));
                demand.setPrice(rs.getDouble(4));
                demand.setDuration(rs.getInt(5));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(demand);
    }

    /**
     * @param status 
     * @param number
     * @return
     */
    @Override
    public boolean updateStatus(demandStatus status, String number) {
        boolean updated = false;
        String sql = "UPDATE demand SET status = ? WHERE number = ?";

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
}
