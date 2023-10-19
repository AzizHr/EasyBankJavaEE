//package controllers_which_will_be_deleted;
//
//import entities.*;
//import enums.paymentType;
//import services.CurrentAccountService;
//import services.EmployeeService;
//import services.OperationService;
//import services.SavingAccountService;
//import java.time.LocalDateTime;
//import java.util.Scanner;
//
//public class OperationController {
//
//    private final OperationService operationService;
//    private final EmployeeService employeeService;
//    private final CurrentAccountService currentAccountService;
//    private final SavingAccountService savingAccountService;
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public OperationController(OperationService instance1, EmployeeService instance2, CurrentAccountService instance3, SavingAccountService instance4) {
//        operationService = instance1;
//        employeeService = instance2;
//        currentAccountService = instance3;
//        savingAccountService = instance4;
//    }
//
//    public void saveForCurrentAccount() {
//
//        System.out.print("Enter The Employee Code Who Will Do This Operation -> ");
//        String employeeCode = scanner.next();
//        Employee employee = employeeService.findByCode(employeeCode);
//        if(employee != null) {
//            System.out.print("Enter The Current Account Number -> ");
//            String currentAccountNumber = scanner.next();
//
//            CurrentAccount currentAccount = currentAccountService.findByNumber(currentAccountNumber);
//            if(currentAccount != null) {
//                Operation operation = new Operation();
//                System.out.print("Enter The Operation Number -> ");
//                String number = scanner.next();
//                operation.setNumber(number);
//                LocalDateTime createdAt = LocalDateTime.now();
//                operation.setCreatedAt(createdAt);
//                System.out.print("Enter The Price -> ");
//                double price = scanner.nextDouble();
//                operation.setPrice(price);
//                System.out.println("Choose The Payment type");
//                System.out.println("1- Deposit");
//                System.out.println("2- Withdrawal");
//                System.out.print("-> ");
//                int paymentTypeChoice = scanner.nextInt();
//                switch (paymentTypeChoice) {
//                    case 1:
//                        operation.setPayment(paymentType.Deposit);
//                        currentAccountService.deposit(price, currentAccountNumber);
//                        break;
//                    case 2:
//                        operation.setPayment(paymentType.Withdrawal);
//                        currentAccountService.withdraw(price, currentAccountNumber);
//                        break;
//                    default:
//                }
//                operation.setEmployee(employee);
//                operation.setAccount(currentAccount);
//                operationService.saveForCA(operation);
//            }
//        }
//
//    }
//
//    public void saveForSavingAccount() {
//
//        System.out.print("Enter The Employee Code Who Will Do This Operation -> ");
//        String employeeCode = scanner.next();
//        Employee employee = employeeService.findByCode(employeeCode);
//        if(employee != null) {
//            System.out.print("Enter The Saving Account Number -> ");
//            String savingAccountNumber = scanner.next();
//
//            SavingAccount savingAccount = savingAccountService.findByNumber(savingAccountNumber);
//            if(savingAccount != null) {
//                Operation operation = new Operation();
//                System.out.print("Enter The Operation Number -> ");
//                String number = scanner.next();
//                operation.setNumber(number);
//                LocalDateTime createdAt = LocalDateTime.now();
//                operation.setCreatedAt(createdAt);
//                System.out.print("Enter The Price -> ");
//                double price = scanner.nextDouble();
//                operation.setPrice(price);
//                System.out.println("Choose The Payment type");
//                System.out.println("1- Deposit");
//                System.out.println("2- Withdrawal");
//                System.out.print("-> ");
//                int paymentTypeChoice = scanner.nextInt();
//                switch (paymentTypeChoice) {
//                    case 1:
//                        operation.setPayment(paymentType.Deposit);
//                        savingAccountService.deposit(price, savingAccountNumber);
//                        break;
//                    case 2:
//                        operation.setPayment(paymentType.Withdrawal);
//                        savingAccountService.withdraw(price, savingAccountNumber);
//                        break;
//                    default:
//                }
//                operation.setEmployee(employee);
//                operation.setAccount(savingAccount);
//                operationService.saveForSA(operation);
//            }
//        }
//
//    }
//
//
//    public void findByNumber() {
//        System.out.print("Enter The Number Of The Operation You're Looking For -> ");
//        String number = scanner.next();
//        System.out.println(operationService.findByNumber(number));
//    }
//
//
//    public void delete() {
//
//        System.out.print("Enter The Number Of The Operation You Wanna Delete -> ");
//        String number = scanner.next();
//        if(operationService.delete(number)) {
//            System.out.println("Deleted With Success!");
//        }
//
//    }
//
//}
