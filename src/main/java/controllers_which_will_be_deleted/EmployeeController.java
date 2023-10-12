package controllers_which_will_be_deleted;

import entities.Agency;
import entities.Employee;
import entities.RecruitmentHistory;
import services.AgencyService;
import services.EmployeeService;
import services.RecruitmentHistoryService;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeController {

    private final EmployeeService employeeService;
    private final AgencyService agencyService;
    private final RecruitmentHistoryService recruitmentHistoryService;
    private static final Scanner scanner = new Scanner(System.in);

    public EmployeeController(EmployeeService instance1, AgencyService instance2, RecruitmentHistoryService instance3) {
        employeeService = instance1;
        agencyService = instance2;
        recruitmentHistoryService = instance3;
    }

    public void save() {

        Employee employee = new Employee();
        System.out.print("Enter The Employee Code -> ");
        String code = scanner.next();
        employee.setCode(code);
        System.out.print("Enter The Employee First Name -> ");
        String firstName = scanner.next();
        employee.setFirstName(firstName);
        System.out.print("Enter The Employee Last Name -> ");
        String lastName = scanner.next();
        employee.setLastName(lastName);
        LocalDate birthDate = LocalDate.of(2001, 2, 2);
        employee.setBirthDate(birthDate);
        System.out.print("Enter The Employee Phone Number -> ");
        String phoneNumber = scanner.next();
        employee.setPhoneNumber(phoneNumber);
        System.out.print("Enter The Employee Email -> ");
        String email = scanner.next();
        employee.setEmail(email);
        LocalDate recruitedAt = LocalDate.of(2024, 2, 2);
        System.out.println(employeeService.save(employee));

    }

    public void findAll() {
        employeeService.findAll();
    }

    public void findByCode() {
        System.out.print("Enter The Code Of The Employee You're Looking For -> ");
        String code = scanner.next();
        System.out.println(employeeService.findByCode(code));
    }

    public void update() {

        System.out.print("Enter The Code Of The Employee You Wanna Update -> ");
        String code = scanner.next();
        if(employeeService.findByCode(code) != null) {
            System.out.println("Enter The New Information");
            Employee employee = new Employee();
            System.out.print("Enter The Employee First Name -> ");
            String firstName = scanner.next();
            employee.setFirstName(firstName);
            System.out.print("Enter The Employee Last Name -> ");
            String lastName = scanner.next();
            employee.setLastName(lastName);
            LocalDate birthDate = LocalDate.of(2001, 2, 2);
            employee.setBirthDate(birthDate);
            System.out.print("Enter The Employee Phone Number -> ");
            String phoneNumber = scanner.next();
            employee.setPhoneNumber(phoneNumber);
            System.out.print("Enter The Employee Email -> ");
            String email = scanner.next();
            employee.setEmail(email);
            employee.setCode(code);
            employeeService.update(employee);
        }

    }

    public void findByPhoneNumber() {

        System.out.print("Enter The Phone Number Of The Employee You're Looking For -> ");
        String empPhoneNumber = scanner.next();
        System.out.println(employeeService.findByPhoneNumber(empPhoneNumber));

    }

    public void delete() {

        System.out.print("Enter The Code Of The Employee You Wanna Delete -> ");
        String clientCodeToDelete = scanner.next();
        if(employeeService.delete(clientCodeToDelete)) {
            System.out.println("Deleted With Success!");
        }

    }

    public void assignToAnAgency() {

        System.out.print("Enter The Code Of The Employee You Wanna Assign To An Agency -> ");
        String employeeCode = scanner.next();
        Employee employee = employeeService.findByCode(employeeCode);
        System.out.print("Enter The Code Of The Agency -> ");
        String agencyCode = scanner.next();
        Agency agency = agencyService.findByCode(agencyCode);
        LocalDate recruitedAt = LocalDate.now();
        LocalDate finishedAt = recruitedAt.plusYears(2);
        RecruitmentHistory recruitmentHistory = new RecruitmentHistory();
        recruitmentHistory.setAgency(agency);
        recruitmentHistory.setEmployee(employee);
        recruitmentHistory.setRecruitedAt(recruitedAt);
        recruitmentHistory.setFinishedAt(finishedAt);
        System.out.println(recruitmentHistoryService.assignAnEmployeeToAnAgency(recruitmentHistory));

    }

    public void assignToAnotherAgency() {

        System.out.print("Enter The Code Of The Employee You Wanna Assign To Another Agency -> ");
        String employeeCode = scanner.next();
        Employee employee = employeeService.findByCode(employeeCode);
        System.out.print("Enter The Code Of The Agency -> ");
        String agencyCode = scanner.next();
        Agency agency = agencyService.findByCode(agencyCode);
        LocalDate recruitedAt = LocalDate.now();
        LocalDate finishedAt = recruitedAt.plusYears(2);
        RecruitmentHistory recruitmentHistory = new RecruitmentHistory();
        recruitmentHistory.setAgency(agency);
        recruitmentHistory.setEmployee(employee);
        recruitmentHistory.setRecruitedAt(recruitedAt);
        recruitmentHistory.setFinishedAt(finishedAt);
        System.out.println(recruitmentHistoryService.assignAnEmployeeToAnAgency(recruitmentHistory));

    }

}
