<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.Client" %>
<jsp:include page="../helpers/header.jsp" />
<jsp:include page="../helpers/navbar.jsp" />
<script>
<%@ include file="../js/simulation.js"%>
</script>

<style>
    .success {
        color: #0F5132;
        background-color: #D1E7DD;
        padding: 16px 10px 16px 10px;
        border-radius: 4px;
        width: 50%;
        margin: 3% auto;
    }
</style>

<% String added_with_success = (String) request.getAttribute("demand_added_with_success"); %>
<% if (added_with_success != null && !added_with_success.isEmpty()) { %>
<div class="success">
    <%= added_with_success %>
</div>
<% } %>

<div style="margin-top: 100px" class="grid grid-cols-2 items-center gap-20 w-fit h-fit mx-auto">
    <div class="font-manrope flex h-screen items-center justify-center">
        <div class="mx-auto box-border w-[365px] border bg-white p-4">

            <div class="flex items-center justify-between">
                <div class="w-12 h-12 rounded-full bg-black text-white flex items-center justify-center">
                    <span class="text-xl">1</span>
                </div>
                <div class="w-12 h-12 rounded-full bg-black text-white flex items-center justify-center">
                    <span class="text-xl">2</span>
                </div>
                <div class="w-12 h-12 rounded-full bg-black text-white flex items-center justify-center">
                    <span class="text-xl">3</span>
                </div>

            </div>
            <div>
                <form id="creditSimulation">
                    <div class="text-center py-4 text-xl font-bold"><p>Credit Simulation</p></div>
                    <div class="mt-6">
                        <div class="font-semibold">Amount</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" id="amount" type="number" placeholder="Enter a number here" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Months</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" type="number" id="months" placeholder="How many months" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Amount to pay monthly</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" type="number" id="paid_monthly" placeholder="How many months" /></div>
                    </div>

                    <div class="mt-6 flex gap-3">
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="calculateMonthlyPayment()">Calculate</button></div>
                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="showIdentificationForm()">Next</button></div>
                    </div>
                </form>
                <form method="post" action="${pageContext.request.contextPath}/simulations?action=check-client" class="hidden" id="getIdentified">
                    <div class="text-center py-4 text-xl font-bold"><p>Get Identified</p></div>
                    <div class="mt-6">
                        <div class="font-semibold">Code</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" type="text" id="client_code" placeholder="Enter a number here" /></div>
                    </div>
                    <div class="mt-6">
                        <% Client client = (Client) request.getAttribute("client"); %>
                        <% if (client != null) { %>
                        <div>
                            Client Exists
                        </div>
                        <% }%>
                        <% String no_client_found = (String) request.getAttribute("no_client_found"); %>
                        <% if (no_client_found != null && !no_client_found.isEmpty()) { %>
                        <div>
                            Client Not Exists <a href="${pageContext.request.contextPath}/clients?action=view">create new one</a>
                        </div>
                        <% }%>
                    </div>
                    <div class="mt-6 flex gap-3">
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="submit">Check</button></div>
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="showSimulation()">Previous</button></div>
                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="showRegisterApplication()">Next</button></div>
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/simulations?action=create" method="post" class="hidden" id="registerApplication">
                    <div class="text-center py-4 text-xl font-bold"><p>Register Application</p></div>
                    <div class="mt-6">
                        <div class="font-semibold">CIN</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="text" placeholder="Enter a number here" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Revenue</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" placeholder="How many months" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Hiring Date</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="date" placeholder="How many months" /></div>
                    </div>
                    <div>
                        <input id="price_value" name="price" class="hidden mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" />
                        <input id="duration_value" name="duration" class="hidden mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" />
                        <input id="paid_monthly_value" name="paid_monthly" class="hidden mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" />
                        <input id="client_code_value" name="client_code" class="hidden mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" />
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Remarks</div>
                        <div><textarea name="remarks" class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" placeholder="Type some remarks"></textarea></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Agency</div>
                        <div><select name="agency_code" class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2">
                            <c:forEach items="${agencies}" var="agency">
                                <option value="${agency.code}">${agency.name}</option>
                            </c:forEach>
                        </select></div>
                    </div>
                    <div class="mt-6 flex gap-3">
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="showIdentificationForm()">Previous</button></div>
                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button type="submit">Submit</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="w-fit">
        <div class="grid gap-3 mx-auto mt-10 border-2 rounded-sm border-gray-200 p-2">
            <span class="text-2xl text-center">My summary</span>
            <div class="grid gap-3">
                <span class="bg-gray-300 rounded-sm font-bold p-2">My project</span>
                <span class="flex justify-between text-sm" id="finalProject">EasyBank</span>
            </div>
            <div class="grid gap-3">
                <span class="bg-gray-300 rounded-sm font-bold p-2">Contact details and personal information</span>
                <div class="flex justify-between text-sm">
                    <span>Email</span>
                    <span class="text-green-600 font-bold" id="finalEmail">aziz@gmail.com</span>
                </div>
                <div class="flex justify-between text-sm">
                    <span>Phone Number</span>
                    <span class="text-green-600 font-bold" id="finalPhoneNumber">06 89 45 33 22</span>
                </div>
            </div>
            <div class="grid gap-3">
                <span class="bg-gray-300 rounded-sm font-bold p-2">My credit details</span>
                <div class="flex justify-between text-sm">
                    <span>You are</span>
                    <span class="text-green-600 font-bold" id="finalJob">Working</span>
                </div>
                <div class="flex justify-between text-sm">
                    <span>Price</span>
                    <span class="text-green-600 font-bold" id="finalPrice"></span>
                </div>
                <div class="flex justify-between text-sm">
                    <span>Duration</span>
                    <span class="text-green-600 font-bold" id="finalDuration"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../helpers/footer.jsp" />
