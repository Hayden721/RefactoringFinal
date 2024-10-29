package com.refactoring.finalproject.chat.controller;

import com.refactoring.finalproject.chat.dto.MessageRequest;
import com.refactoring.finalproject.chat.dto.ChatRoomDto;
import com.refactoring.finalproject.chat.service.ChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    // 채팅리스트
    @GetMapping("/room/list")
    public String roomListGet(Model model){
        logger.info("/chat/room/list : get");

        try {
            List<ChatRoomDto> roomList = chatService.getChatRoomList();
            model.addAttribute("roomList", roomList);
        } catch (Exception e) {
            logger.error("Error retrieving room list: ", e);
        }
        return "/chat/room-list";
    }

    // 채팅방 입장
    @GetMapping("/room/{roomNo}")
    public String roomGet(@PathVariable("roomNo") Long roomNo, Model model){
        logger.info("roomNOooo : {}", roomNo);
        // 접속하려는 채팅방의 정보 가지고 오지
        ChatRoomDto chatroom = chatService.getChatRoomByRoomId(roomNo);
        logger.info("chatroom Info : {}", chatroom);

        // 기존 채팅방에 있던 사람이라면 이전 채팅내역 가지고 오기 / 신규는 그냥 새로운 채팅창 if

        List<MessageRequest> previousMessage = chatService.getPreviousMessage(roomNo);
        logger.info("previousMessage : {}", previousMessage);

        model.addAttribute("previousMessage", previousMessage);
        model.addAttribute("chatroom", chatroom);

        return "/chat/chat-room";
    }

//    // 채팅방 생성
    @PostMapping("/room/create")
    public String roomAddPost(@RequestParam("chatroomName") String chatroomName, @RequestParam("username") String username){
        logger.info("chatroomName : {}", chatroomName);
        logger.info("username : {}", username);
        chatService.createChatroom(chatroomName, username);

        return "redirect:/chat/room/list";
    }








}
