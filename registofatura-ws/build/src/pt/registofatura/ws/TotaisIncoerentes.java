
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TotaisIncoerentes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TotaisIncoerentes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="razao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TotaisIncoerentes", propOrder = {
    "razao"
})
public class TotaisIncoerentes {

    protected String razao;

    /**
     * Gets the value of the razao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazao() {
        return razao;
    }

    /**
     * Sets the value of the razao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazao(String value) {
        this.razao = value;
    }

}
