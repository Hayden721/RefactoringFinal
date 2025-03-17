<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<%@ include file="../layouts/userPage/header.jsp" %>
<div id="wrap">
    <div>
        <ul>
            <c:forEach items="${subChatroom}" var="sub">

                <li>
                    <a href="/chat/room/${sub.chatroomNo}">${sub.chatroomName}</a>
                </li>

            </c:forEach>
        </ul>
    </div>

</div>

<%@ include file="../layouts/userPage/footer.jsp" %>


</body>
</html>





