
package pt.registofatura.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for itemFatura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itemFatura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantidade" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="preco" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemFatura", propOrder = {
    "descricao",
    "quantidade",
    "preco"
})
public class ItemFatura {

    @XmlElement(required = true)
    protected String descricao;
    protected int quantidade;
    protected int preco;

    /**
     * Gets the value of the descricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the value of the descricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

    /**
     * Gets the value of the quantidade property.
     * 
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Sets the value of the quantidade property.
     * 
     */
    public void setQuantidade(int value) {
        this.quantidade = value;
    }

    /**
     * Gets the value of the preco property.
     * 
     */
    public int getPreco() {
        return preco;
    }

    /**
     * Sets the value of the preco property.
     * 
     */
    public void setPreco(int value) {
        this.preco = value;
    }

}
