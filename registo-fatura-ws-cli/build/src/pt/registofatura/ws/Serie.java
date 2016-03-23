
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for serie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numSerie" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="validoAte" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serie", propOrder = {
    "numSerie",
    "validoAte"
})
public class Serie {

    protected int numSerie;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar validoAte;

    /**
     * Gets the value of the numSerie property.
     * 
     */
    public int getNumSerie() {
        return numSerie;
    }

    /**
     * Sets the value of the numSerie property.
     * 
     */
    public void setNumSerie(int value) {
        this.numSerie = value;
    }

    /**
     * Gets the value of the validoAte property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValidoAte() {
        return validoAte;
    }

    /**
     * Sets the value of the validoAte property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValidoAte(XMLGregorianCalendar value) {
        this.validoAte = value;
    }

}
