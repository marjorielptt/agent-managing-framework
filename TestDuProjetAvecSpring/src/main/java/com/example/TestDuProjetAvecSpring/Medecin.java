package com.example.TestDuProjetAvecSpring;

public class Medecin extends Acteur {
    @Override
    protected void onReceive(Message message) {
        System.out.println("Médecin traite: " + message.getContenu());
    }
}