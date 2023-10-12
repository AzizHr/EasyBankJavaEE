package services;

import daoImplementaion.MissionHistoryDAOImp;
import entities.MissionHistory;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MissionHistoryService {

    private  MissionHistoryDAOImp missionHistoryDAOImp;

    public MissionHistoryService(MissionHistoryDAOImp instance) {
        missionHistoryDAOImp = instance;
    }

    public  MissionHistory save(MissionHistory missionHistory) {

        try {
            Optional<MissionHistory> missionHistoryOptional = missionHistoryDAOImp.save(missionHistory);

            if (missionHistoryOptional.isPresent()) {
                return missionHistoryOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String employeeCode, String missionCode, LocalDate startedAt) {

        try {
            if(missionHistoryDAOImp.delete(employeeCode, missionCode, startedAt)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<MissionHistory> findAll() {

        try {
            List<MissionHistory> missionHistories = missionHistoryDAOImp.findAll().orElse(Collections.emptyList());

            if (missionHistories.isEmpty()) {
                throw new Exception("No Mission Histories Found!");
            } else {
                return missionHistories;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
