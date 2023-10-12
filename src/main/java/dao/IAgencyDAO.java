package dao;

import java.util.List;
import java.util.Optional;

public interface IAgencyDAO<T> extends IDataDAO<T> {

    boolean update(T t);
    Optional<T> findByCode(String code);
    Optional<T> findByAdress(String adress);
    Optional<T> findByEmployee(String employeeCode);

}
