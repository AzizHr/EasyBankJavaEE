
function calculateMonthlyPayment() {
    let amount = document.getElementById("amount").value;
    let months = document.getElementById("months").value;
    let paidMonthly = document.getElementById("paid_monthly");
    let finalProject = document.getElementById("finalProject")
    let finalEmail = document.getElementById("finalEmail")
    let finalPhoneNumber = document.getElementById("finalPhoneNumber")
    let finalJob = document.getElementById("finalJob")
    let finalPrice = document.getElementById("finalPrice")
    let finalDuration = document.getElementById("finalDuration")

    finalPrice.innerText = amount.concat(' DH')
    finalDuration.innerText = months.concat(' months')

    console.log((parseFloat(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months))))
    let res = (parseFloat(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months)))
    paidMonthly.value = parseInt(res.toFixed(2))

}

function showSimulation(){

    document.getElementById("creditSimulation").classList.remove("hidden");
    document.getElementById("registerApplication").classList.add("hidden");
    document.getElementById("getIdentified").classList.add("hidden");
}

function showIdentificationForm(){

    let amount = document.getElementById("amount").value;
    let months = document.getElementById("months").value;
    let paidMonthly = document.getElementById("paid_monthly").value;

    let price_value = document.getElementById("price_value");
    let duration_value = document.getElementById("duration_value");
    let paid_monthly_value = document.getElementById("paid_monthly_value");

    price_value.value = amount;
    duration_value.value = months
    paid_monthly_value.value = paidMonthly


    document.getElementById("creditSimulation").classList.add("hidden");
    document.getElementById("registerApplication").classList.add("hidden");
    document.getElementById("getIdentified").classList.remove("hidden");
}

function showRegisterApplication(){

    let clientCode = document.getElementById("client_code").value

    let client_code_value = document.getElementById("client_code_value")

    client_code_value.value = clientCode
    document.getElementById("creditSimulation").classList.add("hidden");
    document.getElementById("registerApplication").classList.remove("hidden");
    document.getElementById("getIdentified").classList.add("hidden");
}

