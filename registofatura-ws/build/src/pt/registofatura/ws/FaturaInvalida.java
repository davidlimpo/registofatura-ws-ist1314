
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FaturaInvalida complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FaturaInvalida">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensagem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numSeqFatura" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numSerie" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaturaInvalida", propOrder = {
    "mensagem",
    "numSeqFatura",
    "numSerie"
})
public class FaturaInvalida {

    protected String mensagem;
    protected int numSeqFatura;
    protected int numSerie;

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
     * Gets the value of the numSeqFatura property.
     * 
     */
    public int getNumSeqFatura() {
        return numSeqFatura;
    }

    /**
     * Sets the value of the numSeqFatura property.
     * 
     */
    public void setNumSeqFatura(int value) {
        this.numSeqFatura = value;
    }

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

}
