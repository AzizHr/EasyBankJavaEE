package dao;

import enums.DemandStatus;

import java.util.List;
import java.util.Optional;

public interface IDemandDAO<T> {

    Optional<T> save(T t);
    boolean delete(int ID);
    Optional<List<T>> findAll();
    Optional<T> findByNumber(int number);
    boolean updateStatus(DemandStatus status, int number);

}
