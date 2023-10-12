package services;

import daoImplementaion.DemandDAOImp;
import entities.Demand;
import entities.Simulation;
import enums.demandStatus;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DemandService {

    public  DemandDAOImp demandDAOImp;

    public DemandService(DemandDAOImp instance) {
        demandDAOImp = instance;
    }

    public  Simulation save(Demand demand) {

        try {
            Optional<Simulation> demandOptional = demandDAOImp.save(demand);

            if (demandOptional.isPresent()) {
                return demandOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

        try {
            List<Simulation> demands = demandDAOImp.findAll().orElse(Collections.emptyList());

            if (demands.isEmpty()) {
                throw new Exception("No Demands Found!");
            } else {
                return demands;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public  boolean update(demandStatus status, String number) {

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
