// let amount = document.getElementById("amount").value
// let months = document.getElementById("months").value
// let paidMonthly = document.getElementById("paidMonthly").value
// // let monthlyIncome = GET_BY_ID("monthlyIncome")
// // let email = GET_BY_ID("email")
// // let phoneNumber = GET_BY_ID("phoneNumber")
// // let project = GET_BY_ID("project")
// // let job = GET_BY_ID("job")
// // let calculate = GET_BY_ID("calculate")
//
//
// // calculate.addEventListener("click", () => {
// //
// //     calculateMonthlyPayment(amount, months)
// // })
//
// function calculateMonthlyPayment() {
//     console.log((parseInt(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months))))
//     paidMonthly.value = (parseInt(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months)))
// }

function calculateMonthlyPayment() {
    let amount = document.getElementById("amount").value;
    let months = document.getElementById("months").value;
    let paidMonthly = document.getElementById("paidMonthly");

    console.log((parseFloat(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months))))
    let res = (parseFloat(amount) * (0.05 / 12)) / (1 - Math.pow((1 + (0.05 / 12)), -parseInt(months)))
    paidMonthly.value = res.toFixed(2)

}

// Uncomment the event listener
// let calculate = document.getElementById("calculate");
// calculate.addEventListener("click", calculateMonthlyPayment);

