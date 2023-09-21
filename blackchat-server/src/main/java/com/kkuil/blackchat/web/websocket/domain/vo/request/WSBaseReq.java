package com.kkuil.blackchat.web.websocket.domain.vo.request;

import com.kkuil.blackchat.web.websocket.domain.enums.WsRequestTypeEnum;
import lombok.Data;

/**
 * @Author Kkuil
 * @Date 2023/09/17 17:00
 * @Description 
 */
@Data
public class WSBaseReq {
    /**
     * @see WsRequestTypeEnum
     */
    private Integer type;
    private String data;
}
