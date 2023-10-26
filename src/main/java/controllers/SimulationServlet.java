package controllers;

import daoImplementaion.AgencyDAOImp;
import daoImplementaion.ClientDAOImp;
import daoImplementaion.DemandDAOImp;
import entities.*;
import enums.DemandStatus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AgencyService;
import services.ClientService;
import services.DemandService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@WebServlet(value = "/simulations")
public class SimulationServlet extends HttpServlet {

    private static final DemandDAOImp demandDAOImp = new DemandDAOImp();
    private static final DemandService demandService = new DemandService(demandDAOImp);
    private static final ClientDAOImp clientDAOImp = new ClientDAOImp();
    private static final ClientService clientService = new ClientService(clientDAOImp);
    private static final AgencyDAOImp agencyDAOImp = new AgencyDAOImp();
    private static final AgencyService agencyService = new AgencyService(agencyDAOImp);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");

        if(action == null) {
            findAll(request, response);
        }

        if ("list".equals(action)) {
            findAll(request, response);
        } else if ("view".equals(action)) {
            showSimulationFormPage(request, response);
        } else if ("check-client".equals(action)) {
            findClientByCode(request, response);
        } else if ("create".equals(action)) {
            save(request, response);
        } else if ("edit-status".equals(action)) {
            findByNumber(request, response);
        } else if ("update-status".equals(action)) {
            updateStatus(request, response);
        } else if ("delete".equals(action)) {
            delete(request, response);
        } else if ("search".equals(action)) {
            search(request, response);
        } else {
            Page.show(request, response, "404.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void findByNumber(HttpServletRequest request, HttpServletResponse response) {

        String number = request.getParameter("number");

        if(number != null && !number.isEmpty()) {
            Simulation demand = demandService.findByCode(Integer.parseInt(number));
            if (demand != null) {
                request.setAttribute("demand", demand);
            } else {
                out.println("No demand found with this code!");
                request.setAttribute("no_demand_found", "No demand was found!");
            }
        } else {
            request.setAttribute("number_is_empty", "Please provide a number!");
        }

        Page.show(request, response, "simulations/update_status.jsp");

    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {

        List<Simulation> demands = demandService.findAll();

        if (demands.isEmpty()) {
            out.println("No demands found!");
            request.setAttribute("no_demands_found", "No demands found");
        } else {
            request.setAttribute("demands", demands);
        }
        Page.show(request, response, "/simulations/index.jsp");
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        Client client = clientService.findByCode(request.getParameter("client_code"));
        out.println(client);

        Agency agency = agencyService.findByCode(request.getParameter("agency_code"));

        if(client != null && agency != null) {
            double paidMonthly = demandService.calculatePaidMonthly(Double.parseDouble(request.getParameter("price")), Integer.parseInt(request.getParameter("duration")));
            if(Double.parseDouble(request.getParameter("paid_monthly")) == paidMonthly) {
                Demand demand = new Demand(LocalDate.now(), DemandStatus.PENDING, Double.parseDouble(request.getParameter("price")), Integer.parseInt(request.getParameter("duration")), paidMonthly, request.getParameter("remarks"), client, agency);
                demandService.save(demand);
                request.setAttribute("a_demand_added_with_success", "Demand added with success!");
            } else {
                out.println("Doesn't match");
            }
        }

        findAll(request, response);

    }

    private void findClientByCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("client_code");

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

        Page.show(request, response, "simulations/simulation.jsp");
    }

    private void showSimulationFormPage(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Agency> agencies = agencyService.findAll();

            if(agencies.isEmpty()) {
                request.setAttribute("no_agencies_found", "No agencies found");
            } else {
                request.setAttribute("agencies", agencies);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("simulations/simulation.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        Simulation demand = demandService.findByCode(Integer.parseInt(request.getParameter("number")));
        if (demand != null) {
            if(demandService.delete(demand.getNumber())) {
                request.setAttribute("a_demand_deleted_with_success", "Demand deleted with success!");
            } else {
                request.setAttribute("wrong_number_error", "No demand was found!");
            }
        } else {
            request.setAttribute("wrong_number_error", "No demand was found!");
        }
        findAll(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) {
        int number = Integer.parseInt(request.getParameter("number"));

        if(request.getParameter("number") != null && !request.getParameter("number").isEmpty()) {
            Simulation demand = demandService.findByCode(number);
            if (demand != null) {
                request.setAttribute("demand", demand);
            } else {
                request.setAttribute("no_demand_found", "No demand was found!");
            }
        } else {
            request.setAttribute("number_required", "Demand number is required!");
        }

        findAll(request, response);
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response) {

        DemandStatus status = DemandStatus.valueOf(request.getParameter("status"));
        int number = Integer.parseInt(request.getParameter("number"));

        if(demandService.updateStatus(status, number)) {
            request.setAttribute("status_updated_with_success", "Demand status updated with success!");
        } else {
            out.println("updating error!");
        }

        findAll(request, response);
    }
}
