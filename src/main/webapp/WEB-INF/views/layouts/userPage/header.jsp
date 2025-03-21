<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%--  bootStrap  --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <%--  jquery  --%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

    <link href="<c:url value="/resources/css/commons/layout.css"/>" rel="stylesheet" type="text/css">


</head>
<body>
<%-- header --%>
<%
//    boolean loginValid = (boolean)session.getAttribute("login");


%>


<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav ">
                <c:if test="${!sessionScope.login}" >
                    <a class="nav-link" href="<c:url value="/user/login"/>">로그인</a>
                    <a class="nav-link" href="<c:url value="/chat/room/list"/>">채팅</a>
                </c:if>

                <c:if test="${sessionScope.login}" >
                    <a class="nav-link" href="<c:url value="/user/logout"/>">로그아웃</a>
                    <a class="nav-link" href="<c:url value="/chat/room/list"/>">채팅</a>
                    <a class="nav-link" href="<c:url value="/user/mypage"/>">마이페이지</a>
                </c:if>
            </div>
        </div>
    </div>
</nav>


