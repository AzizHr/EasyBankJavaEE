package services;

import daoImplementaion.ClientDAOImp;
import entities.Client;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientService {

    private  ClientDAOImp clientDAOImp;

    public ClientService(ClientDAOImp instance) {
        clientDAOImp = instance;
    }

    public  Client save(Client client) {

        try {
            Optional<Client> clientOptional = clientDAOImp.save(client);

            if (clientOptional.isPresent()) {
                return clientOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String code) {

        try {
            if(clientDAOImp.delete(code)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<Client> findAll() {

        try {
            List<Client> clients = clientDAOImp.findAll().orElse(Collections.emptyList());

            if (clients.isEmpty()) {
                throw new Exception("No Clients Found!");
            } else {
                return clients;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Client findByCode(String code) {

        try {

            Optional<Client> clientOptional = clientDAOImp.findByCode(code);

            if (clientOptional.isPresent()) {
                return clientOptional.get();
            } else {
                throw new Exception("No Client With This Code Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Client findByAdress(String adress) {

        try {

            Optional<Client> clientOptional = clientDAOImp.findByAdress(adress);

            if (clientOptional.isPresent()) {
                return clientOptional.get();
            } else {
                throw new Exception("No Client With This Adress Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean update(Client client) {

        try {
            if(clientDAOImp.update(client)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Update!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
