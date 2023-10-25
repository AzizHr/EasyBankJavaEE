package dao;

import enums.DemandStatus;

import java.util.Optional;

public interface IDemandDAO<T> extends IDataDAO<T> {

    Optional<T> findByNumber(int number);
    boolean updateStatus(DemandStatus status, String number);

}
