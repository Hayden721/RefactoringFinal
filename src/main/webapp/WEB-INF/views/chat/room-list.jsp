<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
</head>
<body>
<%
    String id = (String)session.getAttribute("user");
%>

<%@ include file="../layouts/userPage/header.jsp" %>
  <div>
    <h1>이곳은 채팅창</h1>
    <hr>

      <!-- Button trigger modal -->
      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
          채팅방 추가
      </button>


          <c:choose>
              <c:when test="${empty roomList}">
                <p>채팅방이 없습니다.</p>
              </c:when>

              <c:otherwise>
                  <ul>
                      <c:forEach items="${roomList}" var="room">
                          <li><a href="/chat/room/${room.chatroomNo}" target="_blank">${room.chatroomName}</a></li>
                      </c:forEach>
                  </ul>
              </c:otherwise>
          </c:choose>

      <!-- Modal -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
              <div class="modal-content">

                  <form action="<c:url value="/chat/room/create"/>" method="post">

                  <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                      <label> 채팅방 제목
                        <input class="form-control" type="text" placeholder="Default input" aria-label="default input example" name="chatroomName">
                      </label>
                        <input value="<%= id%>" name="username" />
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                      <button type="submit" class="btn btn-primary">채팅방 생성</button>
                  </div>

                  </form>

              </div>
          </div>
      </div>
  </div>

<%@ include file="../layouts/userPage/footer.jsp" %>
</body>
</html>
