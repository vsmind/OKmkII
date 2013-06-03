/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.io.Serializable;

/**
 * Help class which is used to create the jSON object for Android client
 * that used with yr.no module
 * @author Vitaly
 */
public class YrForecast implements Serializable
{
    
    private String time;
    private String icon;
    private String temp;

    public YrForecast() {
    }

    public YrForecast(String time, String icon, String temp) {
        this.time = time;
        this.icon = icon;
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    
}
