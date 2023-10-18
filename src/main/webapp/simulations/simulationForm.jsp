<%--
  Created by IntelliJ IDEA.
  User: yc
  Date: 10/18/2023
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Simulation Form</title>
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
</head>
<body>
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
    <form action="#">
      <div class="page slide-page">
        <div class="title">Simulation info:</div>
        <div class="field">
          <div class="label">Amount</div>
          <input type="number">
        </div>
        <div class="field">
          <div class="label">Months</div>
          <input type="number">
        </div>
        <div class="field">
          <div class="label">Paid monthly</div>
          <input type="number">
        </div>
        <div class="field">
          <button class="firstNext next">Next</button>
        </div>
      </div>

      <div class="page">
        <div class="title">Login in:</div>
        <div class="field">
          <div class="label">First Name</div>
          <input type="text">
        </div>
        <div class="field">
          <div class="label">Last Name</div>
          <input type="text">
        </div>
        <div class="field">
          <div class="label">code</div>
          <input type="Number">
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
          <input type="text">
        </div>
        <div class="field">
          <div class="label">Hired Job</div>
          <input type="date">
        </div>
        <div class="field">
          <div class="label">Monthly Income</div>
          <input type="number">
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
</body>
</html>
