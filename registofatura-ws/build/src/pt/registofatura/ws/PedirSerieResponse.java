
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pedirSerieResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pedirSerieResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serie" type="{urn:pt:registofatura:ws}serie"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pedirSerieResponse", propOrder = {
    "serie"
})
public class PedirSerieResponse {

    @XmlElement(required = true)
    protected Serie serie;

    /**
     * Gets the value of the serie property.
     * 
     * @return
     *     possible object is
     *     {@link Serie }
     *     
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * Sets the value of the serie property.
     * 
     * @param value
     *     allowed object is
     *     {@link Serie }
     *     
     */
    public void setSerie(Serie value) {
        this.serie = value;
    }

}
