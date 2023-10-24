<jsp:include page="../helpers/header.jsp" />
<jsp:include page="../helpers/navbar.jsp" />
<script>
<%@ include file="../js/simulation.js"%>
</script>

<div class="grid grid-cols-2 items-center gap-20 w-fit h-fit mx-auto">
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
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" id="months" placeholder="How many months" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Amount to pay monthly</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" id="paidMonthly" placeholder="How many months" /></div>
                    </div>

                    <div class="mt-6 flex gap-3">
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button onclick="calculateMonthlyPayment()">Calculate</button></div>
                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button onclick="showIdentificationForm()">Next</button></div>
                    </div>
                </form>
<%--                <form onsubmit="e.preventDefault()" class="hidden" id="getIdentified">--%>
<%--                    <div class="text-center py-4 text-xl font-bold"><p>Get Identified</p></div>--%>
<%--                    <div class="mt-6">--%>
<%--                        <div class="font-semibold">Code</div>--%>
<%--                        <div><input name="code" class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="text" placeholder="Enter a number here" /></div>--%>
<%--                    </div>--%>
<%--                    <div class="mt-6">--%>
<%--                        <div class="font-semibold">Phone number</div>--%>
<%--                        <div><input name="phone_number" class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" placeholder="How many months" /></div>--%>
<%--                    </div>--%>

<%--                    <div class="mt-6 flex gap-3">--%>
<%--                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button onclick="showSimulation()">Previous</button></div>--%>
<%--                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button onclick="showRegisterApplication()">Next</button></div>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--                <form onsubmit="e.preventDefault()" class="hidden" id="registerApplication">--%>
<%--                    <div class="text-center py-4 text-xl font-bold"><p>Register Application</p></div>--%>
<%--                    <div class="mt-6">--%>
<%--                        <div class="font-semibold">CIN</div>--%>
<%--                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="text" placeholder="Enter a number here" /></div>--%>
<%--                    </div>--%>
<%--                    <div class="mt-6">--%>
<%--                        <div class="font-semibold">Revenue</div>--%>
<%--                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" placeholder="How many months" /></div>--%>
<%--                    </div>--%>
<%--                    <div class="mt-6">--%>
<%--                        <div class="font-semibold">Hiring Date</div>--%>
<%--                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="date" placeholder="How many months" /></div>--%>
<%--                    </div>--%>
<%--                    <div class="mt-6">--%>
<%--                        <div class="font-semibold">Remarks</div>--%>
<%--                        <div><textarea name="remarks" class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" placeholder="Type some remarks"></textarea></div>--%>
<%--                    </div>--%>
<%--                    <div class="mt-6 flex gap-3">--%>
<%--                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button onclick="showIdentificationForm()">Previous</button></div>--%>
<%--                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button>Submit</button></div>--%>
<%--                    </div>--%>
<%--                </form>--%>
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
