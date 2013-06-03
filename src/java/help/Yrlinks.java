/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Help class which is used to create the jSON object for Android client
 * class would be used along with the android client
 * @author Vitaly
 */
public class Yrlinks implements Serializable
{
    
    private int id;
    private int kommunenummer;
    private String stadnamn;
    private int prioritet;
    private String stadtypenynorsk;
    private String stadtypebokmaal;
    private String stadtypeengelsk;
    private String kommune;
    private String fylke;
    private double lat;
    private double lon;
    private String hoegd;
    private String nynorsk;
    private String bokmaal;
    private String engelsk;

    public Yrlinks() {
    }

    public Yrlinks(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getKommunenummer() {
        return kommunenummer;
    }

    public void setKommunenummer(int kommunenummer) {
        this.kommunenummer = kommunenummer;
    }

    public String getStadnamn() {
        return stadnamn;
    }

    public void setStadnamn(String stadnamn) {
        this.stadnamn = stadnamn;
    }

    public Integer getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }

    public String getStadtypenynorsk() {
        return stadtypenynorsk;
    }

    public void setStadtypenynorsk(String stadtypenynorsk) {
        this.stadtypenynorsk = stadtypenynorsk;
    }

    public String getStadtypebokmaal() {
        return stadtypebokmaal;
    }

    public void setStadtypebokmaal(String stadtypebokmaal) {
        this.stadtypebokmaal = stadtypebokmaal;
    }

    public String getStadtypeengelsk() {
        return stadtypeengelsk;
    }

    public void setStadtypeengelsk(String stadtypeengelsk) {
        this.stadtypeengelsk = stadtypeengelsk;
    }

    public String getKommune() {
        return kommune;
    }

    public void setKommune(String kommune) {
        this.kommune = kommune;
    }

    public String getFylke() {
        return fylke;
    }

    public void setFylke(String fylke) {
        this.fylke = fylke;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getHoegd() {
        return hoegd;
    }

    public void setHoegd(String hoegd) {
        this.hoegd = hoegd;
    }

    public String getNynorsk() {
        return nynorsk;
    }

    public void setNynorsk(String nynorsk) {
        this.nynorsk = nynorsk;
    }

    public String getBokmaal() {
        return bokmaal;
    }

    public void setBokmaal(String bokmaal) {
        this.bokmaal = bokmaal;
    }

    public String getEngelsk() {
        return engelsk;
    }

    public void setEngelsk(String engelsk) {
        this.engelsk = engelsk;
    }
    
}
