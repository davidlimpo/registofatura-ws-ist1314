
package pt.registofatura.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for fatura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fatura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="numSeqFatura" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numSerie" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nifEmissor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nomeEmissor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nifCliente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="itens" type="{urn:pt:registofatura:ws}itemFatura" maxOccurs="unbounded"/>
 *         &lt;element name="iva" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fatura", propOrder = {
    "data",
    "numSeqFatura",
    "numSerie",
    "nifEmissor",
    "nomeEmissor",
    "nifCliente",
    "itens",
    "iva",
    "total"
})
public class Fatura {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar data;
    protected int numSeqFatura;
    protected int numSerie;
    protected int nifEmissor;
    @XmlElement(required = true)
    protected String nomeEmissor;
    protected Integer nifCliente;
    @XmlElement(required = true)
    protected List<ItemFatura> itens;
    protected int iva;
    protected int total;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
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
     * Gets the value of the nomeEmissor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeEmissor() {
        return nomeEmissor;
    }

    /**
     * Sets the value of the nomeEmissor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeEmissor(String value) {
        this.nomeEmissor = value;
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

    /**
     * Gets the value of the itens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemFatura }
     * 
     * 
     */
    public List<ItemFatura> getItens() {
        if (itens == null) {
            itens = new ArrayList<ItemFatura>();
        }
        return this.itens;
    }

    /**
     * Gets the value of the iva property.
     * 
     */
    public int getIva() {
        return iva;
    }

    /**
     * Sets the value of the iva property.
     * 
     */
    public void setIva(int value) {
        this.iva = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

}
