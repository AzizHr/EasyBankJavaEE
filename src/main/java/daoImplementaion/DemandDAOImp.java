package daoImplementaion;

import controllers.HibernateHelper;
import dao.IDemandDAO;
import database.Database;
import entities.Demand;
import entities.Simulation;
import enums.DemandStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.*;
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
        try (Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(simulation);
            transaction.commit();
            return Optional.of(simulation);
        } catch (Exception e) {
            System.out.println("Error when trying to save: " + e.getMessage());
            return Optional.empty();
        }
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
    public Optional<Simulation> findByNumber(int number) {
        Demand demand = new Demand();
        String sql = "SELECT * FROM demand WHERE number = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                demand.setNumber(rs.getInt(1));
                demand.setCreateAt(rs.getDate(2).toLocalDate());
                demand.setStatus(DemandStatus.valueOf(rs.getString(3)));
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
    public boolean updateStatus(DemandStatus status, String number) {
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
