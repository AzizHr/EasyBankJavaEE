<jsp:include page="../helpers/header.jsp" />
<jsp:include page="../helpers/navbar.jsp" />

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

<% String no_client_found = (String) request.getAttribute("no_client_found"); %>
<% if (no_client_found != null && !no_client_found.isEmpty()) { %>
<div class="error">
    <%= no_client_found %>
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
    <h2>Updating a client account</h2>
    <form action="${pageContext.request.contextPath}/clients?action=update" method="post">
        <div class="item item-1">
            <label>Code</label>
            <input type="text" name="client_code" placeholder="Enter employee code" required value="${client.code}" />
        </div>

        <div class="item item-2">
            <label>Firstname</label>
            <input type="text" name="client_firstname" placeholder="Enter client firstname" required value="${client.firstName}"/>
        </div>

        <div class="item item-3">
            <label class="lastname">Lastname</label>
            <input type="text" name="client_lastname" placeholder="Enter client lastname" required value="${client.lastName}"/>
        </div>

        <div class="item item-4">
            <label>Birthdate</label>
            <input type="date" name="client_birthdate" placeholder="Enter client birthdate" required value="${client.birthDate}"/>
        </div>

        <div class="item item-5">
            <label>Phone number</label>
            <input type="text" name="client_phone_number" placeholder="Enter client phone number" required value="${client.phoneNumber}"/>
        </div>

        <div class="item item-6">
            <label>Address</label>
            <input type="text" name="client_address" placeholder="Enter client address" required value="${client.address}"/>
        </div>

        <button class="bg-blue-500" type="submit">Submit</button>
    </form>
</div>

<jsp:include page="../helpers/footer.jsp" />