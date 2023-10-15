<%@ page import="entities.Client" %><%--
  Created by IntelliJ IDEA.
  User: Aziz
  Date: 09/10/2023
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Clients</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

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

        .nav-wrapper {
            box-shadow: 0 0 4px 0 #219C90;
        }

        nav {
            width: 90%;
            margin: 0 auto;
            display: grid;
            grid-template-columns: 30% 60%;
            gap: 10%;
            align-items: center;
        }
        ul {
            list-style: none;
            display: flex;
            gap: 20px;
        }
        ul a {
            text-decoration: none;
            color: black;
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

    <div class="nav-wrapper">
        <nav>
            <h1>Easy <span>Bank</span></h1>
            <ul>
                <a href="${pageContext.request.contextPath}/employees"><li>Employees</li></a>
                <a href="${pageContext.request.contextPath}/clients"><li>Clients</li></a>
            </ul>
        </nav>
    </div>
    <% String added_with_success = (String) request.getAttribute("new_client_added_with_success"); %>
    <% if (added_with_success != null && !added_with_success.isEmpty()) { %>
    <div class="success">
        <%= added_with_success %>
    </div>
    <% } %>

    <% String deleted_with_success = (String) request.getAttribute("a_client_deleted_with_success"); %>
    <% if (deleted_with_success != null && !deleted_with_success.isEmpty()) { %>
    <div class="success">
        <%= deleted_with_success %>
    </div>
    <% } %>

    <% String error = (String) request.getAttribute("wrong_code_error"); %>
    <% if (error != null && !error.isEmpty()) { %>
    <div class="mismatch">
        <%= error %>
    </div>
    <% } %>


    <% String updated_with_success = (String) request.getAttribute("a_client_updated_with_success"); %>
    <% if (updated_with_success != null && !updated_with_success.isEmpty()) { %>
    <div class="success">
        <%= updated_with_success %>
    </div>
    <% } %>

    <% String no_client_found = (String) request.getAttribute("no_client_found"); %>
    <% if (no_client_found != null && !no_client_found.isEmpty()) { %>
    <div class="not-found">
        <%= no_client_found %>
    </div>
    <% } %>

    <% String address_is_empty = (String) request.getAttribute("address_is_empty"); %>
    <% if (address_is_empty != null && !address_is_empty.isEmpty()) { %>
    <div class="required">
        <%= address_is_empty %>
    </div>
    <% } %>

    <form class="search-form" action="${pageContext.request.contextPath}/clients?action=search" method="post">

        <input name="address" placeholder="Enter an address to search..." />
        <button type="submit">Search</button>

    </form>


    <% String no_clients_found = (String) request.getAttribute("no_clients_found"); %>
    <% Client client = (Client) request.getAttribute("client"); %>
    <% if (no_clients_found != null && !no_clients_found.isEmpty()) { %>
    <div>
        <%= no_clients_found %>
    </div>
    <% } else if(client != null) { %>
        <div class="table-wrapper">
            <table class="employees-table">
                <thead>
                <tr>
                    <th>Code</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${client.code}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.birthDate}</td>
                    <td>${client.phoneNumber}</td>
                    <td>${client.address}</td>
                    <td class="delete-form">
                        <form action="${pageContext.request.contextPath}/clients?action=delete" method="post">
                            <input name="code" value="${client.code}" hidden>
                            <a><button type="submit"><i class="fa-solid fa-trash" style="color: red"></i></button></a>
                        </form>
                    </td>
                    <td>
                        <c:url value='${pageContext.request.contextPath}/clients?action=edit&code=${client.code}' var="editClientURL" />
                        <a href="${editClientURL}"><i class="fa-solid fa-pen-to-square" style="color: #21de21"></i></a>
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
                    <th>Code</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.code}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.birthDate}</td>
                    <td>${client.phoneNumber}</td>
                    <td>${client.address}</td>
                    <td class="delete-form">
                        <form class="delete-form-form" action="${pageContext.request.contextPath}/clients?action=delete" method="post">
                            <input name="code" value="${client.code}" hidden>
                            <a><button type="submit"><i class="fa-solid fa-trash" style="color: red"></i></button></a>
                        </form>
                    </td>
                    <td>
                        <c:url value='${pageContext.request.contextPath}/clients?action=edit&code=${client.code}' var="editClientURL" />
                        <a href="${editClientURL}"><i class="fa-solid fa-pen-to-square" style="color: #21de21"></i></a>
                    </td>
                </tr>
                </c:forEach>
                <tbody>
            </table>
        </div>
    <% } %>

</body>
</html>
