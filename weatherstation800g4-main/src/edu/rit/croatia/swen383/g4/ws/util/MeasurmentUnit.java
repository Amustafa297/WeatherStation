package edu.rit.croatia.swen383.g4.ws.util;

import java.util.List;
import java.util.ArrayList;

public enum MeasurmentUnit {
    KELVIN(SensorType.TEMPERATURE, 1,0),
    CELSIUS(SensorType.TEMPERATURE,1, -27315),
    FAHRENHEIT(SensorType.TEMPERATURE,1.8,  -45967),
    INHG(SensorType.PRESSURE, 1,0),
    MBAR(SensorType.PRESSURE, 33.8639,0),
    PCT(SensorType.HUMIDITY, 100, 0);

    private final double cf1;
    private final double cf2;
    private final SensorType type;

    MeasurmentUnit(SensorType type, double cf1, double cf2) {
        this.cf1 = cf1;
        this.cf2 = cf2;
        this.type = type;
    }

    public double get(int measurment) {
        return (measurment * this.cf1 + this.cf2) / 100.0;
    }

    public static List<MeasurmentUnit> valuesOf(SensorType type) {
        List<MeasurmentUnit> unitsOfSameType = new ArrayList<>();
        for(MeasurmentUnit unit : MeasurmentUnit.values()) {
            if(unit.type == type) unitsOfSameType.add(unit);
        }
        return unitsOfSameType;
    }
}
