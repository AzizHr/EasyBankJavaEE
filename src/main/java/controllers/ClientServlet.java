package controllers;

import daoImplementaion.ClientDAOImp;
import entities.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import services.ClientService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static java.lang.System.out;

@WebServlet(name = "clientServlet", urlPatterns = {"/clients"})
public class ClientServlet extends HttpServlet {

    private static final ClientDAOImp clientDAOImp = new ClientDAOImp();
    private static final ClientService clientService = new ClientService(clientDAOImp);

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
        } else if ("search".equals(action)) {
            findByAddress(request, response);
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
                out.println("No client found with this code!");
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
        Client client = new Client();
        client.setCode(request.getParameter("client_code"));
        client.setFirstName(request.getParameter("client_firstname"));
        client.setLastName(request.getParameter("client_lastname"));
        client.setBirthDate(LocalDate.parse(request.getParameter("client_birthdate")));
        client.setPhoneNumber(request.getParameter("client_phone_number"));
        client.setAddress(request.getParameter("client_address"));
        clientService.save(client);
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
}

