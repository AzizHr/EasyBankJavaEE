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

    public  boolean delete(String number) {

        return demandDAOImp.delete(number);
    }

    public  List<Simulation> findAll() {

        return demandDAOImp.findAll().orElse(Collections.emptyList());
    }

    public  Simulation findByCode(int number) {

        return demandDAOImp.findByNumber(number).orElse(null);
    }

    public  boolean update(DemandStatus status, String number) {

        return demandDAOImp.updateStatus(status, number);
    }

}
