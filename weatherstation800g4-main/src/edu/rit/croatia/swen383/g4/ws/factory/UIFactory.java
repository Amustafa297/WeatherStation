package edu.rit.croatia.swen383.g4.ws.factory;

import edu.rit.croatia.swen383.g4.ws.WeatherStation;
import edu.rit.croatia.swen383.g4.ws.observer.Observer;
import edu.rit.croatia.swen383.g4.ws.ui.SwingUI;
import edu.rit.croatia.swen383.g4.ws.ui.TextUI;
import edu.rit.croatia.swen383.g4.ws.util.UIType;

public class UIFactory {

    public static Observer create(UIType type, WeatherStation station){
        switch (type) {
            case TEXT:
                return new TextUI(station);
            case UI:
                return new SwingUI(station);
            default:
                return null;
        }
    }
}
