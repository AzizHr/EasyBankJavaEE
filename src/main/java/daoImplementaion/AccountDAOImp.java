package daoImplementaion;

import dao.IAccountDAO;
import entities.Account;
import entities.Person;
import enums.accountStatus;

import java.util.List;
import java.util.Optional;

public class AccountDAOImp implements IAccountDAO<Account> {

    /**
     * @param account 
     * @return
     */
    @Override
    public Optional<Account> save(Account account) {
        return Optional.empty();
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public boolean delete(String number) {
        return false;
    }

    /**
     * @param account 
     * @return
     */
    @Override
    public boolean update(Account account) {
        return false;
    }

    /**
     * @param status 
     * @return
     */
    @Override
    public boolean updateStatus(accountStatus status, String number) {
        return false;
    }

    /**
     * @return 
     */
    @Override
    public Optional<List<Account>> findAll() {
        return Optional.empty();
    }

    /**
     * @param code 
     * @return
     */
    @Override
    public Optional<List<Account>> findByClient(String code) {
        return Optional.empty();
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public Optional<Account> findByNumber(String number) {
        return Optional.empty();
    }

    /**
     * @param number 
     * @return
     */
    @Override
    public Optional<Account> findByOperationNumber(String number) {
        return Optional.empty();
    }

    /**
     * @param balance 
     * @param number
     * @return
     */
    @Override
    public boolean deposit(double balance, String number) {
        return false;
    }

    /**
     * @param balance 
     * @param number
     * @return
     */
    @Override
    public boolean withdraw(double balance, String number) {
        return false;
    }
}
