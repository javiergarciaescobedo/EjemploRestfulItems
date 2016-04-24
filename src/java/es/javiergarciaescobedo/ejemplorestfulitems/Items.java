package es.javiergarciaescobedo.ejemplorestfulitems;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Items {
 
    // A la etiqueta que identifique el inicio y fin de cada item en el XML, se le va
    //  a llamar 'item' en lugar de 'itemlist'
    @XmlElement(name = "item")
    private List<Item> itemsList;
 
    public Items() {
        itemsList = new ArrayList();
    }
 
    public List<Item> getItemsList() {
        return itemsList;
    }
 
    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }
 
}
