package daoImplementaion;

import dao.IPersonDAO;
import entities.Person;
import java.util.List;
import java.util.Optional;

public class PersonDAOImp implements IPersonDAO<Person> {
    /**
     * @param person
     * @return
     */
    @Override
    public Optional<Person> save(Person person) {
        return Optional.empty();
    }

    /**
     * @param code
     * @return
     */
    @Override
    public boolean delete(String code) {
        return false;
    }

    /**
     * @param person
     * @return
     */
    @Override
    public boolean update(Person person) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public Optional<List<Person>> findAll() {
        return Optional.empty();
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Optional<Person> findByCode(String code) {
        return Optional.empty();
    }
}
