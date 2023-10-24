//package controllers_which_will_be_deleted;
//
//import entities.Agency;
//import entities.Client;
//import entities.CurrentAccount;
//import entities.Employee;
//import enums.accountStatus;
//import services.AgencyService;
//import services.ClientService;
//import services.CurrentAccountService;
//import services.EmployeeService;
//
//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class CurrentAccountController {
//
//    private final CurrentAccountService currentAccountService;
//    private final AgencyService agencyService;
//    private final EmployeeService employeeService;
//    private final ClientService clientService;
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public CurrentAccountController(CurrentAccountService instance1, AgencyService instance2, EmployeeService instance3, ClientService instance4) {
//        currentAccountService = instance1;
//        agencyService = instance2;
//        employeeService = instance3;
//        clientService = instance4;
//    }
//
//    public void save() {
//
//        System.out.println("Enter The Agency Code That Will Own This Account -> ");
//        String createdIn = scanner.next();
//        Agency agency = agencyService.findByCode(createdIn);
//        System.out.println("Enter The Employee Code Who Will Create This Account -> ");
//        String createdBy = scanner.next();
//        Employee employee = employeeService.findByCode(createdBy);
//        System.out.println("Enter The Client Code Who Will Own This Account -> ");
//        String createdFor = scanner.next();
//        Client client = clientService.findByCode(createdFor);
//        System.out.println("Now Enter The Current Account Information");
//        CurrentAccount currentAccount = new CurrentAccount();
//        System.out.println("Enter The Current Account Number -> ");
//        String number = scanner.next();
//        currentAccount.setNumber(number);
//        System.out.println("Enter The Current Account Balance -> ");
//        double balance = scanner.nextDouble();
//        currentAccount.setBalance(balance);
//        LocalDate createdAt = LocalDate.of(2022, 2, 3);
//        currentAccount.setCreatedAt(createdAt);
//        System.out.println("Enter The Current Account Overdraft -> ");
//        double overdraft = scanner.nextDouble();
//        currentAccount.setOverdraft(overdraft);
//        currentAccount.setAgency(agency);
//        currentAccount.setClient(client);
//        currentAccount.setEmployee(employee);
//        System.out.println(currentAccountService.save(currentAccount));
//
//    }
//
//    public void findAll() {
//        currentAccountService.findAll();
//    }
//
//    public void findByNumber() {
//        System.out.print("Enter The Number Of The Current Account You're Looking For -> ");
//        String number = scanner.next();
//        System.out.println(currentAccountService.findByNumber(number));
//    }
//
//    public void update() {
//
//        CurrentAccount caToUpdate = new CurrentAccount();
//        System.out.print("Enter The Number Of The Current Account You Wanna Update -> ");
//        String number = scanner.next();
//        if(currentAccountService.findByNumber(number) != null) {
//
//
//            System.out.println("Enter The New Information");
//            System.out.println("Enter The Current Account Balance -> ");
//            double caBalance = scanner.nextDouble();
//            caToUpdate.setBalance(caBalance);
//            System.out.println("Enter The Current Account Overdraft -> ");
//            double caOverdraft= scanner.nextDouble();
//            caToUpdate.setOverdraft(caOverdraft);
//            caToUpdate.setNumber(number);
//            System.out.println(currentAccountService.update(caToUpdate));
//
//        }
//
//    }
//
//    public void findByClient() {
//
//        System.out.print("Enter The Client Code -> ");
//        String clientCode = scanner.next();
//        System.out.println(currentAccountService.findByClient(clientCode));
//
//    }
//
//    public void delete() {
//
//        System.out.print("Enter The Number Of The Current Account You Wanna Delete -> ");
//        String number = scanner.next();
//        if(currentAccountService.delete(number)) {
//            System.out.println("Deleted With Success!");
//        }
//
//    }
//
//    public void updateStatus() {
//
//        System.out.print("Enter The Number Of The Current Account You Wanna Update -> ");
//        String number = scanner.next();
//        if(currentAccountService.findByNumber(number) != null) {
//            System.out.println("Choose The Status");
//            System.out.println("1- Active");
//            System.out.println("2- Frozen");
//            System.out.println("3- Closed");
//            System.out.print("-> ");
//            int statusChoice = scanner.nextInt();
//            switch (statusChoice) {
//                case 1:
//                    currentAccountService.updateStatus(accountStatus.Active, number);
//                    break;
//                case 2:
//                    currentAccountService.updateStatus(accountStatus.Frozen, number);
//                    break;
//                case 3:
//                    currentAccountService.updateStatus(accountStatus.Closed, number);
//                    break;
//                default:
//                    System.out.println("Invalid Option!");
//            }
//        }
//
//    }
//
//}
