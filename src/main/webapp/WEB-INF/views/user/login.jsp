<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <%--  bootStrap  --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <script src="../resources/js/user/login.js"></script>
    <link href="../resources/css/user/login.css" rel="stylesheet" type="text/css">

</head>
<body>


    <div class="login-container">

        <form id="login-form" action="<c:url value="/user/login"/>" method="post">
            <div class="mb-3">
                <label for="id-input" class="form-label">ID</label>
                <input type="text" class="form-control" id="id-input" name="idValue">
            </div>
            <div class="mb-3">
                <label for="pw-input" class="form-label">PW</label>
                <input type="password" class="form-control" id="pw-input" name="pwValue">
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

</body>






</html>
