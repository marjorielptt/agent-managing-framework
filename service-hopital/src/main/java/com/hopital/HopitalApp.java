package com.hopital;

import com.framework.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hopital", "com.framework"})
public class HopitalApp {
    public static void main(String[] args) {
        SpringApplication.run(HopitalApp.class, args);
    }

    @Bean
    public CommandLineRunner init(ActorSystem system) {
        return args -> {
            Thread.sleep(500);

            System.out.println("\n⏳ INITIALISATION DU SERVICE HOSPITALIER EN COURS...");

            // Créer un superviseur pour les acteurs de l'hôpital
            Supervisor hopitalSupervisor = new DefaultSupervisor("superviseur-hopital");
            system.registerActor(hopitalSupervisor);
            Thread.sleep(200);

            // Créer les acteurs et les assigner au superviseur
            MedecinActor medecin = new MedecinActor("medecin-urgences", system);
            medecin.setSupervisor(hopitalSupervisor);
            system.registerActor(medecin);
            Thread.sleep(200);

            AmbulancierActor ambulancier = new AmbulancierActor("ambulancier-1", system);
            ambulancier.setSupervisor(hopitalSupervisor);
            system.registerActor(ambulancier);

            // Envoyer un message de DEBUG à l'ambulancier
            // ActorMessage testMessage = new ActorMessage("system", "ambulancier-1", null, "Urgence à l'hôpital central");
            // system.send(testMessage);

            // Simuler un crash après un délai
            Thread.sleep(1000);
            ActorMessage crashMessage = new ActorMessage("system", "ambulancier-1", null, "CRASH");
            system.send(crashMessage);

        };
    }
}
