package edu.rit.croatia.swen383.g4.ws.ui;
/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

/*
 * Swing UI class used for displaying the information from the
 * associated weather station object.
 * This is an extension of JFrame, the outermost container in
 * a Swing application.
 */

import java.awt.Font;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.rit.croatia.swen383.g4.ws.WeatherStation;
import edu.rit.croatia.swen383.g4.ws.observer.Observer;
import edu.rit.croatia.swen383.g4.ws.util.MeasurmentUnit;



public class SwingUI extends JFrame implements Observer {
    // EnumMap that stores TemperatureUnit and JLabels as Keys and Values
    private final Map<MeasurmentUnit, JLabel> labelMap = new EnumMap<>(MeasurmentUnit.class);

    /*
     * A Font object contains information on the font to be used to
     * render text.
     */
    private final Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    /*
     * WeatherStation object that will be used in the update method to get 
     * the reading data.
     */
    private WeatherStation station = null;

    /*
     * Create and populate the SwingUI JFrame with panels and labels to
     * show the temperatures.
     */
    public SwingUI(WeatherStation station) {
        super("Weather Station");
        station.attach(this);
        this.station = station;
        /*
         * WeatherStation frame is a grid of 1 row by an indefinite
         * number of columns.
         */
        this.setLayout(new GridLayout(1, 0));

        /*
         * Set up Kelvin and Celsius display.
         */
        JPanel panel = null;
        for (MeasurmentUnit unit : MeasurmentUnit.values()) {
            panel = createPanel(unit);
            this.add(panel);
        }

        /*
         * Set up the frame's default close operation pack its elements,
         * and make the frame visible.
         */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Sets the labels values.
     * 
     * @param unit  label name reflecting temperature unit
     * @param value value to set
     */
    public void setJLabel(MeasurmentUnit unit, double value) {
        labelMap.get(unit).setText(String.format("%6.2f", value));
    }

     /**
      * Create a JLabel with the initial value <title> and returns
      * the reference to the JLabel.
      * @param title the title to be displayed
      * @return
      */
    private JLabel createJLabel(String title) {
        JLabel label = new JLabel(title);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(labelFont);

        return label;
    }

    /**
     * Creates a panel and adds labels for measurement units
     * and respective results of the measurements.
     * @param unit 
     * @return
     */
    private JPanel createPanel(MeasurmentUnit unit) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(this.createJLabel(unit.toString()));
        JLabel lb = this.createJLabel("");
        panel.add(lb);
        this.labelMap.put(unit, lb);

        return panel;
    }

    @Override
    public void update() {
        for (MeasurmentUnit unit : MeasurmentUnit.values()) {
            setJLabel(unit, station.getReading(unit));
        }
    }
}
