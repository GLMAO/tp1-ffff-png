package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Implémentation du TimerService utilisant PropertyChangeSupport
 * pour gérer les listeners de manière thread-safe
 * 
 * @author tina
 */
public class DummyTimeServiceImpl implements TimerService {

    private int dixiemeDeSeconde;
    private int minutes;
    private int secondes;
    private int heures;
    
    // ✅ Utilisation de PropertyChangeSupport au lieu d'une List
    private final PropertyChangeSupport support;

    /**
     * Constructeur du DummyTimeServiceImpl
     * Utilise un Timer pour générer des tics à chaque 100ms
     */
    public DummyTimeServiceImpl() {
        // ✅ Initialiser PropertyChangeSupport
        this.support = new PropertyChangeSupport(this);
        
        setTimeValues();
        
        // Initialiser le timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener listener) {
        // ✅ Utiliser PropertyChangeSupport
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener listener) {
        // ✅ Utiliser PropertyChangeSupport (thread-safe)
        support.removePropertyChangeListener(listener);
    }

    private void timeChanged() {
        setTimeValues();
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;

        // ✅ Notifier via PropertyChangeSupport
        support.firePropertyChange(
            TimerChangeListener.DIXEME_DE_SECONDE_PROP,
            oldValue,
            dixiemeDeSeconde
        );
    }

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;

        // ✅ Notifier via PropertyChangeSupport
        support.firePropertyChange(
            TimerChangeListener.SECONDE_PROP,
            oldValue,
            secondes
        );
    }

    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;

        // ✅ Notifier via PropertyChangeSupport
        support.firePropertyChange(
            TimerChangeListener.MINUTE_PROP,
            oldValue,
            minutes
        );
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;

        // ✅ Notifier via PropertyChangeSupport
        support.firePropertyChange(
            TimerChangeListener.HEURE_PROP,
            oldValue,
            heures
        );
    }

    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}