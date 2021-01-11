<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<main class="container my-5">
    <h1>안녕~~!</h1>
    <a class="btn btn-outline-danger m-3" href="${contextPath}/members">멤버전체보기</a><br>
    <form action="${contextPath}/members/new" method="post">
        <h3>멤버추가</h3>
        <input type="text" name="id" placeholder="아이디">
        <input type="text" name="name" placeholder="닉네임">
        <input type="password" name="password" placeholder="비번">
        <input type="submit" class="btn btn-outline-primary" value="확인">
    </form>

    <h3>아이디 중복 확인</h3>
    <input type="text" id="id">
    <input type="button" class="btn btn-outline-success" id="btn1" value="xhr 확인">
    <input type="button" class="btn btn-outline-success" id="btn2" value="fetch 확인">
    <span id="result"></span>
</main>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const btn1 = document.querySelector("#btn1");
        btn1.onclick = () => {
            const id = document.querySelector("#id").value;
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if(this.readyState === 4){
                    if(this.status === 200){
                        const msg = this.responseText;
                        const result = document.querySelector("#result");
                        if(msg === "1") {
                            result.textContent = '중복된 아이디입니다.';
                            result.style.color = '#F00';
                        }
                        else {
                            result.textContent = '사용가능한 아이디입니다.';
                            result.style.color = '#00F';
                        }
                    }
                }
            }
            xhr.open('GET','/members/' + id ,true);
            xhr.send();
        }
        const btn2 = document.querySelector("#btn2");
        btn2.addEventListener('click', async () => {
            const id = document.querySelector("#id").value;
            const response = await fetch('/members/' + id);
            if (!response.ok) {
                console.log('Error');
            }
            const msg = await response.text();
            const result = document.querySelector("#result");
            if(msg === "1") {
                result.textContent = '중복된 아이디입니다.';
                result.style.color = '#F00';
            }
            else {
                result.textContent = '사용가능한 아이디입니다.';
                result.style.color = '#00F';
            }
        });
    });
</script>
</body>
</html>