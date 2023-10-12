package dao;

import entities.Payment;

import java.util.Optional;

public interface IPaymentDAO<T> {

    Optional<T> saveForCA(T t);
    Optional<T> saveForSA(T t);
    boolean delete(String number);
    Optional<T> findByNumber(String number);
}
