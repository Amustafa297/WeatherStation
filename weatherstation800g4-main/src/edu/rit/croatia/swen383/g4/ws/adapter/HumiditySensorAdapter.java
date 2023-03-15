package edu.rit.croatia.swen383.g4.ws.adapter;

import edu.rit.croatia.swen383.g4.ws.sensor.Sensor;
import edu.rit.croatia.swen383.g4.ws.util.MeasurmentUnit;
import edu.rit.marasovic.swen383.thirdparty.sensor.HumidityReader;

public class HumiditySensorAdapter implements Sensor {
    private final HumidityReader humidityReader;

    public HumiditySensorAdapter() {
        this.humidityReader = new HumidityReader();
    }

    @Override
    public int read() {
        double reading = MeasurmentUnit.PCT.get(humidityReader.get());
        return (int) reading;
    }

}
