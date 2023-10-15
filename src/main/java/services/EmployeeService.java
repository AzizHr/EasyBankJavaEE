package services;

import daoImplementaion.EmployeeDAOImp;
import entities.Employee;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private  EmployeeDAOImp employeeDAOImp;

    public EmployeeService(EmployeeDAOImp instance) {
        employeeDAOImp = instance;
    }

    public  Employee save(Employee employee) {

        try {
            Optional<Employee> employeeOptional = employeeDAOImp.save(employee);

            if (employeeOptional.isPresent()) {
                return employeeOptional.get();
            } else {
                throw new Exception("Error When Trying To Insert!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean delete(String code) {

        return employeeDAOImp.delete(code);
    }

    public List<Employee> findAll() {

        return employeeDAOImp.findAll().orElse(Collections.emptyList());
    }

    public  Employee findByCode(String code) {

        Optional<Employee> employeeOptional = employeeDAOImp.findByCode(code);

        return employeeOptional.orElse(null);
    }

    public  Employee findByPhoneNumber(String phoneNumber) {
        Optional<Employee> employeeOptional = employeeDAOImp.findByPhoneNumber(phoneNumber);

        return employeeOptional.orElse(null);
    }

    public  boolean update(Employee employee) {

        try {
            if(employeeDAOImp.update(employee)) {
                return true;
            } else {
                throw new Exception("Error When Trying To Update!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
