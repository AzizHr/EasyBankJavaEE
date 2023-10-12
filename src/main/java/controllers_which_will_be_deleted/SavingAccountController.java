package controllers_which_will_be_deleted;

import entities.*;
import enums.accountStatus;
import services.*;

import java.time.LocalDate;
import java.util.Scanner;

public class SavingAccountController {

    private final SavingAccountService savingAccountService;
    private final AgencyService agencyService;
    private final EmployeeService employeeService;
    private final ClientService clientService;
    private static final Scanner scanner = new Scanner(System.in);

    public SavingAccountController(SavingAccountService instance1, AgencyService instance2, EmployeeService instance3, ClientService instance4) {
        savingAccountService = instance1;
        agencyService = instance2;
        employeeService = instance3;
        clientService = instance4;
    }

    public void save() {

        System.out.println("Enter The Agency Code That Will Own This Account -> ");
        String createdIn = scanner.next();
        Agency agency = agencyService.findByCode(createdIn);
        System.out.println("Enter The Employee Code Who Will Create This Account -> ");
        String createdBy = scanner.next();
        Employee employee = employeeService.findByCode(createdBy);
        System.out.println("Enter The Client Code Who Will Own This Account -> ");
        String createdFor = scanner.next();
        Client client = clientService.findByCode(createdFor);
        System.out.println("Now Enter The Saving Account Information");
        SavingAccount savingAccount = new SavingAccount();
        System.out.println("Enter The Saving Account Number -> ");
        String number = scanner.next();
        savingAccount.setNumber(number);
        System.out.println("Enter The Saving Account Balance -> ");
        double balance = scanner.nextDouble();
        savingAccount.setBalance(balance);
        LocalDate createdAt = LocalDate.of(2022, 2, 3);
        savingAccount.setCreatedAt(createdAt);
        System.out.println("Enter The Saving Account Interest -> ");
        double interest = scanner.nextDouble();
        savingAccount.setInterest(interest);
        savingAccount.setAgency(agency);
        savingAccount.setClient(client);
        savingAccount.setEmployee(employee);
        System.out.println(savingAccountService.save(savingAccount));

    }

    public void findAll() {
        savingAccountService.findAll();
    }

    public void findByNumber() {
        System.out.print("Enter The Number Of The Saving Account You're Looking For -> ");
        String number = scanner.next();
        System.out.println(savingAccountService.findByNumber(number));
    }

    public void update() {

        SavingAccount savingAccount = new SavingAccount();
        System.out.print("Enter The Number Of The Saving Account You Wanna Update -> ");
        String caNumber = scanner.next();
        if(savingAccountService.findByNumber(caNumber) != null) {


            System.out.println("Enter The New Information");
            System.out.println("Enter The Saving Account Balance -> ");
            double balance = scanner.nextDouble();
            savingAccount.setBalance(balance);
            System.out.println("Enter The Saving Account Interest -> ");
            double interest= scanner.nextDouble();
            savingAccount.setInterest(interest);
            savingAccount.setNumber(caNumber);
            System.out.println(savingAccountService.update(savingAccount));

        }

    }

    public void findByClient() {

        System.out.print("Enter The Client Code -> ");
        String clientCode = scanner.next();
        System.out.println(savingAccountService.findByClient(clientCode));

    }

    public void delete() {

        System.out.print("Enter The Number Of The Saving Account You Wanna Delete -> ");
        String number = scanner.next();
        if(savingAccountService.delete(number)) {
            System.out.println("Deleted With Success!");
        }

    }

    public void updateStatus() {

        System.out.print("Enter The Number Of The Saving Account You Wanna Update Its Status -> ");
        String number = scanner.next();
        if(savingAccountService.findByNumber(number) != null) {
            System.out.println("Choose The Status");
            System.out.println("1- Active");
            System.out.println("2- Frozen");
            System.out.println("3- Closed");
            System.out.print("-> ");
            int statusChoice = scanner.nextInt();
            switch (statusChoice) {
                case 1:
                    savingAccountService.updateStatus(accountStatus.Active, number);
                    break;
                case 2:
                    savingAccountService.updateStatus(accountStatus.Frozen, number);
                    break;
                case 3:
                    savingAccountService.updateStatus(accountStatus.Closed, number);
                    break;
                default:
                    System.out.println("Invalid Option!");
            }
        }

    }

}
