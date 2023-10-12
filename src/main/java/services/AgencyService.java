package services;

import daoImplementaion.AgencyDAOImp;
import entities.Agency;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AgencyService {

    public  AgencyDAOImp agencyDAOImp;

    public AgencyService(AgencyDAOImp instance) {
        agencyDAOImp = instance;
    }

    public  Agency save(Agency agency) {

        try {
            Optional<Agency> agencyOptional = agencyDAOImp.save(agency);

            if (agencyOptional.isPresent()) {
                return agencyOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String code) {

        try {
            if(agencyDAOImp.delete(code)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<Agency> findAll() {

        try {
            List<Agency> agencies = agencyDAOImp.findAll().orElse(Collections.emptyList());

            if (agencies.isEmpty()) {
                throw new Exception("No Agencies Found!");
            } else {
                return agencies;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Agency findByCode(String code) {

        try {

            Optional<Agency> agencyOptional = agencyDAOImp.findByCode(code);

            if (agencyOptional.isPresent()) {
                return agencyOptional.get();
            } else {
                throw new Exception("No Agency With This Code Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Agency findByAdress(String adress) {

        try {

            Optional<Agency> agencyOptional = agencyDAOImp.findByAdress(adress);

            if (agencyOptional.isPresent()) {
                return agencyOptional.get();
            } else {
                throw new Exception("No Agency With This Adress Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean update(Agency agency) {

        try {
            if(agencyDAOImp.update(agency)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Update!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
