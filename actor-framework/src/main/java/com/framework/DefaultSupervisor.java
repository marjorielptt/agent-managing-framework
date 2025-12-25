package com.framework;

public class DefaultSupervisor extends Supervisor {

    public DefaultSupervisor(String id) {
        super(id);
    }

    @Override
    public SupervisionDirective handleFailure(Actor failedActor, Throwable reason) {
        if (failedActor.getFailureCount() < 3) {
            return SupervisionDirective.RESTART;
        } else {
            return SupervisionDirective.STOP;
        }
    }

    @Override
    protected void handleMessage(ActorMessage message) throws Exception {
        // Implémentation par défaut : ignorer les messages
        System.out.println("• 📩 Superviseur " + getId() + " reçoit un message : " + message.getPayload());
    }
}