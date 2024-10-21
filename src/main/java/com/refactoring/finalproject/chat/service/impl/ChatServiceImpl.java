package com.refactoring.finalproject.chat.service.impl;

import com.refactoring.finalproject.chat.dao.ChatDao;
import com.refactoring.finalproject.chat.dto.ChatRoomDto;
import com.refactoring.finalproject.chat.service.ChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final ChatDao chatDao;

    public ChatServiceImpl(ChatDao chatDao) {
        this.chatDao = chatDao;
    }


    @Override
    public List<ChatRoomDto> getChatRoomList() {

        List<ChatRoomDto> rooms = chatDao.selectChatRoomList();

        if(rooms == null) {
            logger.warn("rooms are null");
            return new ArrayList<>();
        }

        logger.info("Retrieved rooms: {}", rooms);

        return rooms;
    }

    @Override
    public void addChatroom(String chatroomName, String username) {



//        ChatRoomDto chatRoomDto = new ChatRoomDto();
//
//        chatRoomDto.setRoomId(chatroomName);
//        chatRoomDto.setUserId(username);
//
//        Long userNo = chatDao.selectUserNoByUsername();

//        chatDao.insertChatroom(chatRoomDto);
    }

    @Override
    public ChatRoomDto getChatRoomByRoomId(String roomNo) {
        return chatDao.selectChatRoom(roomNo);
    }
}
