
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pedirSerie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pedirSerie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nifEmissor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pedirSerie", propOrder = {
    "nifEmissor"
})
public class PedirSerie {

    protected int nifEmissor;

    /**
     * Gets the value of the nifEmissor property.
     * 
     */
    public int getNifEmissor() {
        return nifEmissor;
    }

    /**
     * Sets the value of the nifEmissor property.
     * 
     */
    public void setNifEmissor(int value) {
        this.nifEmissor = value;
    }

}
