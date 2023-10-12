package services;

import daoImplementaion.PaymentDAOImp;
import entities.Operation;
import entities.Payment;
import java.util.Optional;

public class PaymentService {

    public  PaymentDAOImp paymentDAOImp;

    public PaymentService(PaymentDAOImp instance) {
        paymentDAOImp = instance;
    }

    public  Operation saveForCA(Payment payment) {

        try {
            Optional<Payment> paymentOptional = paymentDAOImp.saveForCA(payment);

            if (paymentOptional.isPresent()) {
                return paymentOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  Operation saveForSA(Payment payment) {

        try {
            Optional<Payment> paymentOptional = paymentDAOImp.saveForSA(payment);

            if (paymentOptional.isPresent()) {
                return paymentOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String number) {

        try {
            if(paymentDAOImp.delete(number)) {
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

            Optional<Payment> paymentOptional = paymentDAOImp.findByNumber(number);

            if (paymentOptional.isPresent()) {
                return paymentOptional.get();
            } else {
                throw new Exception("No Payment With This Number Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
