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
<button id="exit-chatroom">나가기</button>

<div id="chat-messages">
  <c:forEach items="${previousMessage}" var="msg">
    <p><strong>${msg.messageSender} :</strong> ${msg.messageContent}</p>
  </c:forEach>

</div>

<input type="text" id="message-input" placeholder="Enter messageDto">
<button id="message-send-btn">Send</button>

<script>
  $(document).ready(function() {
    connect();

    $('#message-send-btn').click(function () {
      sendMessage();
    })


  });

  var roomNo = ${chatroom.chatroomNo};
  const usernameSession = "<%= username%>";
  var stompClient = null;

  // websocket 연결
  function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
      console.log('Connected: ' + frame);
      // 구독된 채팅방에만 메시지 뿌리기
      stompClient.subscribe('/topic/messages/' + roomNo, function(chatMessage) {
        showMessage(JSON.parse(chatMessage.body));
      });

      // 채팅방 입장했을 때
      stompClient.send("/app/enter/" + roomNo,
              {},
              JSON.stringify({
                sender: usernameSession,
              })
      );

      $('#exit-chatroom').click(function () {
        leaveRoom();
      })

    });
  }
  // 메시지 보내기
  function sendMessage() {
    var messageContent = $("#message-input").val().trim();
    if(messageContent && stompClient) {
      var chatMessage = {
        chatroomNo: roomNo,
        sender: usernameSession,
        messageContent: messageContent,
        type: 'CHAT'
      };
      stompClient.send("/app/send/" + roomNo, {}, JSON.stringify(chatMessage));
      $("#message-input").val('');
    }
  }


  function showMessage(message) {
    console.log('Message type:', message.type);
    console.log('Message sender:', message.sender); // 또는 message.messageSender
    console.log('Message content:', message.messageContent);
    let messageElement;

    switch (message.type) {
      case 'EXIT':
        messageElement = `<p class="text-center"><em>` + message.messageContent + `</em></p>`;
        break;
      case 'ENTER':
        messageElement = `<p class="text-center"><em>` +message.messageContent + `</em></p>`;
        break;
      case 'CHAT':
      default:
        messageElement = `<p><strong>` + message.sender +  ` : </strong>` + message.messageContent+ `</p>`;
        break;
    }

    $("#chat-messages").append(messageElement);
  }

  function leaveRoom() {

    if (stompClient) {
      // 퇴장 메시지 전송
      const exitMessage= {
        chatroomNo: roomNo,
        sender: usernameSession,
        messageContent: usernameSession + "님이 채팅방을 나갔습니다.",
        type: 'EXIT'
      };

      console.log("퇴장 메시지 확인 : ", exitMessage);

      stompClient.send("/app/exit/" + roomNo,
              {},
              JSON.stringify(exitMessage)
      );

      setTimeout(function () {
        // 구독 해제
        stompClient.unsubscribe('/topic/messages/' + roomNo);

        // 연결 종료
        stompClient.disconnect(function () {
          console.log("Chatroom Disconnected");
        });

        // 페이지 이동 (메인 페이지나 채팅방 목록으로)
        window.location.href = '/chat/room/list';
      }, 1000)
    }
  }


</script>
</body>
</html>