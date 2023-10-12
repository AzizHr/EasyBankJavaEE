package services;

import daoImplementaion.OperationDAOImp;
import entities.Operation;
import java.util.Optional;

public class OperationService {

    private  OperationDAOImp operationDAOImp;

    public OperationService(OperationDAOImp instance) {
        operationDAOImp = instance;
    }


    public  Operation saveForCA(Operation operation) {

        try {
            Optional<Operation> operationOptional = operationDAOImp.saveForCA(operation);

            if (operationOptional.isPresent()) {
                return operationOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  Operation saveForSA(Operation operation) {

        try {
            Optional<Operation> operationOptional = operationDAOImp.saveForSA(operation);

            if (operationOptional.isPresent()) {
                return operationOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String number) {

        try {
            if(operationDAOImp.delete(number)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  Operation findByNumber(String number) {

        try {

            Optional<Operation> operationOptional = operationDAOImp.findByNumber(number);

            if (operationOptional.isPresent()) {
                return operationOptional.get();
            } else {
                throw new Exception("No Operation With This Number Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
