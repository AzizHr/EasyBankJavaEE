package services;

import daoImplementaion.DemandDAOImp;
import entities.Demand;
import entities.Simulation;
import enums.DemandStatus;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DemandService {

    public  DemandDAOImp demandDAOImp;

    public DemandService(DemandDAOImp instance) {
        demandDAOImp = instance;
    }

    public  Simulation save(Demand demand) {

        return demandDAOImp.save(demand).orElse(null);
    }

    public  boolean delete(String code) {

        try {
            if(demandDAOImp.delete(code)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<Simulation> findAll() {

        return demandDAOImp.findAll().orElse(Collections.emptyList());
    }

    public  Simulation findByCode(String number) {

        try {

            Optional<Simulation> demandOptional= demandDAOImp.findByNumber(number);

            if (demandOptional.isPresent()) {
                return demandOptional.get();
            } else {
                throw new Exception("No Demand With This Number Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean update(DemandStatus status, String number) {

        try {
            if(demandDAOImp.updateStatus(status, number)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Update Status!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
