import daoImplementaion.AgencyDAOImp;
import daoImplementaion.ClientDAOImp;
import daoImplementaion.DemandDAOImp;
import daoImplementaion.EmployeeDAOImp;
import entities.*;
import enums.DemandStatus;
import org.junit.*;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

public class DemandDAOImpTest {

    static double interestRate = 0.05;
    static DemandDAOImp demandDAOImp;
    static EmployeeDAOImp employeeDAOImp;
    static ClientDAOImp clientDAOImp;
    static AgencyDAOImp agencyDAOImp;
    static Demand demand;
    static Employee employee;
    static Client client;
    static Agency agency;

    @Before
    public void setUp() {

        demandDAOImp = new DemandDAOImp();
        employeeDAOImp = new EmployeeDAOImp();
        clientDAOImp = new ClientDAOImp();
        agencyDAOImp = new AgencyDAOImp();

        // New Employee
        employee = new Employee();
        employee.setCode("emp-1000");
        employee.setFirstName("Kamal");
        employee.setLastName("Ahmed");
        employee.setBirthDate(LocalDate.of(2000, 5, 12));
        employee.setPhoneNumber("06 78 33 22 11");
        employee.setEmail("example@gmail.com");
        employeeDAOImp.save(employee);

        // New Client
        client = new Client();
        client.setCode("cli-1000");
        client.setFirstName("Mohammed");
        client.setLastName("Amine");
        client.setBirthDate(LocalDate.of(2000, 5, 12));
        client.setPhoneNumber("06 78 33 22 11");
        client.setAddress("Casablanca rue 23");
        client.setEmployee(employee);
        clientDAOImp.save(client);

        // New Agency
        agency = new Agency();
        agency.setCode("age-1000");
        agency.setName("Casa Agency");
        agency.setAddress("Casablanca rue 13");
        agency.setPhoneNumber("05 66 73 88 34");
        agencyDAOImp.save(agency);

        // New Demand
        demand = new Demand();
        demand.setCreateAt(LocalDate.now());
        demand.setStatus(DemandStatus.PENDING);
        demand.setPrice(10000.00);
        demand.setDuration(30);
        double paidMonthly = (demand.getPrice() * (interestRate / 12)) / (1 - Math.pow((1 + (interestRate / 12)), -demand.getDuration()));
        demand.setPaidMonthly(paidMonthly);
        demand.setRemarks("Something...");
        demand.setClient(client);
        demand.setAgency(agency);
    }


//    @Test
//    public void saveEmployeeTest() {
//        Employee employee1 = employeeDAOImp.save(employee).get();
//        assertEquals(employee, employee1);
//    }
//
//    @Test
//    public void saveClientTest() {
//        Client client1 = clientDAOImp.save(client).get();
//        assertEquals(client, client1);
//    }
//
//    @Test
//    public void saveAgencyTest() {
//        Agency agency1 = agencyDAOImp.save(agency).get();
//        assertEquals(agency, agency1);
//    }


    @Test
    public void saveDemandTest() {
        Simulation demand1 = demandDAOImp.save(demand).get();

        assertEquals(demand, demand1);
    }

}
