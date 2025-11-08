package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

public class Horloge implements TimerChangeListener {

    String name;
    TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;

        
        this.timerService.addTimeChangeListener(this);

        System.out.println("Horloge " + name + " initialized!");
    }

   
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        propertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }

    
    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
       
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            afficherHeure();
        }
    }

    public void afficherHeure() {
        if (timerService != null) {
            System.out.printf("%s affiche %02d:%02d:%02d%n",
                    name,
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes());
        }
    }
}