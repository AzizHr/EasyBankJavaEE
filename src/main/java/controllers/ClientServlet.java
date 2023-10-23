package controllers;

import daoImplementaion.ClientDAOImp;
import daoImplementaion.EmployeeDAOImp;
import entities.Client;
import entities.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import services.ClientService;
import services.EmployeeService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(value = "/clients")
public class ClientServlet extends HttpServlet {

    private static final ClientDAOImp clientDAOImp = new ClientDAOImp();
    private static final ClientService clientService = new ClientService(clientDAOImp);
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
            showCreateNewClientForm(request, response);
        } else if ("create".equals(action)) {
            save(request, response);
        } else if ("edit".equals(action)) {
            findByCode(request, response);
        } else if ("update".equals(action)) {
            update(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        } else if ("search".equals(action)) {
            findByAddress(request, response);
        } else {
            showNotFoundPage(request, response);
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
            List<Client> employees = clientService.findAll();

            if (employees.isEmpty()) {
                request.setAttribute("no_clients_found", "No clients found");
            } else {
                request.setAttribute("clients", employees);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("clients/index.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void findByCode(HttpServletRequest request, HttpServletResponse response) {

        String code = request.getParameter("code");

        if(code != null && !code.isEmpty()) {
            Client client = clientService.findByCode(code);
            if (client != null) {
                request.setAttribute("client", client);
            } else {
                request.setAttribute("no_client_found", "No client was found!");
            }
        } else {
            request.setAttribute("code_is_empty", "Please provide a code!");
        }

        showPage(request, response, "update");

    }

    protected void findByAddress(HttpServletRequest request, HttpServletResponse response) {

        String address = request.getParameter("address");

        if(address != null && !address.isEmpty()) {
            Client client = clientService.findByAddress(address);
            if (client != null) {
                request.setAttribute("client", client);
            } else {
                request.setAttribute("no_client_found", "No client was found!");
            }
        } else {
            request.setAttribute("address_is_empty", "Please provide an address!");
        }

        findAll(request, response);

    }


    protected void showPage(HttpServletRequest request, HttpServletResponse response, String page) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("clients/" + page + ".jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void save(HttpServletRequest request, HttpServletResponse response) {
//        Client client = new Client();
//        client.setCode(request.getParameter("client_code"));
//        client.setFirstName(request.getParameter("client_firstname"));
//        client.setLastName(request.getParameter("client_lastname"));
//        client.setBirthDate(LocalDate.parse(request.getParameter("client_birthdate")));
//        client.setPhoneNumber(request.getParameter("client_phone_number"));
//        client.setAddress(request.getParameter("client_address"));
//        client.setEmployee(employeeService.findByCode(request.getParameter("employee_code")));
//        clientService.save(client);
//        request.setAttribute("new_client_added_with_success", "Client added with success!");
//        findAll(request, response);

        Employee employee = employeeService.findByCode(request.getParameter("employee_code"));
        if(employee != null) {
            Client client = new Client(request.getParameter("client_firstname"), request.getParameter("client_lastname"), LocalDate.parse(request.getParameter("client_birthdate")), request.getParameter("client_phone_number"), request.getParameter("client_code"), request.getParameter("client_address"), employee);
            clientService.save(client);
        } else {
            System.out.println("employee is null");
            System.out.println(request.getParameter("employee_code"));
        }

        request.setAttribute("new_client_added_with_success", "Client added with success!");
        findAll(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {

        String code = request.getParameter("client_code");

        if (code != null && !code.isEmpty()) {
            Client client = clientService.findByCode(code);
            client.setFirstName(request.getParameter("client_firstname"));
            client.setLastName(request.getParameter("client_lastname"));
            client.setBirthDate(LocalDate.parse(request.getParameter("client_birthdate")));
            client.setPhoneNumber(request.getParameter("client_phone_number"));
            client.setAddress(request.getParameter("client_address"));
            clientService.update(client);
            request.setAttribute("a_client_updated_with_success", "Client updated with success!");
            findAll(request, response);
        } else {
            request.setAttribute("code_required", "The client code is required!");
            showPage(request, response, "update");
        }

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {

        Client employee = clientService.findByCode(request.getParameter("code"));
        if (employee != null) {
            if(clientService.delete(employee.getCode())) {
                request.setAttribute("a_client_deleted_with_success", "Client deleted with success!");
            } else {
                request.setAttribute("wrong_code_error", "No client was found!");
            }
        } else {
            request.setAttribute("wrong_code_error", "No client was found!");
        }
        findAll(request, response);
    }

    public void showNotFoundPage(HttpServletRequest request, HttpServletResponse response) {

        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("404.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void showCreateNewClientForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Employee> employees = employeeService.findAll();
            if (employees.isEmpty()) {
                request.setAttribute("no_employees_found", "No employees found");
            } else {
                request.setAttribute("employees", employees);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("clients/create.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

