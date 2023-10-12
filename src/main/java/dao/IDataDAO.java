package dao;

import enums.accountStatus;

import java.util.List;
import java.util.Optional;

public interface IDataDAO<T> {

    Optional<T> save(T t);
    boolean delete(String ID);
    Optional<List<T>> findAll();
}
