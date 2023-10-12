<%--
  Created by IntelliJ IDEA.
  User: Aziz
  Date: 09/10/2023
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update A Client</title>
</head>
<body>


<style>
    .success {
        background-color: #aaffaa;
        color: #007e00;
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



<form action="/update-a-client" method="post">
    <h2>Update a client</h2>
    <div>
        <label>Code</label>
        <input type="text" name="client_code" placeholder="Enter client code"/>
    </div>

    <div>
        <label>First Name</label>
        <input type="text" name="client_firstname" placeholder="Enter client firstname"/>
    </div>

    <div>
        <label>Last Name</label>
        <input type="text" name="client_lastname" placeholder="Enter client lastname"/>
    </div>

    <div>
        <label>Birth Date</label>
        <input type="date" name="client_birthdate" placeholder="Enter client birthdate"/>
    </div>

    <div>
        <label>Address</label>
        <input type="text" name="client_address" placeholder="Enter client phone number"/>
    </div>

    <button type="submit">Submit</button>
</form>

</body>
</html>
