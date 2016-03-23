
package pt.registofatura.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.registofatura.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClienteInexistente_QNAME = new QName("urn:pt:registofatura:ws", "ClienteInexistente");
    private final static QName _FaturaInvalida_QNAME = new QName("urn:pt:registofatura:ws", "FaturaInvalida");
    private final static QName _ListarFacturas_QNAME = new QName("urn:pt:registofatura:ws", "listarFacturas");
    private final static QName _ListarFacturasResponse_QNAME = new QName("urn:pt:registofatura:ws", "listarFacturasResponse");
    private final static QName _PedirSerieResponse_QNAME = new QName("urn:pt:registofatura:ws", "pedirSerieResponse");
    private final static QName _ConsultarIVADevidoResponse_QNAME = new QName("urn:pt:registofatura:ws", "consultarIVADevidoResponse");
    private final static QName _EmissorInexistente_QNAME = new QName("urn:pt:registofatura:ws", "EmissorInexistente");
    private final static QName _TotaisIncoerentes_QNAME = new QName("urn:pt:registofatura:ws", "TotaisIncoerentes");
    private final static QName _PedirSerie_QNAME = new QName("urn:pt:registofatura:ws", "pedirSerie");
    private final static QName _ConsultarIVADevido_QNAME = new QName("urn:pt:registofatura:ws", "consultarIVADevido");
    private final static QName _ComunicarFatura_QNAME = new QName("urn:pt:registofatura:ws", "comunicarFatura");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.registofatura.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fatura }
     * 
     */
    public Fatura createFatura() {
        return new Fatura();
    }

    /**
     * Create an instance of {@link ConsultarIVADevidoResponse }
     * 
     */
    public ConsultarIVADevidoResponse createConsultarIVADevidoResponse() {
        return new ConsultarIVADevidoResponse();
    }

    /**
     * Create an instance of {@link TotaisIncoerentes }
     * 
     */
    public TotaisIncoerentes createTotaisIncoerentes() {
        return new TotaisIncoerentes();
    }

    /**
     * Create an instance of {@link EmissorInexistente }
     * 
     */
    public EmissorInexistente createEmissorInexistente() {
        return new EmissorInexistente();
    }

    /**
     * Create an instance of {@link PedirSerie }
     * 
     */
    public PedirSerie createPedirSerie() {
        return new PedirSerie();
    }

    /**
     * Create an instance of {@link ConsultarIVADevido }
     * 
     */
    public ConsultarIVADevido createConsultarIVADevido() {
        return new ConsultarIVADevido();
    }

    /**
     * Create an instance of {@link PedirSerieResponse }
     * 
     */
    public PedirSerieResponse createPedirSerieResponse() {
        return new PedirSerieResponse();
    }

    /**
     * Create an instance of {@link ClienteInexistente }
     * 
     */
    public ClienteInexistente createClienteInexistente() {
        return new ClienteInexistente();
    }

    /**
     * Create an instance of {@link FaturaInvalida }
     * 
     */
    public FaturaInvalida createFaturaInvalida() {
        return new FaturaInvalida();
    }

    /**
     * Create an instance of {@link ListarFacturasResponse }
     * 
     */
    public ListarFacturasResponse createListarFacturasResponse() {
        return new ListarFacturasResponse();
    }

    /**
     * Create an instance of {@link ListarFacturas }
     * 
     */
    public ListarFacturas createListarFacturas() {
        return new ListarFacturas();
    }

    /**
     * Create an instance of {@link Serie }
     * 
     */
    public Serie createSerie() {
        return new Serie();
    }

    /**
     * Create an instance of {@link ItemFatura }
     * 
     */
    public ItemFatura createItemFatura() {
        return new ItemFatura();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClienteInexistente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "ClienteInexistente")
    public JAXBElement<ClienteInexistente> createClienteInexistente(ClienteInexistente value) {
        return new JAXBElement<ClienteInexistente>(_ClienteInexistente_QNAME, ClienteInexistente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaturaInvalida }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "FaturaInvalida")
    public JAXBElement<FaturaInvalida> createFaturaInvalida(FaturaInvalida value) {
        return new JAXBElement<FaturaInvalida>(_FaturaInvalida_QNAME, FaturaInvalida.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarFacturas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "listarFacturas")
    public JAXBElement<ListarFacturas> createListarFacturas(ListarFacturas value) {
        return new JAXBElement<ListarFacturas>(_ListarFacturas_QNAME, ListarFacturas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarFacturasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "listarFacturasResponse")
    public JAXBElement<ListarFacturasResponse> createListarFacturasResponse(ListarFacturasResponse value) {
        return new JAXBElement<ListarFacturasResponse>(_ListarFacturasResponse_QNAME, ListarFacturasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PedirSerieResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "pedirSerieResponse")
    public JAXBElement<PedirSerieResponse> createPedirSerieResponse(PedirSerieResponse value) {
        return new JAXBElement<PedirSerieResponse>(_PedirSerieResponse_QNAME, PedirSerieResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarIVADevidoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "consultarIVADevidoResponse")
    public JAXBElement<ConsultarIVADevidoResponse> createConsultarIVADevidoResponse(ConsultarIVADevidoResponse value) {
        return new JAXBElement<ConsultarIVADevidoResponse>(_ConsultarIVADevidoResponse_QNAME, ConsultarIVADevidoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmissorInexistente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "EmissorInexistente")
    public JAXBElement<EmissorInexistente> createEmissorInexistente(EmissorInexistente value) {
        return new JAXBElement<EmissorInexistente>(_EmissorInexistente_QNAME, EmissorInexistente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TotaisIncoerentes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "TotaisIncoerentes")
    public JAXBElement<TotaisIncoerentes> createTotaisIncoerentes(TotaisIncoerentes value) {
        return new JAXBElement<TotaisIncoerentes>(_TotaisIncoerentes_QNAME, TotaisIncoerentes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PedirSerie }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "pedirSerie")
    public JAXBElement<PedirSerie> createPedirSerie(PedirSerie value) {
        return new JAXBElement<PedirSerie>(_PedirSerie_QNAME, PedirSerie.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarIVADevido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "consultarIVADevido")
    public JAXBElement<ConsultarIVADevido> createConsultarIVADevido(ConsultarIVADevido value) {
        return new JAXBElement<ConsultarIVADevido>(_ConsultarIVADevido_QNAME, ConsultarIVADevido.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Fatura }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:pt:registofatura:ws", name = "comunicarFatura")
    public JAXBElement<Fatura> createComunicarFatura(Fatura value) {
        return new JAXBElement<Fatura>(_ComunicarFatura_QNAME, Fatura.class, null, value);
    }

}
