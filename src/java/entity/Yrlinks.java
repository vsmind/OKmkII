/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generated entity class for Yrlinks table
 * @author Vitaly
 */
@Entity
@Table(name = "Yrlinks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Yrlinks.findAll", query = "SELECT y FROM Yrlinks y"),
    @NamedQuery(name = "Yrlinks.findById", query = "SELECT y FROM Yrlinks y WHERE y.id = :id"),
    @NamedQuery(name = "Yrlinks.findByKommunenummer", query = "SELECT y FROM Yrlinks y WHERE y.kommunenummer = :kommunenummer"),
    @NamedQuery(name = "Yrlinks.findByStadnamn", query = "SELECT y FROM Yrlinks y WHERE y.stadnamn = :stadnamn"),
    @NamedQuery(name = "Yrlinks.findByPrioritet", query = "SELECT y FROM Yrlinks y WHERE y.prioritet = :prioritet"),
    @NamedQuery(name = "Yrlinks.findByStadtypenynorsk", query = "SELECT y FROM Yrlinks y WHERE y.stadtypenynorsk = :stadtypenynorsk"),
    @NamedQuery(name = "Yrlinks.findByStadtypebokmaal", query = "SELECT y FROM Yrlinks y WHERE y.stadtypebokmaal = :stadtypebokmaal"),
    @NamedQuery(name = "Yrlinks.findByStadtypeengelsk", query = "SELECT y FROM Yrlinks y WHERE y.stadtypeengelsk = :stadtypeengelsk"),
    @NamedQuery(name = "Yrlinks.findByKommune", query = "SELECT y FROM Yrlinks y WHERE y.kommune = :kommune"),
    @NamedQuery(name = "Yrlinks.findByFylke", query = "SELECT y FROM Yrlinks y WHERE y.fylke = :fylke"),
    @NamedQuery(name = "Yrlinks.findByLat", query = "SELECT y FROM Yrlinks y WHERE y.lat = :lat"),
    @NamedQuery(name = "Yrlinks.findByLon", query = "SELECT y FROM Yrlinks y WHERE y.lon = :lon"),
    @NamedQuery(name = "Yrlinks.findByHoegd", query = "SELECT y FROM Yrlinks y WHERE y.hoegd = :hoegd"),
    @NamedQuery(name = "Yrlinks.findByNynorsk", query = "SELECT y FROM Yrlinks y WHERE y.nynorsk = :nynorsk"),
    @NamedQuery(name = "Yrlinks.findByBokmaal", query = "SELECT y FROM Yrlinks y WHERE y.bokmaal = :bokmaal"),
    @NamedQuery(name = "Yrlinks.findByEngelsk", query = "SELECT y FROM Yrlinks y WHERE y.engelsk = :engelsk")})
public class Yrlinks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Kommunenummer")
    private Integer kommunenummer;
    @Size(max = 35)
    @Column(name = "Stadnamn")
    private String stadnamn;
    @Column(name = "Prioritet")
    private Integer prioritet;
    @Size(max = 34)
    @Column(name = "Stadtype_nynorsk")
    private String stadtypenynorsk;
    @Size(max = 34)
    @Column(name = "Stadtype_bokmaal")
    private String stadtypebokmaal;
    @Size(max = 47)
    @Column(name = "Stadtype_engelsk")
    private String stadtypeengelsk;
    @Size(max = 15)
    @Column(name = "Kommune")
    private String kommune;
    @Size(max = 16)
    @Column(name = "Fylke")
    private String fylke;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Lat")
    private Double lat;
    @Column(name = "Lon")
    private Double lon;
    @Size(max = 4)
    @Column(name = "Hoegd")
    private String hoegd;
    @Size(max = 103)
    @Column(name = "Nynorsk")
    private String nynorsk;
    @Size(max = 103)
    @Column(name = "Bokmaal")
    private String bokmaal;
    @Size(max = 107)
    @Column(name = "Engelsk")
    private String engelsk;

    public Yrlinks() {
    }

    public Yrlinks(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKommunenummer() {
        return kommunenummer;
    }

    public void setKommunenummer(Integer kommunenummer) {
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

    public void setPrioritet(Integer prioritet) {
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

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Yrlinks)) {
            return false;
        }
        Yrlinks other = (Yrlinks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Yrlinks[ id=" + id + " ]";
    }
    
}
