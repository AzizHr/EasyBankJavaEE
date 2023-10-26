package services;

import daoImplementaion.DemandDAOImp;
import entities.*;
import org.junit.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import enums.DemandStatus;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DemandServiceTest {

    DemandDAOImp demandDAOImp;
    DemandService demandService;
    Demand demand;
    Employee employee;
    Client client;
    Agency agency;

    @Before
    public void setUp() {

        demandDAOImp = mock(DemandDAOImp.class);
        demandService = new DemandService(demandDAOImp);

//        // New Employee
        employee = new Employee();
        employee.setCode("emp-1000");
        employee.setFirstName("Kamal");
        employee.setLastName("Ahmed");
        employee.setBirthDate(LocalDate.of(2000, 5, 12));
        employee.setPhoneNumber("06 78 33 22 11");
        employee.setEmail("example@gmail.com");

//        // New Client
        client = new Client();
        client.setCode("cli-1000");
        client.setFirstName("Mohammed");
        client.setLastName("Amine");
        client.setBirthDate(LocalDate.of(2000, 5, 12));
        client.setPhoneNumber("06 78 33 22 11");
        client.setAddress("Casablanca rue 23");
        client.setEmployee(employee);

//        // New Agency
        agency = new Agency();
        agency.setCode("age-1000");
        agency.setName("Casa Agency");
        agency.setAddress("Casablanca rue 13");
        agency.setPhoneNumber("05 66 73 88 34");

//        // New Demand
        demand = new Demand();
        demand.setCreateAt(LocalDate.now());
        demand.setStatus(DemandStatus.PENDING);
        demand.setPrice(10000.00);
        demand.setDuration(30);
        double paidMonthly = demandService.calculatePaidMonthly(demand.getPrice(), demand.getDuration());
        demand.setPaidMonthly(paidMonthly);
        demand.setRemarks("Something...");
        demand.setClient(client);
        demand.setAgency(agency);
    }


    @Test
    public void save() {
        when(demandDAOImp.save(demand)).thenReturn(Optional.ofNullable(demand));
        Simulation demand1 = demandService.save(demand);
        assertEquals(demand, demand1);
        Assert.assertNotEquals(null, demand1);
    }

    @Test
    public void delete() {
        when(demandDAOImp.delete(12)).thenReturn(true);
        boolean isDeleted = demandService.delete(12);
        assertTrue(isDeleted);
    }

    @Test
    public void findAll() {
        List<Simulation> demands = new ArrayList<>();
        demands.add(demand);
        when(demandDAOImp.findAll()).thenReturn(Optional.of(demands));
        List<Simulation> allDemands = demandService.findAll();
        assertEquals(demands, allDemands);
        Assert.assertNotEquals(null, allDemands);
    }

    @Test
    public void findByNumber() {
        when(demandDAOImp.findByNumber(12)).thenReturn(Optional.ofNullable(demand));
        Simulation foundDemand = demandService.findByNumber(12);
        assertEquals(demand, foundDemand);
        Assert.assertNotEquals(null, foundDemand);
    }

    @Test
    public void updateStatus() {
        demand.setStatus(DemandStatus.ACCEPTED);
        when(demandDAOImp.updateStatus(DemandStatus.ACCEPTED, 12)).thenReturn(true);
        boolean isUpdated = demandService.updateStatus(DemandStatus.ACCEPTED, 12);
        assertTrue(isUpdated);
    }

    @Test
    public void calculatePaidMonthly() {
        double res = demandService.calculatePaidMonthly(10000, 30);
        assertEquals(355.29364, res, 0.00001);
        Assert.assertNotEquals(0, res, 0.00001);
    }
}