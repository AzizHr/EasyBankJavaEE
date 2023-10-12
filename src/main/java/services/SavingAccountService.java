package services;

import daoImplementaion.SavingAccountDAOImp;
import entities.SavingAccount;
import enums.accountStatus;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SavingAccountService {

    private  SavingAccountDAOImp savingAccountDAOImp;

    public SavingAccountService(SavingAccountDAOImp instance){
        savingAccountDAOImp = instance;
    }


    public  SavingAccount save(SavingAccount savingAccount) {

        try {
            Optional<SavingAccount> savingAccountOptional = savingAccountDAOImp.save(savingAccount);

            if (savingAccountOptional.isPresent()) {
                return savingAccountOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String code) {

        try {
            if(savingAccountDAOImp.delete(code)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Delete!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<SavingAccount> findAll() {

        try {
            List<SavingAccount> savingAccounts = savingAccountDAOImp.findAll().orElse(Collections.emptyList());

            if (savingAccounts.isEmpty()) {
                throw new Exception("No Current Accounts Found!");
            } else {
                return savingAccounts;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  SavingAccount findByNumber(String number) {

        try {

            Optional<SavingAccount> savingAccountOptional = savingAccountDAOImp.findByNumber(number);

            if (savingAccountOptional.isPresent()) {
                return savingAccountOptional.get();
            } else {
                throw new Exception("No Current Account With This Number Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  List<SavingAccount> findByClient(String clientCode) {

        try {
            List<SavingAccount> currentAccounts = savingAccountDAOImp.findAll().orElse(Collections.emptyList());

            if (currentAccounts.isEmpty()) {
                throw new Exception("No Current Accounts Found!");
            } else {
                return currentAccounts;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  SavingAccount findByOperationNumber(String operationNumber) {

        try {

            Optional<SavingAccount> savingAccountOptional = savingAccountDAOImp.findByOperationNumber(operationNumber);

            if (savingAccountOptional.isPresent()) {
                return savingAccountOptional.get();
            } else {
                throw new Exception("No Saving Account With This Operation Number Found!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  boolean update(SavingAccount savingAccount) {

        try {
            if(savingAccountDAOImp.update(savingAccount)) {
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
            if(savingAccountDAOImp.updateStatus(status, number)) {
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
            if(savingAccountDAOImp.deposit(balance, number)) {
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
            if(savingAccountDAOImp.withdraw(balance, number)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Withdraw!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
