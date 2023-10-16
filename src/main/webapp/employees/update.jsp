<%@ page import="entities.Employee" %><%--
  Created by IntelliJ IDEA.
  User: Aziz
  Date: 09/10/2023
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update An Employee</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/employee.css">
</head>
<body>
<!-- hello -->
<style>

    .error {
        color: #842029;
        background-color: #F8D7DA;
        padding: 16px 10px 16px 10px;
        border-radius: 4px;
        width: 50%;
        margin: 2% auto;
    }

    .success {
        color: #0F5132;
        background-color: #D1E7DD;
        padding: 16px 10px 16px 10px;
        border-radius: 4px;
        width: 50%;
        margin: 1% auto;
    }
</style>

<% String no_employee_found = (String) request.getAttribute("no_employee_found"); %>
<% if (no_employee_found != null && !no_employee_found.isEmpty()) { %>
<div class="error">
    <%= no_employee_found %>
</div>
<% } %>

<% String code_is_empty = (String) request.getAttribute("code_is_empty"); %>
<% if (code_is_empty != null && !code_is_empty.isEmpty()) { %>
<div class="error">
    <%= code_is_empty %>
</div>
<% } %>

<% String code_required = (String) request.getAttribute("code_required"); %>
<% if (code_required != null && !code_required.isEmpty()) { %>
<div class="error">
    <%= code_required %>
</div>
<% } %>

<style>

    .container {
        width: 60%;
        margin: 4% auto;
        display: grid;
        gap: 14px;
    }

    form {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-auto-rows: 1fr;
        grid-gap: 16px;
        width: 100%;
    }

    form .item {
        display: grid;
        gap: 6px;
        color: #3a3a3a;
    }

    form .item-1 {
        grid-column-start: 1;
        grid-column-end: 3;
    }

    form .item-2 {
        grid-column-start: 1;
        grid-column-end: 2;
    }

    form .item-3 {
        grid-column-start: 2;
        grid-column-end: 3;
    }

    form .item-4 {
        grid-column-start: 1;
        grid-column-end: 3;
    }

    form .item-5 {
        grid-column-start: 1;
        grid-column-end: 3;
    }

    form .item-6 {
        grid-column-start: 1;
        grid-column-end: 3;
    }

    form .item input {
        padding: 12px 6px 12px 6px;
        background-color: #f1f1f1;
        font-size: small;
        color: #444444;
        border: none;
    }

    form .item label::after {
        content: ' *';
        color: red;
    }

    form button {
        align-self: self-start;
        border: none;
        background-color: #2929ff;
        color: whitesmoke;
        font-weight: bold;
        letter-spacing: 1px;
        padding: 10px 4px 10px 4px;
    }


    @media (max-width: 767px) {
        .container {
            width: 90%;
        }

        form .item-2 {
            grid-column-end: 3;
        }

        form .item-3 {
            grid-column-start: 1;
        }
    }
</style>

<div class="container">
    <h2>Updating an employee account</h2>
    <form action="${pageContext.request.contextPath}/employees?action=update" method="post">
        <div class="item item-1">
            <label>Code</label>
            <input type="text" name="employee_code" placeholder="Enter employee code" required value="${employee.code}" />
        </div>

        <div class="item item-2">
            <label>Firstname</label>
            <input type="text" name="employee_firstname" placeholder="Enter employee firstname" required value="${employee.firstName}"/>
        </div>

        <div class="item item-3">
            <label class="lastname">Lastname</label>
            <input type="text" name="employee_lastname" placeholder="Enter employee lastname" required value="${employee.lastName}"/>
        </div>

        <div class="item item-4">
            <label>Birthdate</label>
            <input type="date" name="employee_birthdate" placeholder="Enter employee birthdate" required value="${employee.birthDate}"/>
        </div>

        <div class="item item-5">
            <label>Phone number</label>
            <input type="text" name="employee_phone_number" placeholder="Enter employee phone number" required value="${employee.phoneNumber}"/>
        </div>

        <div class="item item-6">
            <label>Email</label>
            <input type="email" name="employee_email" placeholder="Enter Employee Email" required value="${employee.email}"/>
        </div>

        <button type="submit">Submit</button>
    </form>
</div>

</body>
</html>
