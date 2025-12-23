package com.framework;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ActorSystem {

    private final Map<String, Actor> localActors = new ConcurrentHashMap<>();
    
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    
    private final WebClient.Builder webClientBuilder;
    private static final int MAX_RETRIES = 3;

    public ActorSystem(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public void registerActor(Actor actor) {
        localActors.put(actor.getId(), actor);
        System.out.println("Acteur enregistré : " + actor.getId());
    }

    public void send(ActorMessage message) {
        if (localActors.containsKey(message.getTargetId())) {
            sendLocal(message);
        } else {
            sendRemote(message);
        }
    }

    private void sendLocal(ActorMessage message) {
        executorService.submit(() -> {
            Actor actor = localActors.get(message.getTargetId());
            if (actor == null) return;

            try {
                actor.onReceive(message);
                actor.resetFailureCount();
            } catch (Exception e) {
                handleActorFailure(actor, e, message);
            }
        });
    }

    private void sendRemote(ActorMessage message) {
        // Communication asynchrone non-bloquante via Eureka
        String url = "http://" + message.getTargetService() + "/api/actors/receive";
        
        webClientBuilder.build()
                .post()
                .uri(url)
                .bodyValue(message)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        success -> System.out.println("Message envoyé à " + message.getTargetService()),
                        error -> System.err.println("Erreur envoi vers " + message.getTargetService() + ": " + error.getMessage())
                );
    }

    private void handleActorFailure(Actor actor, Exception e, ActorMessage failedMessage) {
        if (actor.getFailureCount() < MAX_RETRIES) {
            actor.incrementFailureCount();
            actor.preRestart(e);
            // On pourrait ré-essayer le message ici
        } else {
            System.err.println("Acteur " + actor.getId() + " arrêté définitivement après trop d'échecs.");
            localActors.remove(actor.getId());
        }
    }
}