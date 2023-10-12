<%--
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
    <title>All Employees</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

    <style>

        .success {
            background-color: #beffbe;
            color: #009300;
            border-radius: 6px;
            margin: 40px auto;
            width: 60%;
            padding: 14px;
        }

        .error {
            background-color: #ffb1b1;
            color: #8d0000;
            border-radius: 6px;
            margin: 40px auto;
            width: 60%;
            padding: 14px;
        }

        /*table {*/
        /*    width: 86%;*/
        /*    margin: 40px auto;*/
        /*    box-shadow: 0 0 6px 0 black;*/
        /*    padding: 10px;*/
        /*    border-collapse: revert;*/
        /*}*/

        /*table thead th {*/
        /*    background-color: #687EFF;*/
        /*    padding: 6px;*/
        /*}*/

        /*table tr td {*/
        /*    padding: 6px;*/
        /*    text-align: center;*/
        /*}*/
        button {
            background: none;
            border: none;
            /*margin-top: 8px;*/
        }


        *{
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
        }
        body{
            font-family: Helvetica;
            -webkit-font-smoothing: antialiased;
            background: rgba( 71, 147, 227, 1);
        }
        h2{
            text-align: center;
            font-size: 18px;
            text-transform: uppercase;
            letter-spacing: 1px;
            color: white;
            padding: 30px 0;
        }

        /* Table Styles */

        .table-wrapper{
            margin: 10px 70px 70px;
            box-shadow: 0 35px 50px rgba( 0, 0, 0, 0.2 );
        }

        .fl-table {
            border-radius: 5px;
            font-size: 12px;
            font-weight: normal;
            border: none;
            border-collapse: collapse;
            width: 100%;
            max-width: 100%;
            white-space: nowrap;
            background-color: white;
        }

        .fl-table td, .fl-table th {
            text-align: center;
            padding: 8px;
        }

        .fl-table td {
            border-right: 1px solid #f8f8f8;
            font-size: 12px;
        }

        .fl-table thead th {
            color: #ffffff;
            background: #4FC3A1;
        }


        .fl-table thead th:nth-child(odd) {
            color: #ffffff;
            background: #324960;
        }

        .fl-table tr:nth-child(even) {
            background: #F8F8F8;
        }

        /* Responsive */

        @media (max-width: 767px) {
            .fl-table {
                display: block;
                width: 100%;
            }
            .table-wrapper:before{
                content: "Scroll horizontally >";
                display: block;
                text-align: right;
                font-size: 11px;
                color: white;
                padding: 0 0 10px;
            }
            .fl-table thead, .fl-table tbody, .fl-table thead th {
                display: block;
            }
            .fl-table thead th:last-child{
                border-bottom: none;
            }
            .fl-table thead {
                float: left;
            }
            .fl-table tbody {
                width: auto;
                position: relative;
                overflow-x: auto;
            }
            .fl-table td, .fl-table th {
                padding: 20px .625em .625em .625em;
                height: 60px;
                vertical-align: middle;
                box-sizing: border-box;
                overflow-x: hidden;
                overflow-y: auto;
                width: 120px;
                font-size: 13px;
                text-overflow: ellipsis;
            }
            .fl-table thead th {
                text-align: left;
                border-bottom: 1px solid #f7f7f9;
            }
            .fl-table tbody tr {
                display: table-cell;
            }
            .fl-table tbody tr:nth-child(odd) {
                background: none;
            }
            .fl-table tr:nth-child(even) {
                background: transparent;
            }
            .fl-table tr td:nth-child(odd) {
                background: #F8F8F8;
                border-right: 1px solid #E6E4E4;
            }
            .fl-table tr td:nth-child(even) {
                border-right: 1px solid #E6E4E4;
            }
            .fl-table tbody td {
                display: block;
                text-align: center;
            }
        }

    </style>

    <% String success = (String) request.getAttribute("an_employee_deleted_with_success"); %>
<%--    <% if (success != null && !success.isEmpty()) { %>--%>
<%--    <div class="success">--%>
<%--        <p id="success"><%= success %></p>--%>
<%--    </div>--%>
<%--    <% } %>--%>

    <% String error = (String) request.getAttribute("wrong_code_error"); %>
    <% if (error != null && !error.isEmpty()) { %>
    <div class="error">
        <%= error %>
    </div>
    <% } %>


    <h2>Employees List</h2>
    <% String no_employees_found = (String) request.getAttribute("no_employees_found"); %>
    <% if (no_employees_found != null && !no_employees_found.isEmpty()) { %>
    <div>
        <%= no_employees_found %>
    </div>
    <% } %>
    <div class="table-wrapper">
        <table class="fl-table">
            <thead>
            <tr>
                <th>Code</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Birth Date</th>
                <th>Phone Number</th>
                <th>Delete</th>
                <th>Update</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.code}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.birthDate}</td>
                <td>${employee.phoneNumber}</td>
                <td class="delete-form">
                    <a onclick="showConfirmationBox(${employee.code}, ${success})"><i class="fa-solid fa-trash" style="color: red"></i></a>
                </td>
                <td>
                    <c:url value='${pageContext.request.contextPath}/employees?action=edit&code=${employee.code}' var="editEmployeeURL" />
                    <a href="${editEmployeeURL}"><i class="fa-solid fa-pen-to-square" style="color: #21de21"></i></a>
                </td>
            </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>
        function showConfirmationBox(employeeCode, message) {
            Swal.fire({
                title: 'Do You Confirm Deletion?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    const form = document.createElement('form');
                    form.action = `${pageContext.request.contextPath}/employees?action=delete`;
                    form.method = 'post';

                    const codeInput = document.createElement('input');
                    codeInput.type = 'hidden';
                    codeInput.name = 'code';
                    codeInput.value = employeeCode;

                    form.appendChild(codeInput);
                    document.body.appendChild(form);
                    form.submit();

                    Swal.fire(message);
                }
            });
        }
    </script>


</body>
</html>
