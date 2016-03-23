
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmissorInexistente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmissorInexistente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensagem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmissorInexistente", propOrder = {
    "mensagem",
    "nif"
})
public class EmissorInexistente {

    protected String mensagem;
    protected int nif;

    /**
     * Gets the value of the mensagem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Sets the value of the mensagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensagem(String value) {
        this.mensagem = value;
    }

    /**
     * Gets the value of the nif property.
     * 
     */
    public int getNif() {
        return nif;
    }

    /**
     * Sets the value of the nif property.
     * 
     */
    public void setNif(int value) {
        this.nif = value;
    }

}
