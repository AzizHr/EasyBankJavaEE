package controllers;

import daoImplementaion.DemandDAOImp;
import entities.Demand;
import entities.Employee;
import entities.Simulation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.DemandService;

import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

@WebServlet(value = "/simulations")
public class SimulationServlet extends HttpServlet {

    private static final DemandDAOImp demandDAOImp = new DemandDAOImp();
    private static final DemandService demandService = new DemandService(demandDAOImp);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");

        if(action == null) {
            findAll(request, response);
        }

        if ("list".equals(action)) {
            findAll(request, response);
        } else if ("view".equals(action)) {
            Page.show(request, response, "simulations/simulation.jsp");
        } else if ("create".equals(action)) {
        } else if ("edit".equals(action)) {
        } else if ("update".equals(action)) {
        } else if ("delete".equals(action)) {
        } else if ("search".equals(action)) {
        } else {
            Page.show(request, response, "404.jsp");
        }

    }

    private void findByNumber(HttpServletRequest request, HttpServletResponse response) {

        String number = request.getParameter("number");

        if(number != null && !number.isEmpty()) {
            Simulation demand = demandService.findByCode(number);
            if (demand != null) {
                request.setAttribute("demand", demand);
            } else {
                out.println("No demand found with this code!");
                request.setAttribute("no_demand_found", "No demand was found!");
            }
        } else {
            request.setAttribute("number_is_empty", "Please provide a number!");
        }

        Page.show(request, response, "simulations/update");

    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {

        List<Simulation> demands = demandService.findAll();

        if (demands.isEmpty()) {
            out.println("No demands found!");
            request.setAttribute("no_demands_found", "No demands found");
        } else {
            request.setAttribute("demands", demands);
        }
        Page.show(request, response, "simulations/index.jsp");
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

        request.getParameter("number");
        request.getParameter("created_at");
        request.getParameter("status");
        request.getParameter("price");
        request.getParameter("duration");
        request.getParameter("paid_monthly");
        request.getParameter("remarks");
        request.getParameter("client_code");
        request.getParameter("agency_code");

    }
}
