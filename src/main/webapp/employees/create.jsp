<jsp:include page="../helpers/header.jsp" />
<jsp:include page="../helpers/navbar.jsp" />

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
        <h2>Creating a new employee account</h2>
        <form action="${pageContext.request.contextPath}/employees?action=create" method="post">
            <div class="item item-1">
                <label>Code</label>
                <input type="text" name="employee_code" placeholder="Enter employee code" required/>
            </div>

            <div class="item item-2">
                <label>Firstname</label>
                <input type="text" name="employee_firstname" placeholder="Enter employee firstname" required/>
            </div>

            <div class="item item-3">
                <label class="lastname">Lastname</label>
                <input type="text" name="employee_lastname" placeholder="Enter employee lastname" required/>
            </div>

            <div class="item item-4">
                <label>Birthdate</label>
                <input type="date" name="employee_birthdate" placeholder="Enter employee birthdate" required/>
            </div>

            <div class="item item-5">
                <label>Phone number</label>
                <input type="text" name="employee_phone_number" placeholder="Enter employee phone number" required/>
            </div>

            <div class="item item-6">
                <label>Email</label>
                <input type="email" name="employee_email" placeholder="Enter Employee Email" required/>
            </div>

            <button class="bg-green-500" type="submit">Submit</button>
        </form>
    </div>

<jsp:include page="../helpers/footer.jsp" />
