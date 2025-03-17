<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Chat Room</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

  <link rel="stylesheet" href="/resources/css/chat/chat-room.css"/>
</head>
<body>
<%
  String username = (String)session.getAttribute("user");
%>

<h1>${chatroom.chatroomName}</h1>
<button id="exit-chatroom">나가기</button>

<div id="chat-messages">
  <c:forEach items="${previousMessage}" var="msg">
    <c:choose>
      <%-- 본인이 쓴 메시지 --%>
      <c:when test="${msg.messageSender == sessionScope.user}">
        <div><pre class="my-msg">${msg.messageContent}</pre></div>
      </c:when>
      <%-- 남이 쓴 메시지 --%>
      <c:otherwise>
        <div><pre class="msg"><strong>${msg.messageSender} :</strong> ${msg.messageContent}</pre></div>
      </c:otherwise>

    </c:choose>
  </c:forEach>

</div>

<input type="text" id="message-input" placeholder="Enter messageDto">
<button id="message-send-btn">Send</button>
<div id="user-list"></div>
<script>
  $(document).ready(function() {
    connect();

    $('#message-send-btn').click(function () {
      sendMessage();
    })

    console.log("roooooomNo : ", roomNo);

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

      stompClient.subscribe('/topic/users/' + roomNo, function(userListMsg) {
        let users = JSON.parse(userListMsg.body);
        userUpdateList(users);
      });


      // 채팅방 입장 시
      stompClient.send("/app/enter/" + roomNo,
              {},
              JSON.stringify({
                sender: usernameSession,
              })
      );

      // 채팅방 나가기
      $('#exit-chatroom').click(function () {
        leaveRoom();
      });

      // 유저 리스트 요청
      stompClient.send("/app/user/" + roomNo, {}, {});

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
        // 내가
        if(message.sender === usernameSession) {
          messageElement = `<div><pre class="my-msg"></strong>` + message.messageContent+ `</strong></pre></div>`;
        }else {
          messageElement = `<div><pre class="msg"><strong>` + message.sender + ` : </strong>` + message.messageContent + `</pre></div>`;
        }
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

  //
  function userUpdateList(users) {
    const $userListDiv = $('#user-list');
    $userListDiv.empty();
    $.each(users, function (index, user) {
      const $userItem = $('<div></div>')
        .addClass('user-item')
              .text(user);
      $userListDiv.append($userItem);
    })
  }

</script>
</body>
</html>