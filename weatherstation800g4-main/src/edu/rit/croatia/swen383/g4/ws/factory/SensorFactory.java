package edu.rit.croatia.swen383.g4.ws.factory;

import edu.rit.croatia.swen383.g4.ws.sensor.*;
import edu.rit.croatia.swen383.g4.ws.util.SensorType;
import edu.rit.croatia.swen383.g4.ws.adapter.*;

public class SensorFactory {
    
    public static Sensor create(SensorType type){
        return switch(type) {
            case TEMPERATURE -> new TemperatureSensor();
            case PRESSURE -> new PressureSensor();
            case HUMIDITY -> new HumiditySensorAdapter();
        };
    }
}
