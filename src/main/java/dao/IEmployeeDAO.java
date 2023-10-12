package dao;

import entities.Employee;
import entities.Person;

import java.util.Optional;

public interface IEmployeeDAO<T> extends IPersonDAO<T> {
    Optional<Employee> findByPhoneNumber(String phoneNumber);
}
