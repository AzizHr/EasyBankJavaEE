package daoImplementaion;

import dao.IMissionHistoryDAO;
import database.Database;
import entities.Mission;
import entities.MissionHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MissionHistoryDAOImp implements IMissionHistoryDAO<MissionHistory> {

    private static final Connection connection = Database.getInstance().getConnection();
    private static final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
    private static final MissionDAOImp missionDAOImp = new MissionDAOImp();
    /**
     * @return 
     */
    @Override
    public Optional<List<MissionHistory>> findAll() {
        List<MissionHistory> missionHistories = new ArrayList<>();

        String sql = "SELECT * FROM mission_history";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                MissionHistory missionHistory = new MissionHistory();
                missionHistory.setEmployee(employeeDAOImp.findByCode(rs.getString(1)).get());
                missionHistory.setMission(missionDAOImp.findByCode(rs.getString(2)).get());
                missionHistory.setStartedAt(rs.getDate(3).toLocalDate());
                missionHistory.setEndedAt(rs.getDate(4).toLocalDate());
                missionHistories.add(missionHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(missionHistories);
    }

    /**
     * @param missionHistory 
     * @return
     */
    @Override
    public Optional<MissionHistory> save(MissionHistory missionHistory) {
        String sql = "INSERT INTO mission_history (employee_code, mission_code, started_at, ended_at) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, missionHistory.getEmployee().getCode());
            preparedStatement.setString(2, missionHistory.getMission().getCode());
            preparedStatement.setObject(3, missionHistory.getStartedAt());
            preparedStatement.setObject(4, missionHistory.getEndedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(missionHistory);
    }

    /**
     * @param missionCode 
     * @param employeeCode
     * @param startedAt
     * @return
     */
    @Override
    public boolean delete(String employeeCode, String missionCode, LocalDate startedAt) {
        boolean deleted = false;
        String sql = "DELETE FROM mission_history WHERE employee_code = ? AND mission_code = ? AND started_at = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employeeCode);
            preparedStatement.setString(2, missionCode);
            preparedStatement.setObject(3, startedAt);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error when trying to delete");
        }
        return deleted;
    }
}
