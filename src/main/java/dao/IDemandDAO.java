package dao;

import enums.demandStatus;

import java.util.Optional;

public interface IDemandDAO<T> extends IDataDAO<T> {

    Optional<T> findByNumber(String number);
    boolean updateStatus(demandStatus status, String number);

}
