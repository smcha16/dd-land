<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
#reveal {
        width: 100%;
        height: 100vh;
        background: url("../img/reveal-bg.jpg") top center;
        background-size: cover;
    }

    #reveal:before {
        content: "";
        background: rgba(5, 13, 24, 0.3);
        position: absolute;
        bottom: 0;
        top: 0;
        left: 0;
        right: 0;
        z-index: 1;
    }

    #reveal .reveal-container {
        position: relative;
        z-index: 2;
        min-width: 300px;
    }

    #reveal h1 {
        margin: 0 0 10px 0;
        font-size: 64px;
        font-weight: 700;
        line-height: 56px;
        color: #fff;
    }

    #reveal p {
        color: #fff;
        margin-bottom: 50px;
        font-size: 26px;
        font-family: "Poppins", sans-serif;
    }

    #reveal p span {
        color: #fff;
        padding-bottom: 4px;
        letter-spacing: 1px;
        border-bottom: 3px solid #149ddd;
    }

    @media (min-width: 1024px) {
        #reveal {
            background-attachment: fixed;
        }
    }

    @media (max-width: 768px) {
        #reveal h1 {
            font-size: 28px;
            line-height: 36px;
        }

        #reveal h2 {
            font-size: 18px;
            line-height: 24px;
            margin-bottom: 30px;
        }
    }
</style>
<!DOCTYPE html>
	<section id="reveal">
        <div class="reveal-container" data-aos="fade-in">
            <p><span class="typed" data-typed-items="안녕하세요 관리자님, 안녕하세요 김수민님, 환영합니다"></span></p>
        </div>
    </section>

    <script>
        const typed = document.querySelector('.typed')
        if (typed) {
            let typed_strings = typed.getAttribute('data-typed-items')
            typed_strings = typed_strings.split(',')
            new Typed('.typed', {
                strings: typed_strings,
                loop: true,
                typeSpeed: 100,
                backSpeed: 50,
                backDelay: 2000
            });
        }
    </script>
  <main id="main" class="main">

    <div class="pagetitle">
      <h1>어트랙션 관리</h1>
    </div>

    <section class="section">
      <div class="row">
        <div class="col-lg-8">
          <div class="row">
            <div class="col-12">

              <div id="search" class="header">
                  <form class="search-form d-flex align-items-center" method="POST" action="#">
                    <input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    <button type="submit" title="Search"><i class="bi bi-search"></i></button>
                  </form>
              </div>

              <div class="card">
                <div class="card-body">

                  <nav class="d-flex justify-content-end">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="index.html">추가</a></li>
                      <li class="breadcrumb-item"><a href="#">수정</a></li>
                      <li class="breadcrumb-item active"><a href="#">삭제</a></li>
                    </ol>
                  </nav>
                  
                  <table class="table">
                    <thead>
                      <tr>
                        <th></th>
                        <th>No</th>
                        <th>이름</th>
                        <th>위치</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                      <tr>
                        <td><input type="checkbox" name="attraction_checkbox"></td>
                        <td>1</td>
                        <td>회전목마</td>
                        <td>위치정보</td>
                      </tr>
                    </tbody>
                  </table>

                  <ul class="pagination pagination-sm">
                    <li class="page-item active" aria-current="page">
                      <span class="page-link">1</span>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">4</a></li>
                    <li class="page-item"><a class="page-link" href="#">5</a></li>
                  </ul>
                </div>

              </div>
            </div>

          </div>
        </div>

      </div>
    </section>

  </main>