package daoImplementaion;

import dao.IMissionDAO;
import database.Database;
import entities.Employee;
import entities.Mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MissionDAOImp implements IMissionDAO<Mission> {

    private static final Connection connection = Database.getInstance().getConnection();

    /**
     * @param mission 
     * @return
     */
    @Override
    public Optional<Mission> save(Mission mission) {
        String sql = "INSERT INTO mission (code, name, description) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mission.getCode());
            preparedStatement.setString(2, mission.getName());
            preparedStatement.setString(3, mission.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(mission);
    }

    /**
     * @param code 
     * @return
     */
    @Override
    public boolean delete(String code) {
        boolean deleted = false;
        String sql = "DELETE FROM mission WHERE code = ?";

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
    public Optional<List<Mission>> findAll() {

        List<Mission> missions = new ArrayList<>();

        String sql = "SELECT * FROM mission";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mission mission = new Mission();
                mission.setCode(rs.getString(1));
                mission.setName(rs.getString(2));
                mission.setDescription(rs.getString(3));
                missions.add(mission);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(missions);
    }

    /**
     * @param code 
     * @return
     */
    @Override
    public Optional<Mission> findByCode(String code) {
        Mission mission = new Mission();
        String sql = "SELECT * FROM mission WHERE code = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                mission.setCode(rs.getString(1));
                mission.setName(rs.getString(2));
                mission.setDescription(rs.getString(3));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println("Error when trying to select");
        }
        return Optional.of(mission);
    }
}
