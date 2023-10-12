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
    form {
        display: flex;
        flex-direction: column;
        gap: 16px;
        width: 50%;
        margin: 60px auto;
    }

    form h2:before{
        content: "";
        width: 4px;
        height: 10px;
        background-color: #21de21;
    }

    form div {
        display: flex;
        flex-direction: column;
        gap: 6px;
    }

    form button, div input {
        padding: 8px 6px 8px 6px;
    }

    form button {
        background-color: #21de21;
        align-self: start;
        border: none;
        border-radius: 4px;
        width: 80px;
    }
</style>



<% String success = (String) request.getAttribute("an_employee_updated_with_success"); %>
<% if (success != null && !success.isEmpty()) { %>
<div class="success">
    <%= success %>
</div>
<% } %>
<form action="${pageContext.request.contextPath}/employees?action=update" method="post">
    <h2>Update an employee</h2>
    <div>
        <label>Code</label>
        <input type="text" name="employee_code" placeholder="Enter Employee Code" value="${employee.code}"/>
    </div>

    <div>
        <label>First Name</label>
        <input type="text" name="employee_firstname" placeholder="Enter Employee First Name" value="${employee.firstName}"/>
    </div>

    <div>
        <label>Last Name</label>
        <input type="text" name="employee_lastname" placeholder="Enter Employee Last Name" value="${employee.lastName}"/>
    </div>

    <div>
        <label>Birth Date</label>
        <input type="date" name="employee_birthdate" placeholder="Enter Employee Birth Date" value="${employee.birthDate}"/>
    </div>

    <div>
        <label>Phone Number</label>
        <input type="text" name="employee_phone_number" placeholder="Enter Employee Phone Number" value="${employee.phoneNumber}"/>
    </div>

    <div>
        <label>Email</label>
        <input type="email" name="employee_email" placeholder="Enter Employee Email" value="${employee.email}"/>
    </div>

    <button type="submit">Submit</button>
</form>
</body>
</html>
