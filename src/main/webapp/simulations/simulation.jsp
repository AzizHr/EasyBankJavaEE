<jsp:include page="../helpers/header.jsp" />
<jsp:include page="../helpers/navbar.jsp" />
<script>
<%@ include file="../js/simulation.js"%>
</script>

<div class="flex gap-10 w-fit mx-auto">
    <div class="font-manrope flex h-screen w-full items-center justify-center">
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
                <form onsubmit="e.preventDefault()" id="creditSimulation">
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
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="calculateMonthlyPayment()">Calculate</button></div>
                        <div onclick="showIdentificationForm()" class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button onclick="showIdentificationForm()" type="submit">Next</button></div>
                    </div>
                </form>
                <form class="hidden" action="" id="getIdentified">
                    <div class="text-center py-4 text-xl font-bold"><p>Get Identified</p></div>
                    <div class="mt-6">
                        <div class="font-semibold">Code</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="text" placeholder="Enter a number here" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">First Name</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="text" placeholder="Enter a number here" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Last Name</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="text" placeholder="How many months" /></div>
                    </div>
                    <div class="mt-6">
                        <div class="font-semibold">Phone</div>
                        <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" placeholder="How many months" /></div>
                    </div>

                    <div class="mt-6 flex gap-3">
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="showSimulation()">Previous</button></div>
                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button type="submit" onclick="showRegisterApplication()">Next</button></div>
                    </div>
                </form>
                <form class="hidden" action="" id="registerApplication">
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

                    <div class="mt-6 flex gap-3">
                        <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="showIdentificationForm()">Previous</button></div>
                        <div class="w-full cursor-pointer rounded-[4px] bg-green-700 px-3 py-[6px] text-center font-semibold text-white"><button type="submit">Submit</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="grid gap-3 w-1/2 mx-auto mt-10 border-2 rounded-sm border-gray-400 p-2">
        <span class="text-2xl text-center">My summary</span>
        <div class="grid gap-3">
            <span class="bg-gray-300 rounded-sm p-2 font-bold">My project</span>
            <span class="flex justify-between text-sm" id="finalProject">EasyBank</span>
        </div>
        <div class="grid gap-3">
            <span class="bg-gray-300 rounded-sm p-2 font-bold">Contact details and personal information</span>
            <div class="flex justify-between text-sm">
                <span>Email</span>
                <span class="text-green-500 font-bold" id="finalEmail">aziz@gmail.com</span>
            </div>
            <div class="flex justify-between text-sm">
                <span>Phone Number</span>
                <span class="text-green-500 font-bold" id="finalPhoneNumber">06 89 45 33 22</span>
            </div>
        </div>
        <div class="grid gap-1">
            <span class="bg-gray-300 rounded-sm p-2 font-bold">My credit details</span>
            <div class="flex justify-between text-sm">
                <span>You are</span>
                <span class="text-green-500 font-bold" id="finalJob">Working</span>
            </div>
            <div class="flex justify-between text-sm">
                <span>Price</span>
                <span class="text-green-500 font-bold" id="finalPrice">10 000 DH</span>
            </div>
            <div class="flex justify-between text-sm">
                <span>Duration</span>
                <span class="text-green-500 font-bold" id="finalDuration">28 months</span>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../helpers/footer.jsp" />
