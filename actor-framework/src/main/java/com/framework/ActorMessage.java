package com.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorMessage {
    private String senderId;
    private String targetId;
    private String targetService;
    private Object payload;
}
