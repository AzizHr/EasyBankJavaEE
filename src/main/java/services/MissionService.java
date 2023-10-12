package services;

import daoImplementaion.MissionDAOImp;
import entities.Mission;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MissionService {

    private  MissionDAOImp missionDAOImp;

    public MissionService(MissionDAOImp instance) {
        missionDAOImp= instance;
    }

    public  Mission findByCode(String code) {

        try {

            Optional<Mission> missionOptional = missionDAOImp.findByCode(code);

            if (missionOptional.isPresent()) {
                return missionOptional.get();
            } else {
                throw new Exception("No Mission With This Code Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Mission save(Mission mission) {


        try {
            Optional<Mission> missionOptional = missionDAOImp.save(mission);

            if (missionOptional.isPresent()) {
                return missionOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String code) {

        try {
            if(missionDAOImp.delete(code)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<Mission> findAll() {
        try {
            List<Mission> missions = missionDAOImp.findAll().orElse(Collections.emptyList());

            if (missions.isEmpty()) {
                throw new Exception("No Missions Found!");
            } else {
                return missions;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
