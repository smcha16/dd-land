
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Yummy Bootstrap Template - Index</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/main.css" rel="stylesheet">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

  <!-- =======================================================
  * Template Name: Yummy
  * Updated: Sep 18 2023 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

  <style>
    #pagetitle {
      margin-top: 70px;
    }

    #title {
      font-size: 48px;
      display: block;
      color: #fff;
      font-weight: 700;
      margin-bottom: 20px;
    }

    .munti-content-container {
      display: flex;
      flex-wrap: wrap;
      margin: 30px 50px 0 50px;
      padding: 0 !important;
    }

    .item {
      position: relative;
      width: 25.5%;
      aspect-ratio: 0.75;
      padding: 0;
      box-sizing: border-box;
      min-width: 270px;
      border: 1px solid #E1E1E1;
      margin: 10px 45px 50px 45px;
      border-radius: 10px;
      transition: all 0.3s;
    }

    .item:hover {
      cursor: pointer;
      box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
    }

    .item>div:nth-child(1) {
      height: 70%;
      background-color: transparent;
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
      border-radius: 10px 10px 0 0;
    }

    .item>div:nth-child(2) {
      height: 30%;
      display: flex;
      flex-direction: column;
      padding: 20px;
      font-size: 1.3rem;
      font-weight: bold;
      background: transparent;
      border-radius: 0 0 10px 10px;
    }

    .hidden-div {
          display: none;
          color: white;
          position: absolute;
          top: 0;
          left: 0;
            width: 100%;
            height: 70%;
            padding: 20px;
            background-color: black;
            opacity: 0.65; /* 투명도 조절 */
            border-radius: 10px 10px 0 0;
            z-index: 1; /* 다른 요소들보다 위에 위치하도록 설정 */
    }

    /* 운영/운휴 셀렉박스 */
    .btn {
      position: relative;
      height: 56px;
      font-size: 16px !important;
      border: 0;
      cursor: default;
      width: 224px;
      margin: 0 auto;
    }

    .btn input {
      position: relative;
      width: 200px;
      height: 50px;
      border-radius: 25px;
      outline: none;
      cursor: pointer;
      appearance: none;
      font-weight: bold;
      box-shadow: 1px 6px 11px #000;
    }

    .btn input::before,
    .btn input::after {
      z-index: 2;
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      color: #111;
    }

    .btn input::before {
      content: "운영";
      left: 40px;
    }

    .btn input::after {
      content: "운휴";
      right: 40px;
    }

    .btn label {
      z-index: 1;
      position: absolute;
      top: 15px;
      bottom: 10px;
      border-radius: 20px;
      width: 86px;
    }

    .btn.btn-1 input {
      transition: 0.2s -0.1s;
    }

    .btn.btn-1 input:not(:checked) {
      background: rgba(255, 255, 255, 0.6);
    }

    .btn.btn-1 input:not(:checked):before {
      color: #111;
      transition: color 0.5s 0.2s;
    }

    .btn.btn-1 input:not(:checked):after {
      color: #111;
      transition: color 0.5s;
    }
    
    .btn.btn-1 input:not(:checked) + label {
      left: 24px;
      /* background: #D2AB21; */
      background: rgba(215, 62, 62, .7);
      transition: left 0.5s, right 0.4s 0.2s;
    }

    .btn.btn-1 input:checked {
      /* background: rgba(215, 62, 62, .7); */
      background: rgba(255, 255, 255, 0.6)
    }

    .btn.btn-1 input:checked::before {
      color: #111;
      transition: color 0.5s;
    }
    
    .btn.btn-1 input:checked::after {
      color: #1E1E1E;
      transition: color 0.5s 0.2s;
    }
    
    .btn.btn-1 input:checked + label {
      left: 114px; 
      background: rgba(215, 62, 62, .7);
      transition: left 0.4s 0.2s, right 0.5s, background 0.35s -0.1s;
    }

    /* list photo 변경 */
    .stats-counter {

      background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("assets/img/roller-coaster-7942853_1280.jpg") center center;
      background-size: cover;
      padding: 100px 0;
      background-attachment: fixed;
    }

  </style>
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <a href="index.html" class="logo d-flex align-items-center me-auto me-lg-0">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="assets/img/logo.png" alt=""> -->
        <h1>Yummy<span>.</span></h1>
      </a>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a href="#hero">Home</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#menu">Menu</a></li>
          <li><a href="#events">Events</a></li>
          <li><a href="#chefs">Chefs</a></li>
          <li><a href="#gallery">Gallery</a></li>
          <li class="dropdown"><a href="#"><span>Drop Down</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
            <ul>
              <li><a href="#">Drop Down 1</a></li>
              <li class="dropdown"><a href="#"><span>Deep Drop Down</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                <ul>
                  <li><a href="#">Deep Drop Down 1</a></li>
                  <li><a href="#">Deep Drop Down 2</a></li>
                  <li><a href="#">Deep Drop Down 3</a></li>
                  <li><a href="#">Deep Drop Down 4</a></li>
                  <li><a href="#">Deep Drop Down 5</a></li>
                </ul>
              </li>
              <li><a href="#">Drop Down 2</a></li>
              <li><a href="#">Drop Down 3</a></li>
              <li><a href="#">Drop Down 4</a></li>
            </ul>
          </li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </nav><!-- .navbar -->

      <a class="btn-book-a-table" href="#book-a-table">Book a Table</a>
      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

    </div>
  </header><!-- End Header -->

  <!-- ======= Stats Counter Section ======= -->
  <section id="stats-counter" class="stats-counter">
    <div id="pagetitle" class="container" data-aos="zoom-out">
      <div class="gy-4" style="justify-content: center; width: 100%;">
  
        <div class="col-lg-3 col-md-6" style="width: 100%;">
          <div class="stats-item text-center w-100 h-100">
            <div id="title">Attraction</div>
            <span class="btn btn-1"> <input type="checkbox" id="close"
              value="close"> <label for="close"></label>
            </span>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Stats Counter Section -->

  <!-- ======= Menu Section ======= -->
  <section id="menu" class="menu">
    <div class="container" data-aos="fade-up">
      <div class="tab-content" data-aos="fade-up" data-aos-delay="300">
        <div class="tab-pane fade active show" id="menu-starters">
          <div class="munti-content-container">
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
            <div class="item">
              <div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
              <div>어트랙션명</div>
              <div class="hidden-div">설명</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Menu Section -->
    
   <!-- ======= Footer ======= -->
   <footer id="footer" class="footer">

    <div class="container">
      <div class="row gy-3">
        <div class="col-lg-3 col-md-6 d-flex">
          <i class="bi bi-geo-alt icon"></i>
          <div>
            <h4>Address</h4>
            <p>
              A108 Adam Street <br>
              New York, NY 535022 - US<br>
            </p>
          </div>

        </div>

        <div class="col-lg-3 col-md-6 footer-links d-flex">
          <i class="bi bi-telephone icon"></i>
          <div>
            <h4>Reservations</h4>
            <p>
              <strong>Phone:</strong> +1 5589 55488 55<br>
              <strong>Email:</strong> info@example.com<br>
            </p>
          </div>
        </div>

        <div class="col-lg-3 col-md-6 footer-links d-flex">
          <i class="bi bi-clock icon"></i>
          <div>
            <h4>Opening Hours</h4>
            <p>
              <strong>Mon-Sat: 11AM</strong> - 23PM<br>
              Sunday: Closed
            </p>
          </div>
        </div>

        <div class="col-lg-3 col-md-6 footer-links">
          <h4>Follow Us</h4>
          <div class="social-links d-flex">
            <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
            <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
            <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
            <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
          </div>
        </div>

      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>Yummy</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>

  </footer><!-- End Footer -->
  <!-- End Footer -->

  <a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <div id="preloader"></div>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/aos/aos.js"></script>
  <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

  <script>
    var itemElements = document.querySelectorAll('.item');
  
    itemElements.forEach(function(item) {
      
      item.addEventListener('mouseover', function() {
        // 마우스 오버 시 hidden-div를 보이게 변경
        item.querySelector('.hidden-div').style.display = 'block';
      });
  
      item.addEventListener('mouseout', function() {
        // 마우스 아웃 시 hidden-div를 다시 숨김
        item.querySelector('.hidden-div').style.display = 'none';
          });
      
    });
  </script>

</body>

</html>