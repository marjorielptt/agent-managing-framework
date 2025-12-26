
package com.framework;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestionLog {

    private static final String FICHIER_LOG = "historique_simulation.txt";
    // Format de la date/heure
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Le mot synchronized empeche deux acteurs d'écrire en meme temps et de mélanger leurs lignes
    public static synchronized void ecrire(String acteur, String message) {
        // Date actuelle
        String dateHeure = LocalDateTime.now().format(FORMATTER);
        // On construit la ligne : [DATE] [ACTEUR] : Message
        String ligne = String.format("[%s] [%s] : %s", dateHeure, acteur.toUpperCase(), message);

        // Bloc try-with-resources pour fermer le fichier automatiquement après écriture
        try (FileWriter fileWriter = new FileWriter(FICHIER_LOG, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            // Ecriture dans le fichier
            printWriter.println(ligne);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du log : " + e.getMessage());
        }
    }

    public static void sautDeLigne() {
        try (java.io.FileWriter fw = new java.io.FileWriter(FICHIER_LOG, true);
             java.io.PrintWriter pw = new java.io.PrintWriter(fw)) {
            pw.println(); // Saut de ligne
        } catch (java.io.IOException e) {
            // On ignore l'erreur
        }
    }

    public static void sautDeSection() {
        try (java.io.FileWriter fw = new java.io.FileWriter(FICHIER_LOG, true);
             java.io.PrintWriter pw = new java.io.PrintWriter(fw)) {

            pw.println();
            pw.println();
            pw.println("---------------------------------------------------------------");
            pw.println();

        } catch (java.io.IOException e) {
            // On ignore l'erreur
        }
    }
}
