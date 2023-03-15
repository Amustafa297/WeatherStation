package edu.rit.croatia.swen383.g4.ws.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        observers.forEach(observer -> {
            observer.update();
        });
    }
}
