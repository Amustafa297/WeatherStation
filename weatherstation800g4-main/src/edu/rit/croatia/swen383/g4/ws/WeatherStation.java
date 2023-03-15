package edu.rit.croatia.swen383.g4.ws;

import java.util.EnumMap;
import java.util.Map;

import edu.rit.croatia.swen383.g4.ws.factory.SensorFactory;
import edu.rit.croatia.swen383.g4.ws.observer.Subject;
import edu.rit.croatia.swen383.g4.ws.sensor.*;
import edu.rit.croatia.swen383.g4.ws.util.*;

/**
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a sensor
 * that reports the temperature as a 16-bit number (0 to 65535) representing the
 * Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 *
 * @author Michael J. Lutz
 * @author Kristina Marasovic
 * @version 1
 */
public class WeatherStation extends Subject implements Runnable{

    private final Map<SensorType, Sensor> sensorMap = new EnumMap<>(SensorType.class); // Temperature sensor.
    private Map<MeasurmentUnit, Double> readingMap = new EnumMap<>(MeasurmentUnit.class);
    private static final long PERIOD = 1000;      // 1 sec = 1000 ms.
    /*
     * When a WeatherStation object is created, it in turn creates the sensor
     * object it will use.
     */
    public WeatherStation(){
        for (SensorType type : SensorType.values()) {
            sensorMap.put(type, SensorFactory.create(type));
        }
    }

    private void getSensorReadings(){
        Sensor sensor = null;
        for (SensorType type  : SensorType.values()) {
            sensor = sensorMap.get(type);
            int reading = sensor.read();
            for (MeasurmentUnit unit : MeasurmentUnit.valuesOf(type)) {
                readingMap.put(unit, unit.get(reading));
            }
        }
    }

    public double getReading(MeasurmentUnit mu){

        return readingMap.get(mu);
    }

    /*
     * The "run" method called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from
     * its sensor, and reports this as a formatted output string.
     */
    public void run() {

        while (true) {
            getSensorReadings();

            notifyObservers();
            
            try {Thread.sleep(PERIOD);} catch (Exception e) {}    // ignore exceptions
        }
    }
}
