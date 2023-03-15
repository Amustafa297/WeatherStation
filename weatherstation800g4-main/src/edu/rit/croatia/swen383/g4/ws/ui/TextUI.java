package edu.rit.croatia.swen383.g4.ws.ui;


import edu.rit.croatia.swen383.g4.ws.WeatherStation;
import edu.rit.croatia.swen383.g4.ws.observer.Observer;
import edu.rit.croatia.swen383.g4.ws.util.*;

public class TextUI implements Observer {
    private WeatherStation station;

    public TextUI(WeatherStation station) {
        this.station = station;
        station.attach(this);
    }

    @Override
    public void update() {

        for (MeasurmentUnit u : MeasurmentUnit.values()) {
            System.out.printf("%6.2f %s\t", this.station.getReading(u), u);
        }
        System.out.println();

    }

}
