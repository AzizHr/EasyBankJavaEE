<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.Demand" %>
<jsp:include page="../helpers/header.jsp" />
<jsp:include page="../helpers/navbar.jsp" />

<style>

    *{
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
    }

    .required, .mismatch, .not-found {
        color: #842029;
        background-color: #F8D7DA;
        padding: 16px 10px 16px 10px;
        border-radius: 4px;
        width: 50%;
        margin: 3% auto;
    }

    .success {
        color: #0F5132;
        background-color: #D1E7DD;
        padding: 16px 10px 16px 10px;
        border-radius: 4px;
        width: 50%;
        margin: 3% auto;
    }

    .table-wrapper {
        overflow: auto;
    }
    .employees-table {
        width: 80%;
        margin: 2px auto;
        border-collapse: collapse;
    }

    .employees-table thead tr {
        text-align: left;
        background-color: #219C90;
        font-weight: bold;
        color: whitesmoke;
    }

    .employees-table td,
    .employees-table th {
        padding: 12px 15px;
    }

    .employees-table tbody tr {
        border-bottom: 1px solid #d9d9d9;
        font-size: small;
        color: #5e5e5e;
    }

    .delete-form form button {
        background: none;
        border: none;
    }

    .search-form {
        width: 50%;
        margin-top: 8%;
        margin-left: 10%;
        margin-right: 10%;
        display: grid;
        grid-template-columns: 60% 20%;
        gap: 20%;
        align-items: center;
    }
    .search-form input {
        padding: 14px 10px 14px 10px;
        border: none;
        color: #5e5e5e;
        background: #f3f3f3;
        border-radius: 4px;
    }

    .search-form button {
        padding: 14px 10px 14px 10px;
        background-color: #1AACAC;
        font-weight: bold;
        color: whitesmoke;
        border: none;
        border-radius: 4px;
    }
    span {
        color: #219C90;
    }

    @media (max-width: 767px) {
        .search-form {
            width: 80%;
            margin-left: 2%;
            margin-right: 2%;
            grid-template-columns: 68% 28%;
            gap: 4%;
        }

        .required, .mismatch, .not-found, .success {
            width: 70%;
        }
    }
</style>

<% String added_with_success = (String) request.getAttribute("new_demand_added_with_success"); %>
<% if (added_with_success != null && !added_with_success.isEmpty()) { %>
<div class="success">
    <%= added_with_success %>
</div>
<% } %>

<% String deleted_with_success = (String) request.getAttribute("a_demand_deleted_with_success"); %>
<% if (deleted_with_success != null && !deleted_with_success.isEmpty()) { %>
<div class="success">
    <%= deleted_with_success %>
</div>
<% } %>

<% String error = (String) request.getAttribute("wrong_number_error"); %>
<% if (error != null && !error.isEmpty()) { %>
<div class="mismatch">
    <%= error %>
</div>
<% } %>


<% String updated_with_success = (String) request.getAttribute("a_demand_updated_with_success"); %>
<% if (updated_with_success != null && !updated_with_success.isEmpty()) { %>
<div class="success">
    <%= updated_with_success %>
</div>
<% } %>

<% String no_demand_found = (String) request.getAttribute("no_demand_found"); %>
<% if (no_demand_found != null && !no_demand_found.isEmpty()) { %>
<div class="not-found">
    <%= no_demand_found %>
</div>
<% } %>

<form class="search-form" action="${pageContext.request.contextPath}/simulations?action=search" method="post">

    <input name="code" placeholder="Enter a demand code to search..." />
    <button type="submit">Search</button>

</form>


<% String no_demands_found = (String) request.getAttribute("no_demands_found"); %>
<% Demand demand = (Demand) request.getAttribute("demand"); %>
<% if (no_demands_found != null && !no_demands_found.isEmpty()) { %>
<div>
    <%= no_demands_found %>
</div>
<% } else if(demand != null) { %>
<div class="table-wrapper">
    <table class="employees-table">
        <thead>
        <tr>
            <th>Number</th>
            <th>Created at</th>
            <th>Status</th>
            <th>Price</th>
            <th>Duration</th>
            <th>Paid monthly</th>
            <th>Remarks</th>
            <th>Created for</th>
            <th>Created in</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${demand.number}</td>
            <td>${demand.createAt}</td>
            <td>${demand.status}</td>
            <td>${demand.price}</td>
            <td>${demand.duration}</td>
            <td>${demand.paidMonthly}</td>
            <td>${demand.remarks}</td>
            <td>${demand.client.firstName} ${demand.client.lastName}</td>
            <td>${demand.agency.name}</td>
            <td class="delete-form">
                <form action="${pageContext.request.contextPath}/simulations?action=delete" method="post">
                    <input name="number" value="${demand.number}" hidden>
                    <a><button type="submit"><i class="fa-solid fa-trash" style="color: red"></i></button></a>
                </form>
            </td>
            <td>
                <c:url value='${pageContext.request.contextPath}/simulations?action=edit-status&number=${demand.number}' var="editDemandStatusURL" />
                <a href="${editDemandStatusURL}"><i class="fa-solid fa-pen-to-square" style="color: #21de21"></i></a>
            </td>
        </tr>
        <tbody>
    </table>
</div>
<% } else { %>
<div class="table-wrapper">
    <table class="employees-table">
        <thead>
        <tr>
            <th>Number</th>
            <th>Created at</th>
            <th>Status</th>
            <th>Price</th>
            <th>Duration</th>
            <th>Paid monthly</th>
            <th>Remarks</th>
            <th>Created for</th>
            <th>Created in</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${demands}" var="demand">
        <tr>
            <td>${demand.number}</td>
            <td>${demand.createAt}</td>
            <td>${demand.status}</td>
            <td>${demand.price}</td>
            <td>${demand.duration}</td>
            <td>${demand.paidMonthly}</td>
            <td>${demand.remarks}</td>
            <td>${demand.client.firstName} ${demand.client.lastName}</td>
            <td>${demand.client.firstName} ${demand.client.lastName}</td>
            <td class="delete-form">
                <form action="${pageContext.request.contextPath}/simulations?action=delete" method="post">
                    <input name="number" value="${demand.number}" hidden>
                    <a><button type="submit"><i class="fa-solid fa-trash" style="color: red"></i></button></a>
                </form>
            </td>
            <td>
                <c:url value='${pageContext.request.contextPath}/simulations?action=edit-status&number=${demand.number}' var="editDemandStatusURL" />
                <a href="${editDemandStatusURL}"><i class="fa-solid fa-pen-to-square" style="color: #21de21"></i></a>
            </td>
        </tr>
        </c:forEach>
        <tbody>
    </table>
</div>
<% } %>

<jsp:include page="../helpers/footer.jsp" />
