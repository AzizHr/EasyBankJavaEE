//package controllers_which_will_be_deleted;
//
//import entities.Client;
//import services.ClientService;
//
//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class ClientController {
//
//    private final ClientService clientService;
//    private static final Scanner scanner = new Scanner(System.in);
//
//    public ClientController(ClientService instance) {
//        clientService = instance;
//    }
//
//    public void save() {
//
//        Client client = new Client();
//        System.out.print("Enter The Client Code -> ");
//        String code = scanner.next();
//        client.setCode(code);
//        System.out.print("Enter The Client First Name -> ");
//        String firstName = scanner.next();
//        client.setFirstName(firstName);
//        System.out.print("Enter The Client Last Name -> ");
//        String lastName = scanner.next();
//        client.setLastName(lastName);
//        LocalDate birthDate = LocalDate.of(2001, 2, 2);
//        client.setBirthDate(birthDate);
//        System.out.print("Enter The Client Phone Number -> ");
//        String phoneNumber = scanner.next();
//        client.setPhoneNumber(phoneNumber);
//        System.out.print("Enter The Client Adress -> ");
//        String adress = scanner.next();
//        client.setAdress(adress);
//        System.out.println(clientService.save(client));
//
//    }
//
//    public void findAll() {
//        clientService.findAll();
//    }
//
//    public void findByCode() {
//        System.out.print("Enter The Code Of The Client You're Looking For -> ");
//        String clientCode = scanner.next();
//        System.out.println(clientService.findByCode(clientCode));
//    }
//
//    public void update() {
//        System.out.print("Enter The Code Of The Client You Wanna Update -> ");
//        String code = scanner.next();
//
//        if(clientService.findByCode(code) != null) {
//
//            System.out.println("Enter The New Information");
//            Client client = new Client();
//            System.out.print("Enter The Client First Name -> ");
//            String firstName = scanner.next();
//            client.setFirstName(firstName);
//            System.out.print("Enter The Client Last Name -> ");
//            String lastName = scanner.next();
//            client.setLastName(lastName);
//            LocalDate birthDate = LocalDate.of(2001, 2, 2);
//            client.setBirthDate(birthDate);
//            System.out.print("Enter The Client Phone Number -> ");
//            String phoneNumber = scanner.next();
//            client.setPhoneNumber(phoneNumber);
//            System.out.print("Enter The Client Address -> ");
//            String adress = scanner.next();
//            client.setAdress(adress);
//            client.setCode(code);
//            System.out.println(clientService.update(client));
//
//        }
//    }
//
//    public void findByAdress() {
//
//        System.out.print("Enter The Adress Of The Client You're Looking For -> ");
//        String clientAdress = scanner.next();
//        System.out.println(clientService.findByAdress(clientAdress));
//
//    }
//
//    public void delete() {
//
//        System.out.print("Enter The Code Of The Client You Wanna Delete -> ");
//        String clientCodeToDelete = scanner.next();
//        if(clientService.delete(clientCodeToDelete)) {
//            System.out.println("Deleted With Success!");
//        }
//
//    }
//
//}
