<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<%
    String id = (String)session.getAttribute("user");


%>


    <%@ include file="./layouts/userPage/header.jsp" %>
    <div id="wrap">
        <div>
            메인 페이지

            id : <%=id %>
        </div>

    </div>

    <%@ include file="./layouts/userPage/footer.jsp" %>


</body>
</html>





