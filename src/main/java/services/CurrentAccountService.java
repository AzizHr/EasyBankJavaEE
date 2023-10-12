package services;

import daoImplementaion.CurrentAccountDAOImp;
import entities.CurrentAccount;
import enums.accountStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CurrentAccountService {

    private  CurrentAccountDAOImp currentAccountDAOImp;

    public CurrentAccountService(CurrentAccountDAOImp instance) {
        currentAccountDAOImp = instance;
    }

    public  CurrentAccount save(CurrentAccount currentAccount) {

        try {
            Optional<CurrentAccount> currentAccountOptional = currentAccountDAOImp.save(currentAccount);

            if (currentAccountOptional.isPresent()) {
                return currentAccountOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String code) {

        try {
            if(currentAccountDAOImp.delete(code)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<CurrentAccount> findAll() {

        try {
            List<CurrentAccount> currentAccounts = currentAccountDAOImp.findAll().orElse(Collections.emptyList());

            if (currentAccounts.isEmpty()) {
                throw new Exception("No Current Accounts Found!");
            } else {
                return currentAccounts;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  CurrentAccount findByNumber(String number) {

        try {

            Optional<CurrentAccount> currentAccountOptional = currentAccountDAOImp.findByNumber(number);

            if (currentAccountOptional.isPresent()) {
                return currentAccountOptional.get();
            } else {
                throw new Exception("No Current Account With This Number Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<CurrentAccount> findByClient(String clientCode) {

        try {
            List<CurrentAccount> currentAccounts = currentAccountDAOImp.findAll().orElse(Collections.emptyList());

            if (currentAccounts.isEmpty()) {
                throw new Exception("No Current Accounts Found!");
            } else {
                return currentAccounts;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  CurrentAccount findByOperationNumber(String operationNumber) {

        try {

            Optional<CurrentAccount> currentAccountOptional = currentAccountDAOImp.findByOperationNumber(operationNumber);

            if (currentAccountOptional.isPresent()) {
                return currentAccountOptional.get();
            } else {
                throw new Exception("No Current Account With This Operation Number Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  boolean update(CurrentAccount currentAccount) {

        try {
            if(currentAccountDAOImp.update(currentAccount)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Update!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean updateStatus(accountStatus status, String number) {

        try {
            if(currentAccountDAOImp.updateStatus(status, number)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Update Status!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  boolean deposit(double balance, String number) {

        try {
            if(currentAccountDAOImp.deposit(balance, number)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Deposit!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean withdraw(double balance, String number) {

        try {
            if(currentAccountDAOImp.withdraw(balance, number)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Withdraw!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
