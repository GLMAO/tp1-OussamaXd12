package org.emp.gl.core.launcher;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGraphique;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

import javax.swing.*;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        testDuTimeService();
        testCompteARebours();
        testMultiplesComptesARebours();
        SwingUtilities.invokeLater(() -> {
            testHorlogeGraphique();
        });

    }

    private static void testDuTimeService() {
      
        TimerService timerService = new DummyTimeServiceImpl();

  
        Horloge horloge1 = new Horloge("Num 1", timerService);
        Horloge horloge2 = new Horloge("Num 2", timerService);
        Horloge horloge3 = new Horloge("Num 3", timerService);

        System.out.println("\n Les horloges  démarrées ");

    }

    private static void testCompteARebours() {
    
        TimerService timerService = new DummyTimeServiceImpl();

        
        CompteARebours compte1 = new CompteARebours(timerService, 5);

        System.out.println("\n=== Compte à rebours démarré ===\n");
    }

    private static void testMultiplesComptesARebours() {
       
        TimerService timerService = new DummyTimeServiceImpl();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int valeur = 10 + random.nextInt(11); 
            new CompteARebours(timerService, valeur);
        }

        System.out.println("\n comptes à rebours démarrés");
    }

    private static void testHorlogeGraphique() {
       
        TimerService timerService = new DummyTimeServiceImpl();

     
        new HorlogeGraphique(timerService);

        System.out.println("Interface graphique lancée !");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }





}