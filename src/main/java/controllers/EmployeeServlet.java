package controllers;

import daoImplementaion.EmployeeDAOImp;
import entities.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import services.EmployeeService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static java.lang.System.out;

@WebServlet(name = "employeeServlet", urlPatterns = {"/employees"})
public class EmployeeServlet extends HttpServlet {

    private static final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
    private static final EmployeeService employeeService = new EmployeeService(employeeDAOImp);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        response.setContentType("text/html");

        if(action == null) {
            findAll(request, response);
        }

        if ("list".equals(action)) {
            findAll(request, response);
        } else if ("view".equals(action)) {
            showPage(request, response, "create");
        } else if ("create".equals(action)) {
            save(request, response);
        } else if ("edit".equals(action)) {
            findByCode(request, response);
        } else if ("update".equals(action)) {
            update(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        } else {
            out.print("Page Not Found!");
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
        try {
            List<Employee> employees = employeeService.findAll();

            if (employees.isEmpty()) {
                request.setAttribute("no_employees_found", "No Employees Found");
            } else {
                request.setAttribute("employees", employees);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("employees/index.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void findByCode(HttpServletRequest request, HttpServletResponse response) {

        Employee employee = employeeService.findByCode(request.getParameter("code"));
        if (employee != null) {
            request.setAttribute("employee", employee);
            showPage(request, response, "update");
        } else {
            out.println("No employee found!");
        }
    }


    protected void showPage(HttpServletRequest request, HttpServletResponse response, String page) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("employees/" + page + ".jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void save(HttpServletRequest request, HttpServletResponse response) {
        try {
            Employee employee = new Employee();
            employee.setCode(request.getParameter("employee_code"));
            employee.setFirstName(request.getParameter("employee_firstname"));
            employee.setLastName(request.getParameter("employee_lastname"));
            employee.setBirthDate(LocalDate.parse(request.getParameter("employee_birthdate")));
            employee.setPhoneNumber(request.getParameter("employee_phone_number"));
            employee.setEmail(request.getParameter("employee_email"));
            employeeService.save(employee);
            request.setAttribute("new_employee_added_with_success", "Employee Added With Success");
            RequestDispatcher dispatcher = request.getRequestDispatcher("employees/create.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
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
            request.setAttribute("an_employee_updated_with_success", "Employee Updated With Success");
            showPage(request, response, "update");
        } else {
            out.println("Wait a minute");
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {

        Employee employee = employeeService.findByCode(request.getParameter("code"));
        if (employee != null) {
            if(employeeService.delete(employee.getCode())) {
                request.setAttribute("an_employee_deleted_with_success", "An Employee Deleted With Success");
            } else {
                request.setAttribute("wrong_code_error", "No Employee With This Code Found");
            }
        } else {
            request.setAttribute("wrong_code_error", "No Employee With This Code Found");
        }
        findAll(request, response);
    }
}
