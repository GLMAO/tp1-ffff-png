package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import java.util.Random;

/**
 * Classe principale pour tester le pattern Observer
 */
public class App {

    public static void main(String[] args) {
        // testQuestionC();
        testQuestionD();
    }

    /**
     * Test de la question C : Plusieurs horloges
     */
    private static void testQuestionC() {
        TimerService timerService = new DummyTimeServiceImpl();
        
        Horloge horloge1 = new Horloge("Horloge Paris", timerService);
        Horloge horloge2 = new Horloge("Horloge Londres", timerService);
        Horloge horloge3 = new Horloge("Horloge New York", timerService);
        
        System.out.println("\n=== Les horloges sont actives ===\n");
    }

    /**
     * Test de la question D : CompteARebours avec valeurs aléatoires
     * ATTENTION : Cette version peut causer des bugs (ConcurrentModificationException)
     */
    private static void testQuestionD() {
        TimerService timerService = new DummyTimeServiceImpl();
        
        // d.1 : Test avec un compte à rebours de 5
        System.out.println("=== Test avec un compte à rebours de 5 ===\n");
        CompteARebours compte1 = new CompteARebours("Compte-1", 5, timerService);
        
        // Attendre un peu avant de créer les autres
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // d.3 : Créer 10 comptes à rebours avec valeurs aléatoires entre 10 et 20
        System.out.println("\n=== Création de 10 comptes à rebours (10-20s) ===\n");
        Random random = new Random();
        
        for (int i = 1; i <= 10; i++) {
            int valeur = 10 + random.nextInt(11); // Entre 10 et 20
            CompteARebours compte = new CompteARebours("Compte-" + (i+1), valeur, timerService);
        }
        
        System.out.println("\n=== Tous les comptes à rebours sont lancés ===");
        System.out.println("⚠️  ATTENTION : Des bugs peuvent survenir (ConcurrentModificationException)");
        System.out.println("Raison : Modification de la liste des listeners pendant son itération\n");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}