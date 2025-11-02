package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * Horloge qui affiche l'heure à chaque seconde
 * Implémente PropertyChangeListener via TimerChangeListener
 */
public class Horloge implements TimerChangeListener {

    private String name;
    private TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        
        // S'inscrire en tant qu'observer
        this.timerService.addTimeChangeListener(this);
        
        System.out.println("Horloge " + name + " initialized!");
    }

    /**
     * ✅ Méthode de PropertyChangeListener
     * C'est LA SEULE méthode à implémenter maintenant !
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Afficher l'heure seulement quand les secondes changent
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }

    public void afficherHeure() {
        if (timerService != null) {
            System.out.println(name + " affiche " +
                    String.format("%02d:%02d:%02d",
                            timerService.getHeures(),
                            timerService.getMinutes(),
                            timerService.getSecondes()));
        }
    }
}