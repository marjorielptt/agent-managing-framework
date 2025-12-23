package com.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorMessage {
    private String senderId;      // ID de l'acteur emmeteur
    private String targetId;      // ID de l'acteur cible
    private String targetService; // Nom du service cible (ex: service-hopital)
    private Object payload;       // Contenu du message
}