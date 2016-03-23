
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for consultarIVADevido complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consultarIVADevido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nifEmissor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ano" type="{http://www.w3.org/2001/XMLSchema}gYear"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarIVADevido", propOrder = {
    "nifEmissor",
    "ano"
})
public class ConsultarIVADevido {

    protected int nifEmissor;
    @XmlElement(required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar ano;

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

    /**
     * Gets the value of the ano property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAno() {
        return ano;
    }

    /**
     * Sets the value of the ano property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAno(XMLGregorianCalendar value) {
        this.ano = value;
    }

}
