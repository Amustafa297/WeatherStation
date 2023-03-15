package edu.rit.croatia.swen383.g4.ws;

import edu.rit.croatia.swen383.g4.ws.factory.UIFactory;
import edu.rit.croatia.swen383.g4.ws.util.UIType;

public class WeatherStationRunner {

    public WeatherStationRunner() {
    }

    public static void main(String[] args) {

        WeatherStation ws = new WeatherStation();
        for (UIType type : UIType.values()) {
            UIFactory.create(type, ws);
        }
        Thread thread = new Thread(ws);
        thread.start();
    }
}