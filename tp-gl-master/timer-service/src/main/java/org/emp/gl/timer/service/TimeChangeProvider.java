package org.emp.gl.timer.service;

/**
 * Interface définissant le contrat pour un fournisseur de changements de temps
 * Permet d'ajouter et de retirer des listeners (Pattern Observer)
 * 
 * @author tina
 */
public interface TimeChangeProvider {
    
    /**
     * Ajoute un listener qui sera notifié à chaque changement de temps
     * @param listener le listener à ajouter
     */
    void addTimeChangeListener(TimerChangeListener listener);
    
    /**
     * Retire un listener de la liste des observateurs
     * @param listener le listener à retirer
     */
    void removeTimeChangeListener(TimerChangeListener listener);
    
}