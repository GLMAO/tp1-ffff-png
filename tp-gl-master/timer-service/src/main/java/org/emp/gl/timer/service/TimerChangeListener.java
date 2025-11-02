package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;

/**
 * Interface pour les listeners de changement de temps
 * Hérite maintenant de PropertyChangeListener pour utiliser PropertyChangeSupport
 * 
 * @author tina
 */
public interface TimerChangeListener extends PropertyChangeListener {
    
    final static String DIXEME_DE_SECONDE_PROP = "dixième";
    final static String SECONDE_PROP = "seconde";
    final static String MINUTE_PROP = "minute";
    final static String HEURE_PROP = "heure";
    
   