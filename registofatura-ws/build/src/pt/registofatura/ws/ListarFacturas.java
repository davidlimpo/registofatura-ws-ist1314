
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarFacturas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarFacturas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nifEmissor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nifCliente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarFacturas", propOrder = {
    "nifEmissor",
    "nifCliente"
})
public class ListarFacturas {

    protected Integer nifEmissor;
    protected Integer nifCliente;

    /**
     * Gets the value of the nifEmissor property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNifEmissor() {
        return nifEmissor;
    }

    /**
     * Sets the value of the nifEmissor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNifEmissor(Integer value) {
        this.nifEmissor = value;
    }

    /**
     * Gets the value of the nifCliente property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNifCliente() {
        return nifCliente;
    }

    /**
     * Sets the value of the nifCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNifCliente(Integer value) {
        this.nifCliente = value;
    }

}
