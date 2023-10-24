package controllers;

import daoImplementaion.EmployeeDAOImp;
import entities.Employee;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.EmployeeService;
import java.time.LocalDate;
import java.util.List;
import static java.lang.System.out;

@WebServlet(value = "/employees")
public class EmployeeServlet extends HttpServlet {


    private static final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
    private static final EmployeeService employeeService = new EmployeeService(employeeDAOImp);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");

        if(action == null) {
            findAll(request, response);
        }

        if ("list".equals(action)) {
            findAll(request, response);
        } else if ("view".equals(action)) {
            Page.show(request, response, "employees/create.jsp");
        } else if ("create".equals(action)) {
            save(request, response);
        } else if ("edit".equals(action)) {
            findByCode(request, response);
        } else if ("update".equals(action)) {
            update(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        } else if ("search".equals(action)) {
            findByPhoneNumber(request, response);
        } else {
            Page.show(request, response, "index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void findAll(HttpServletRequest request, HttpServletResponse response) {

            List<Employee> employees = employeeService.findAll();
            if (employees.isEmpty()) {
                request.setAttribute("no_employees_found", "No employees found");
            } else {
                request.setAttribute("employees", employees);
            }

            Page.show(request, response, "employees/index.jsp");
    }

    protected void findByCode(HttpServletRequest request, HttpServletResponse response) {

        String code = request.getParameter("code");

        if(code != null && !code.isEmpty()) {
            Employee employee = employeeService.findByCode(code);
            if (employee != null) {
                request.setAttribute("employee", employee);
//                out.println(employee.getFirstName());
            } else {
                out.println("No employee found with this code!");
                request.setAttribute("no_employee_found", "No employee was found!");
                out.println("No employee found");
            }
        } else {
            request.setAttribute("code_is_empty", "Please provide a code!");
            out.println("Please provide a code");
        }

        Page.show(request, response, "employees/update.jsp");

    }

    protected void findByPhoneNumber(HttpServletRequest request, HttpServletResponse response) {

        String phoneNumber = request.getParameter("phone_number");

        if(phoneNumber != null && !phoneNumber.isEmpty()) {
            Employee employee = employeeService.findByPhoneNumber(phoneNumber);
            if (employee != null) {
                request.setAttribute("employee", employee);
            } else {
                request.setAttribute("no_employee_found", "No employee was found!");
            }
        } else {
            request.setAttribute("phone_number_is_empty", "Please provide a phone number!");
        }

        findAll(request, response);

    }



    protected void save(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = new Employee();
        employee.setCode(request.getParameter("employee_code"));
        employee.setFirstName(request.getParameter("employee_firstname"));
        employee.setLastName(request.getParameter("employee_lastname"));
        employee.setBirthDate(LocalDate.parse(request.getParameter("employee_birthdate")));
        employee.setPhoneNumber(request.getParameter("employee_phone_number"));
        employee.setEmail(request.getParameter("employee_email"));
        employeeService.save(employee);
        request.setAttribute("new_employee_added_with_success", "Employee added with success!");
        findAll(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {

        String code = request.getParameter("employee_code");

        if (code != null && !code.isEmpty()) {
            Employee emp = employeeService.findByCode(code);
            emp.setFirstName(request.getParameter("employee_firstname"));
            emp.setLastName(request.getParameter("employee_lastname"));
            emp.setBirthDate(LocalDate.parse(request.getParameter("employee_birthdate")));
            emp.setPhoneNumber(request.getParameter("employee_phone_number"));
            emp.setEmail(request.getParameter("employee_email"));
            employeeService.update(emp);
            request.setAttribute("an_employee_updated_with_success", "Employee updated with success!");
            findAll(request, response);
        } else {
            request.setAttribute("code_required", "The employee code is required!");
            Page.show(request, response, "employees/update.jsp");
        }

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {

        Employee employee = employeeService.findByCode(request.getParameter("code"));
        if (employee != null) {
            if(employeeService.delete(employee.getCode())) {
                request.setAttribute("an_employee_deleted_with_success", "Employee deleted with success!");
            } else {
                request.setAttribute("wrong_code_error", "No employee was found!");
            }
        } else {
            request.setAttribute("wrong_code_error", "No employee was found!");
        }
        findAll(request, response);
    }

}
