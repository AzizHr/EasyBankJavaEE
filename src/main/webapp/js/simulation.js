
function calculateMonthlyPayment() {
    let amount = document.getElementById("amount").value;
    let months = document.getElementById("months").value;
    let paidMonthly = document.getElementById("paidMonthly");

    console.log((parseFloat(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months))))
    let res = (parseFloat(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months)))
    paidMonthly.value = res.toFixed(2)

}

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

