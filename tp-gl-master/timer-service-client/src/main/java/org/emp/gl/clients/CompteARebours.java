package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

public class CompteARebours implements TimerChangeListener {

    private String name;
    private TimerService timerService;
    private int compteur;
    private static int idCounter = 0;

    public CompteARebours(TimerService timerService, int valeurInitiale) {
        this.timerService = timerService;
        this.compteur = valeurInitiale;
        this.name = "CompteARebours #" + (++idCounter);

      
        this.timerService.addTimeChangeListener(this);

        System.out.println(name + " démarre à " + compteur);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        propertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }

  
    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
       
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            decrementer();
        }
    }

    private void decrementer() {
        if (compteur > 0) {
            compteur--;
            System.out.println(name + ": " + compteur);

           
            if (compteur == 0) {
                System.out.println(name + " terminé! Désinscription...");
                timerService.removeTimeChangeListener(this);
            }
        }
    }

    public int getCompteur() {
        return compteur;
    }
}