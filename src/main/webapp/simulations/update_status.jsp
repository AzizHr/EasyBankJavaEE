<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="enums.DemandStatus" %>
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

</style>

<% String no_demand_found = (String) request.getAttribute("no_demand_found"); %>
<% if (no_demand_found != null && !no_demand_found.isEmpty()) { %>
<div class="error">
    <%= no_demand_found %>
</div>
<% } %>

<% String number_is_empty = (String) request.getAttribute("number_is_empty"); %>
<% if (number_is_empty != null && !number_is_empty.isEmpty()) { %>
<div class="error">
    <%= number_is_empty %>
</div>
<% } %>

<% String number_required = (String) request.getAttribute("number_required"); %>
<% if (number_required != null && !number_required.isEmpty()) { %>
<div class="error">
    <%= number_required %>
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
        grid-column-end: 3;
    }

    form .item input,
    form .item select {
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

    }
</style>

<div class="container">
    <h2>Updating an employee account</h2>
    <form action="${pageContext.request.contextPath}/simulations?action=update-status" method="post">
        <div class="item item-1">
            <label>Code</label>
            <input type="text" name="number" placeholder="Enter demand number" required value="${demand.number}" />
        </div>

        <div class="item item-2">
            <label>Status</label>
            <select name="status">
                <c:forEach items="${DemandStatus.values()}" var="status">
                    <option value="${status.name()}">${status.name()}</option>
                </c:forEach>
            </select>
        </div>

        <button class="bg-blue-500" type="submit">Submit</button>
    </form>
</div>

<jsp:include page="../helpers/footer.jsp" />