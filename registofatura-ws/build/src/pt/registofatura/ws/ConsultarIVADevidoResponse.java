
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consultarIVADevidoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultarIVADevidoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ivaDevido" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarIVADevidoResponse", propOrder = {
    "ivaDevido"
})
public class ConsultarIVADevidoResponse {

    protected int ivaDevido;

    /**
     * Gets the value of the ivaDevido property.
     * 
     */
    public int getIvaDevido() {
        return ivaDevido;
    }

    /**
     * Sets the value of the ivaDevido property.
     * 
     */
    public void setIvaDevido(int value) {
        this.ivaDevido = value;
    }

}
