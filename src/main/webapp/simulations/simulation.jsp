<jsp:include page="../helpers/header.jsp" />
<jsp:include page="../helpers/navbar.jsp" />
<script>
<%@ include file="../js/simulation.js"%>
</script>

<style>
    @import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');
    *{
        margin: 0;
        padding: 0;
        outline: none;
        font-family: 'Poppins', sans-serif;
    }
    body{
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
        overflow: hidden;
        background: -webkit-linear-gradient(bottom, #0250c5, #d43f8d);
    }
    ::selection{
        color: #fff;
        background: #d43f8d;
    }
    .container{
        width: 330px;
        background: #fff;
        text-align: center;
        border-radius: 5px;
        padding: 50px 35px 10px 35px;
    }
    .container header{
        font-size: 35px;
        font-weight: 600;
        margin: 0 0 30px 0;
    }
    .container .form-outer{
        width: 100%;
        overflow: hidden;
    }
    .container .form-outer form{
        display: flex;
        width: 400%;
    }
    .form-outer form .page{
        width: 25%;
        transition: margin-left 0.3s ease-in-out;
    }
    .form-outer form .page .title{
        text-align: left;
        font-size: 25px;
        font-weight: 500;
    }
    .form-outer form .page .field{
        width: 330px;
        height: 45px;
        margin: 45px 0;
        display: flex;
        position: relative;
    }
    form .page .field .label{
        position: absolute;
        top: -30px;
        font-weight: 500;
    }
    form .page .field input{
        height: 100%;
        width: 100%;
        border: 1px solid lightgrey;
        border-radius: 5px;
        padding-left: 15px;
        font-size: 18px;
    }
    form .page .field select{
        width: 100%;
        padding-left: 10px;
        font-size: 17px;
        font-weight: 500;
    }
    form .page .field button{
        width: 100%;
        height: calc(100% + 5px);
        border: none;
        background: #d33f8d;
        margin-top: -20px;
        border-radius: 5px;
        color: #fff;
        cursor: pointer;
        font-size: 18px;
        font-weight: 500;
        letter-spacing: 1px;
        text-transform: uppercase;
        transition: 0.5s ease;
    }
    form .page .field button:hover{
        background: #000;
    }
    form .page .btns button{
        margin-top: -20px!important;
    }
    form .page .btns button.prev{
        margin-right: 3px;
        font-size: 17px;
    }
    form .page .btns button.next{
        margin-left: 3px;
    }
    .container .progress-bar{
        display: flex;
        margin: 40px 0;
        user-select: none;
    }
    .container .progress-bar .step{
        text-align: center;
        width: 100%;
        position: relative;
    }
    .container .progress-bar .step p{
        font-weight: 500;
        font-size: 18px;
        color: #000;
        margin-bottom: 8px;
    }
    .progress-bar .step .bullet{
        height: 25px;
        width: 25px;
        border: 2px solid #000;
        display: inline-block;
        border-radius: 50%;
        position: relative;
        transition: 0.2s;
        font-weight: 500;
        font-size: 17px;
        line-height: 25px;
    }
    .progress-bar .step .bullet.active{
        border-color: #d43f8d;
        background: #d43f8d;
    }
    .progress-bar .step .bullet span{
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
    }
    .progress-bar .step .bullet.active span{
        display: none;
    }
    .progress-bar .step .bullet:before,
    .progress-bar .step .bullet:after{
        position: absolute;
        content: '';
        bottom: 11px;
        right: -51px;
        height: 3px;
        width: 44px;
        background: #262626;
    }
    .progress-bar .step .bullet.active:after{
        background: #d43f8d;
        transform: scaleX(0);
        transform-origin: left;
        animation: animate 0.3s linear forwards;
    }
    @keyframes animate {
        100%{
            transform: scaleX(1);
        }
    }
    .progress-bar .step:last-child .bullet:before,
    .progress-bar .step:last-child .bullet:after{
        display: none;
    }
    .progress-bar .step p.active{
        color: #d43f8d;
        transition: 0.2s linear;
    }
    .progress-bar .step .check{
        position: absolute;
        left: 50%;
        top: 70%;
        font-size: 15px;
        transform: translate(-50%, -50%);
        display: none;
    }
    .progress-bar .step .check.active{
        display: block;
        color: #fff;
    }

</style>
<div class="container">
    <header>Credit simulation Form</header>
    <div class="progress-bar">
        <div class="step">
            <p>Simulation</p>
            <div class="bullet">
                <span>1</span>
            </div>
            <div class="check fas fa-check"></div>
        </div>
        <div class="step">
            <p>Contact</p>
            <div class="bullet">
                <span>2</span>
            </div>
            <div class="check fas fa-check"></div>
        </div>
        <div class="step">
            <p>Birth</p>
            <div class="bullet">
                <span>3</span>
            </div>
            <div class="check fas fa-check"></div>
        </div>
        <div class="step">
            <p>Submit</p>
            <div class="bullet">
                <span>4</span>
            </div>
            <div class="check fas fa-check"></div>
        </div>
    </div>
    <div class="form-outer">
        <form onsubmit="e.preventDefault()">
            <div class="page slide-page">
                <div class="title">Simulation info:</div>
                <div class="field">
                    <div class="label">Amount</div>
                    <input type="number" step="0.01" name="amount" id="amount">
                </div>
                <div class="field">
                    <div class="label">Months</div>
                    <input type="number" name="months" id="months">
                </div>
                <div class="field">
                    <div class="label">Paid monthly</div>
                    <input type="number" step="0.01" name="paidMonthly" id="paidMonthly">
                </div>
                <div class="field">
                    <button class="firstNext next">Next</button>
                    <button class="bg-red-500 p-4 rounded-sm border-none" onclick="calculateMonthlyPayment()">Calculate</button>
                </div>
            </div>

            <div class="page">
                <div class="title">Login in:</div>
                <div class="field">
                    <div class="label">First Name</div>
                    <input type="text" name="firstname" id="firstname">
                </div>
                <div class="field">
                    <div class="label">Last Name</div>
                    <input type="text" name="lastname" id="lastname">
                </div>
                <div class="field">
                    <div class="label">code</div>
                    <input type="Number" name="code" id="code">
                </div>
                <div class="field btns">
                    <button class="prev-1 prev">Previous</button>
                    <button class="next-1 next">Next</button>
                </div>
            </div>

            <div class="page">
                <div class="title">Registration Form:</div>
                <div class="field">
                    <div class="label">CIN</div>
                    <input type="text" name="cin" id="cin">
                </div>
                <div class="field">
                    <div class="label">Hired Job</div>
                    <input type="date" name="hiredAt" id="hiredAt">
                </div>
                <div class="field">
                    <div class="label">Monthly Income</div>
                    <input type="number" name="monthlyIncome" id="monthlyIncome">
                </div>
                <div class="field btns">
                    <button class="prev-2 prev">Previous</button>
                    <button class="next-2 next">Submit</button>
                </div>
            </div>

            <!-- <div class="page">
              <div class="title">Login Details:</div>
              <div class="field">
                <div class="label">Username</div>
                <input type="text">
              </div>
              <div class="field">
                <div class="label">Password</div>
                <input type="password">
              </div>
              <div class="field btns">
                <button class="prev-3 prev">Previous</button>
                <button class="submit">Submit</button>
              </div>
            </div> -->
        </form>
    </div>
</div>

<%--Details--%>

<div class="grid gap-4 w-1/4 mx-auto mt-10 border-2 rounded-sm border-gray-400 p-2">
    <span class="text-2xl text-center">My summary</span>
    <div class="grid gap-2">
        <span class="bg-gray-300 rounded-sm p-2 font-bold">My project</span>
        <span class="flex justify-between text-sm" id="finalProject">EasyBank</span>
    </div>
    <div class="grid gap-2">
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
    <div class="grid gap-2">
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

<%--haytam--%>



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
            <form class="hidden" action="" id="creditSimulation">
                <div class="text-center py-4 text-xl font-bold"><p>Credit Simulation</p></div>
                <div class="mt-6">
                    <div class="font-semibold">Amount</div>
                    <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" placeholder="Enter a number here" /></div>
                </div>
                <div class="mt-6">
                    <div class="font-semibold">Months</div>
                    <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" placeholder="How many months" /></div>
                </div>
                <div class="mt-6">
                    <div class="font-semibold">Amount to pay monthly</div>
                    <div><input class="mt-1 w-full rounded-[4px] border border-[#A0ABBB] p-2" value="" type="number" placeholder="How many months" /></div>
                </div>

                <div class="mt-6 flex gap-3">
                    <div class="w-full cursor-pointer rounded-[4px] bg-orange-500 px-3 py-[6px] text-center font-semibold text-white"><button type="button" onclick="showIdentificationForm()">Calculate</button></div>
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
            <form class="" action="" id="registerApplication">
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
<script>
    function showSimulation(){
        document.getElementById("creditSimulation").classList.remove("hidden");
        document.getElementById("registerApplication").classList.add("hidden");
        document.getElementById("getIdentified").classList.add("hidden");
    }

    function showIdentificationForm(){
        document.getElementById("creditSimulation").classList.add("hidden");
        document.getElementById("registerApplication").classList.add("hidden");
        document.getElementById("getIdentified").classList.remove("hidden");
    }

    function showRegisterApplication(){
        document.getElementById("creditSimulation").classList.add("hidden");
        document.getElementById("registerApplication").classList.remove("hidden");
        document.getElementById("getIdentified").classList.add("hidden");
    }
</script>




<script>
    const slidePage = document.querySelector(".slide-page");
    const nextBtnFirst = document.querySelector(".firstNext");
    const prevBtnSec = document.querySelector(".prev-1");
    const nextBtnSec = document.querySelector(".next-1");
    const prevBtnThird = document.querySelector(".prev-2");
    const nextBtnThird = document.querySelector(".next-2");
    // const prevBtnFourth = document.querySelector(".prev-3");
    // const submitBtn = document.querySelector(".submit");
    const progressText = document.querySelectorAll(".step p");
    const progressCheck = document.querySelectorAll(".step .check");
    const bullet = document.querySelectorAll(".step .bullet");
    let current = 1;

    nextBtnFirst.addEventListener("click", function(event){
        event.preventDefault();
        slidePage.style.marginLeft = "-25%";
        bullet[current - 1].classList.add("active");
        progressCheck[current - 1].classList.add("active");
        progressText[current - 1].classList.add("active");
        current += 1;
    });
    nextBtnSec.addEventListener("click", function(event){
        event.preventDefault();
        slidePage.style.marginLeft = "-50%";
        bullet[current - 1].classList.add("active");
        progressCheck[current - 1].classList.add("active");
        progressText[current - 1].classList.add("active");
        current += 1;
    });
    nextBtnThird.addEventListener("click", function(event){
        event.preventDefault();
        slidePage.style.marginLeft = "-75%";
        bullet[current - 1].classList.add("active");
        progressCheck[current - 1].classList.add("active");
        progressText[current - 1].classList.add("active");
        current += 1;
    });
    submitBtn.addEventListener("click", function(){
        bullet[current - 1].classList.add("active");
        progressCheck[current - 1].classList.add("active");
        progressText[current - 1].classList.add("active");
        current += 1;
        setTimeout(function(){
            alert("Your Form Successfully Signed up");
            location.reload();
        },800);
    });

    prevBtnSec.addEventListener("click", function(event){
        event.preventDefault();
        slidePage.style.marginLeft = "0%";
        bullet[current - 2].classList.remove("active");
        progressCheck[current - 2].classList.remove("active");
        progressText[current - 2].classList.remove("active");
        current -= 1;
    });
    prevBtnThird.addEventListener("click", function(event){
        event.preventDefault();
        slidePage.style.marginLeft = "-25%";
        bullet[current - 2].classList.remove("active");
        progressCheck[current - 2].classList.remove("active");
        progressText[current - 2].classList.remove("active");
        current -= 1;
    });
    prevBtnFourth.addEventListener("click", function(event){
        event.preventDefault();
        slidePage.style.marginLeft = "-50%";
        bullet[current - 2].classList.remove("active");
        progressCheck[current - 2].classList.remove("active");
        progressText[current - 2].classList.remove("active");
        current -= 1;
    });

</script>

<jsp:include page="../helpers/footer.jsp" />
