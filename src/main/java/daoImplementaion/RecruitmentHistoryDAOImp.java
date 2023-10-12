package daoImplementaion;

import dao.IRecruitmentHistoryDAO;
import database.Database;
import entities.Agency;
import entities.RecruitmentHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecruitmentHistoryDAOImp implements IRecruitmentHistoryDAO<RecruitmentHistory> {

    private static final Connection connection = Database.getInstance().getConnection();
    private static final AgencyDAOImp agencyDAOImp = new AgencyDAOImp();
    private static final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
    /**
     * @param recruitmentHistory
     * @return
     */
    @Override
    public Optional<RecruitmentHistory> assignAnEmployeeToAnAgency(RecruitmentHistory recruitmentHistory) {
        String sql = "INSERT INTO recruitment_history (agency_code, employee_code, started_at, finished_at) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recruitmentHistory.getAgency().getCode());
            preparedStatement.setString(2, recruitmentHistory.getEmployee().getCode());
            preparedStatement.setObject(3, recruitmentHistory.getRecruitedAt());
            preparedStatement.setObject(4, recruitmentHistory.getFinishedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(recruitmentHistory);
    }

    /**
     * @return
     */
    @Override
    public Optional<List<RecruitmentHistory>> findAll() {

        List<RecruitmentHistory> recruitmentHistories = new ArrayList<>();

        String sql = "SELECT * FROM recruitment_history";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RecruitmentHistory recruitmentHistory = new RecruitmentHistory();
                recruitmentHistory.setAgency(agencyDAOImp.findByCode(rs.getString(1)).get());
                recruitmentHistory.setEmployee(employeeDAOImp.findByCode(rs.getString(2)).get());
                recruitmentHistory.setRecruitedAt(rs.getDate(3).toLocalDate());
                recruitmentHistory.setFinishedAt(rs.getDate(4).toLocalDate());
                recruitmentHistories.add(recruitmentHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(recruitmentHistories);

    }
}
