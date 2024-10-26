<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Chat Room</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
<%
  String username = (String)session.getAttribute("user");
%>

<h1>${chatroom.chatroomName}</h1>


<div id="chatMessages">
  <c:forEach items="${previousMessage}" var="msg">
    <p><strong>${msg.messageSender} :</strong> ${msg.messageContent}</p>
  </c:forEach>


</div>

<input type="text" id="messageDto" placeholder="Enter messageDto">
<button onclick="sendMessage()">Send</button>

<script>
  var roomNo = ${chatroom.chatroomNo};
  const username = "<%= username%>";
  var stompClient = null;

  function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/messages/' + roomNo, function(messageOutput) {
        showMessage(JSON.parse(messageOutput.body));
      });

      // 채팅방 입장했을 때
      stompClient.send("/app/enter/" + roomNo, {}, JSON.stringify({messageSender: username}));
    });
  }

  function sendMessage() {
    var messageContent = $("#messageDto").val().trim();
    if(messageContent && stompClient) {
      var chatMessage = {
        chatroomNo: roomNo,
        sender: username,
        messageContent: messageContent,

      };
      stompClient.send("/app/send/" + roomNo, {}, JSON.stringify(chatMessage));
      $("#messageDto").val('');
    }
  }

  function showMessage(messageDto) {
    $("#chatMessages").append('<p><strong>' + messageDto.sender + ':</strong> ' + messageDto.messageContent + '</p>');
  }

  $(document).ready(function() {
    connect();
  });
</script>
</body>
</html>