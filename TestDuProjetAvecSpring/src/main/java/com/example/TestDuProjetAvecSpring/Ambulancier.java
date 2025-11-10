package com.example.TestDuProjetAvecSpring;

public class Ambulancier extends Acteur {
    @Override
    protected void onReceive(Message message) {
        System.out.println("Ambulancier se déplace pour: " + message.getContenu());
    }
}
