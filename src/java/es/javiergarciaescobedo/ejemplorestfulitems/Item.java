package es.javiergarciaescobedo.ejemplorestfulitems;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
 
    @XmlElement
    private int id;
    @XmlElement
    private String astring;
    @XmlElement
    private int anumber;
    @XmlElement
    private Date adate;
 
    public Item() {
    }
 
    public Item(int id, String astring, int anumber, Date adate) {
        this.id = id;
        this.astring = astring;
        this.anumber = anumber;
        this.adate = adate;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getAstring() {
        return astring;
    }
 
    public void setAstring(String astring) {
        this.astring = astring;
    }
 
    public int getAnumber() {
        return anumber;
    }
 
    public void setAnumber(int anumber) {
        this.anumber = anumber;
    }
 
    public Date getAdate() {
        return adate;
    }
 
    public void setAdate(Date adate) {
        this.adate = adate;
    }
 
}
