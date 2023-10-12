package controllers_which_will_be_deleted;

import entities.Employee;
import entities.Mission;
import entities.MissionHistory;
import services.EmployeeService;
import services.MissionHistoryService;
import services.MissionService;

import java.time.LocalDate;
import java.util.Scanner;

public class MissionController {

    private final MissionService missionService;
    private final EmployeeService employeeService;
    private final MissionHistoryService missionHistoryService;
    private static final Scanner scanner = new Scanner(System.in);

    public MissionController(MissionService instance1, EmployeeService instance2, MissionHistoryService instance3) {

        missionService = instance1;
        employeeService = instance2;
        missionHistoryService = instance3;

    }

    public void save() {

        Mission mission = new Mission();
        System.out.print("Enter The Mission Code -> ");
        String code = scanner.next();
        mission.setCode(code);
        System.out.print("Enter The Mission Name -> ");
        String name = scanner.next();
        mission.setName(name);
        System.out.print("Enter The Mission Description -> ");
        String description = scanner.next();
        mission.setDescription(description);
        System.out.println(missionService.save(mission));

    }

    public void findAll() {

        missionService.findAll();

    }

    public void findByCode() {
        System.out.print("Enter The Code Of The Mission You're Looking For -> ");
        String code = scanner.next();
        System.out.println(missionService.findByCode(code));
    }

    public void delete() {

        System.out.print("Enter The Code Of The Mission You Wanna Delete -> ");
        String code = scanner.next();
        if(missionService.delete(code)) {
            System.out.println("Deleted With Success!");
        }

    }

    public void assignToAnEmployee() {

        System.out.print("Enter The Mission Code You Wanna Assign -> ");
        String missionCode = scanner.next();
        Mission mission = missionService.findByCode(missionCode);
        if(mission != null) {
            System.out.print("Enter The Employee Code -> ");
            String employeeCode = scanner.next();
            Employee employee = employeeService.findByCode(employeeCode);
            if(employee != null) {
                MissionHistory missionHistory = new MissionHistory();
                LocalDate startedAt = LocalDate.now();
                LocalDate endedAt = startedAt.plusDays(7);
                missionHistory.setEmployee(employee);
                missionHistory.setMission(mission);
                missionHistory.setStartedAt(startedAt);
                missionHistory.setEndedAt(endedAt);
                System.out.println(missionHistoryService.save(missionHistory));
            }
        }
    }

    public void deleteAnAssignment() {
        System.out.print("Enter The Employee Code -> ");
        String employeeCode = scanner.next();
        Employee employee = employeeService.findByCode(employeeCode);
        if(employee != null) {

            System.out.print("Enter The Mission Code -> ");
            String missionCode = scanner.next();
            Mission mission = missionService.findByCode(missionCode);

            if(mission != null) {
                LocalDate startedAt = LocalDate.now();
                if(missionHistoryService.delete(employee.getCode(), mission.getCode(), startedAt)) {
                    System.out.println("Deleted With Success!");
                }
            }

        }

    }

}
