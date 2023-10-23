
function calculateMonthlyPayment(event) {
    event.preventDefault()
    let amount = document.getElementById("amount").value;
    let months = document.getElementById("months").value;
    let paidMonthly = document.getElementById("paidMonthly");
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
    paidMonthly.value = parseInt(res.toFixed(0))

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

