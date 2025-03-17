<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <c:forEach items="${connectUser}" var="user">
        <p>${user}</p>
    </c:forEach>

    ajax 성공
</div>

</body>
</html>
