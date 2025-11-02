package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * Compte à rebours qui décrémente un compteur à chaque seconde
 * Se désinscrit automatiquement quand il atteint 0
 * Utilise PropertyChangeListener pour éviter les ConcurrentModificationException
 */
public class CompteARebours implements TimerChangeListener {

    private String name;
    private TimerService timerService;
    private int compteur;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.compteur = valeurInitiale;
        this.timerService = timerService;
        
        // S'inscrire en tant qu'observer
        this.timerService.addTimeChangeListener(this);
        
        System.out.println("CompteARebours " + name + " initialisé à " + compteur);
    }

    /**
     * ✅ Méthode de PropertyChangeListener
     * C'est LA SEULE méthode à implémenter maintenant !
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Décrémenter uniquement quand les secondes changent
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            decrementer();
        }
    }

    private void decrementer() {
        if (compteur > 0) {
            compteur--;
            System.out.println(name + " : " + compteur);
            
            // ✅ Se désinscrire à 0 - PropertyChangeSupport gère ça sans bug !
            if (compteur == 0) {
                System.out.println(name + " : TERMINÉ ! (désinscription)");
                timerService.removeTimeChangeListener(this);
            }
        }
    }

    public int getCompteur() {
        return compteur;
    }
}