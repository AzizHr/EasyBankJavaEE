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

    public  boolean delete(int number) {

        return demandDAOImp.delete(number);
    }

    public  List<Simulation> findAll() {

        return demandDAOImp.findAll().orElse(Collections.emptyList());
    }

    public  Simulation findByNumber(int number) {

        return demandDAOImp.findByNumber(number).orElse(null);
    }

    public  boolean updateStatus(DemandStatus status, int number) {

        return demandDAOImp.updateStatus(status, number);
    }

    public double calculatePaidMonthly(double price, int duration) {

        return (price * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -duration));

    }

}
