/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicktest;

import modules.weather.YrDataParser;

/**
 *
 * @author Vitaly
 */
public class Test {

    public static void main(String[] args) {
        //Time t = new Time();
        //WorkingWithEvents w = new WorkingWithEvents();
        
        YrDataParser yr = new YrDataParser("http://www.yr.no/place/Norway/Oslo/Oslo/Oslo/forecast.xml");
        System.out.println(yr.getWeather("2013-06-02"));
    }
    
}




