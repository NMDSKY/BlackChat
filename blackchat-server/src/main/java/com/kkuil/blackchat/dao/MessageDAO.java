package com.kkuil.blackchat.dao;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkuil.blackchat.domain.entity.Message;
import com.kkuil.blackchat.domain.vo.response.CursorPageBaseResp;
import com.kkuil.blackchat.mapper.MessageMapper;
import com.kkuil.blackchat.utils.CursorUtil;
import com.kkuil.blackchat.web.chat.domain.enums.MessageStatusEnum;
import com.kkuil.blackchat.web.chat.domain.vo.request.message.ChatMessageCursorReq;
import com.kkuil.blackchat.web.websocket.domain.vo.request.ChatMessageReq;
import org.springframework.stereotype.Service;

/**
 * @Author Kkuil
 * @Date 2023/9/28 18:28
 * @Description 消息访问层
 */
@Service
public class MessageDAO extends ServiceImpl<MessageMapper, Message> {

    /**
     * 通过用户ID和chatMessageReq保存消息
     *
     * @param uid            用户ID
     * @param chatMessageReq 消息体
     * @return 消息
     */
    public Message saveByUidAndChatMessageReq(Long uid, ChatMessageReq chatMessageReq) {
        Message message = Message.builder()
                .fromUid(uid)
                .roomId(chatMessageReq.getRoomId())
                .type(chatMessageReq.getMessageType())
                .replyMessageId(chatMessageReq.getReplyMessageId())
                .status(MessageStatusEnum.NORMAL.getStatus())
                .build();
        this.save(message);
        return message;
    }

    /**
     * 计算同一房间内两条消息的间隔数
     *
     * @param roomId 房间号
     * @param fromId 原消息
     * @param toId   定位消息
     */
    public void saveGapCount(Long roomId, Long fromId, Long toId) {
        Long gapCount = lambdaQuery()
                .eq(Message::getRoomId, roomId)
                .gt(Message::getId, fromId)
                .le(Message::getId, toId)
                .count();
        UpdateWrapper<Message> wrapper = new UpdateWrapper<>();
        wrapper
                .lambda()
                .eq(true, Message::getId, toId)
                .set(true, Message::getGapCount, gapCount);
        this.update(wrapper);
    }

    /**
     * 获取消息列表
     *
     * @param request 消息请求
     * @return 消息列表
     */
    public CursorPageBaseResp<Message> getCursorPage(ChatMessageCursorReq request) {
        return CursorUtil.getCursorPageByMysql(this, request, wrapper -> {
            wrapper.eq(Message::getStatus, MessageStatusEnum.NORMAL.getStatus());
            wrapper.eq(Message::getRoomId, request.getRoomId());
        }, Message::getCreateTime);
    }
}
