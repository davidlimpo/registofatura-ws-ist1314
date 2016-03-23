package registofatura.client;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import pt.registofatura.ws.ClienteInexistente_Exception;
import pt.registofatura.ws.EmissorInexistente_Exception;
import pt.registofatura.ws.Fatura;
import pt.registofatura.ws.FaturaInvalida_Exception;
import pt.registofatura.ws.ItemFatura;
import pt.registofatura.ws.RegistoFaturaPortType;
import pt.registofatura.ws.RegistoFaturaService;
import pt.registofatura.ws.Serie;
import pt.registofatura.ws.TotaisIncoerentes_Exception;


public class RegistoClient {
	private static final float txIVA = 0.23f;

	
    public static void main(String[] args) throws IOException {

    	RegistoFaturaService service = new RegistoFaturaService();
    	RegistoFaturaPortType port = service.getRegistoFaturaPort();
    	
    	BindingProvider bindingProvider = (BindingProvider) port;
        Map<String, Object> requestContext = bindingProvider.getRequestContext();
        
        // set endpoint address
        String url = args[0];
        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, url);
        System.out.printf("Remote call to %s ...%n", url);

        // call using set endpoint address
       
		try {
        	//Pedir serie - entidade com NIF 5111
			System.out.println("1- Entidade com NIF 5111 pede serie:");
			Serie s;
			s = port.pedirSerie(5111);
			System.out.println("Serie Num: " + s.getNumSerie() + " Válido até: " + 
								s.getValidoAte().getYear() + "-" + s.getValidoAte().getMonth() 
								+ "-" + s.getValidoAte().getDay() + " atribuida ao NIF: 5111");

		} catch (EmissorInexistente_Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
        	//Pedir serie - entidade com NIF 5222   
			System.out.println("2- Entidade com NIF 5222 pede serie:");
			Serie s;
			s = port.pedirSerie(5222);
			System.out.println("Serie Num: " + s.getNumSerie() + " Válido até: " + 
					s.getValidoAte().getYear() + "-" + s.getValidoAte().getMonth() 
					+ "-" + s.getValidoAte().getDay() + " atribuida ao NIF: 5222");
			
		} catch (EmissorInexistente_Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
        	//Pedir serie - entidade com NIF 9999 - INEXISTENTE 
			System.out.println("3- Entidade com NIF 9999 (inexistente) pede serie:");
			Serie s;
			s = port.pedirSerie(9999);
			System.out.println("Serie Num: " + s.getNumSerie() + " Válido até: " + 
					s.getValidoAte().getYear() + "-" + s.getValidoAte().getMonth() 
					+ "-" + s.getValidoAte().getDay() + " atribuida ao NIF: 9999");
			
		} catch (EmissorInexistente_Exception e1) {
			System.out.println(e1.getMessage());
		}
		
        	//Comunicar fatura correctamente
		try {
			System.out.println("4- Entidade com NIF 5111 comunica fatura correcta do cliente 1001.");
			GregorianCalendar gc = new GregorianCalendar();
			XMLGregorianCalendar data = null;
			data = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

			ItemFatura iFat = new ItemFatura();
			iFat.setDescricao("Frango a Patricio");
			iFat.setPreco(10);
			iFat.setQuantidade(1);

			Fatura f = new Fatura();
			f.setData(data);
			f.setIva((int)(iFat.getPreco()*txIVA/(1+txIVA)));
			f.setNifCliente(1001);
			f.setNifEmissor(5111);
			f.setNomeEmissor("Cardozo");
			f.setNumSeqFatura(1);
			f.setNumSerie(1);
            f.getItens().add(iFat);
			f.setTotal(10);
			
			Fatura g = new Fatura();
			g.setData(data);
			g.setIva((int)(iFat.getPreco()*txIVA/(1+txIVA)));
			g.setNifCliente(1002);
			g.setNifEmissor(5222);
			g.setNomeEmissor("Messi");
			g.setNumSeqFatura(1);
			g.setNumSerie(2);
            g.getItens().add(iFat);
			g.setTotal(10);
			
			port.comunicarFatura(f);
			port.comunicarFatura(g);
			
		} catch (DatatypeConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (ClienteInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (EmissorInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (FaturaInvalida_Exception e) {
			System.out.println(e.getMessage());
		} catch (TotaisIncoerentes_Exception e) {
			System.out.println(e.getMessage());
		}
    	//Comunicar a mesma fatura - Fatura Invalida
		try {
			System.out.println("5- Entidade com NIF 5111 comunica a mesma fatura do cliente 1001:");

			GregorianCalendar gc = new GregorianCalendar();
			XMLGregorianCalendar data = null;
			data = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	
			ItemFatura iFat = new ItemFatura();
			iFat.setDescricao("Frango a Patricio");
			iFat.setPreco(10);
			iFat.setQuantidade(1);
	
			Fatura f = new Fatura();
			f.setData(data);
			f.setIva((int)(iFat.getPreco()*txIVA/(1+txIVA)));
			f.setNifCliente(1001);
			f.setNifEmissor(5111);
			f.setNomeEmissor("Cardozo");
			f.setNumSeqFatura(1);
			f.setNumSerie(1);
	        f.getItens().add(iFat);
			f.setTotal(10);
			
			port.comunicarFatura(f);
			
			
		} catch (DatatypeConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (ClienteInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (EmissorInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (FaturaInvalida_Exception e) {
			System.out.println(e.getMessage());
		} catch (TotaisIncoerentes_Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Comunicar fatura com totais incoerentes
		try {
			System.out.println("6- Entidade com NIF 5111 comunica fatura do cliente 1001 com totais incoerentes:");

			GregorianCalendar gc = new GregorianCalendar();
			XMLGregorianCalendar data = null;
			data = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		
			ItemFatura iFat = new ItemFatura();
			iFat.setDescricao("Frango a Patricio");
			iFat.setPreco(20);
			iFat.setQuantidade(1);
		
			Fatura f = new Fatura();
			f.setData(data);
			f.setIva((int)(iFat.getPreco()*txIVA/(1+txIVA)));
			f.setNifCliente(1001);
			f.setNifEmissor(5111);
			f.setNomeEmissor("Cardozo");
			f.setNumSeqFatura(1);
			f.setNumSerie(1);
		    f.getItens().add(iFat);
			f.setTotal(10);
			
			port.comunicarFatura(f);
			
			
		} catch (DatatypeConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (ClienteInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (EmissorInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (FaturaInvalida_Exception e) {
			System.out.println(e.getMessage());
		} catch (TotaisIncoerentes_Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		//Comunicar fatura com cliente inexistente
		try {
			System.out.println("7- Entidade com NIF 5111 comunica fatura correctamente do cliente 999999 (inexistente):");
			GregorianCalendar gc = new GregorianCalendar();
			XMLGregorianCalendar data = null;
			data = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		
			ItemFatura iFat = new ItemFatura();
			iFat.setDescricao("Frango a Patricio");
			iFat.setPreco(20);
			iFat.setQuantidade(1);
		
			Fatura f = new Fatura();
			f.setData(data);
			f.setIva((int)(iFat.getPreco()*txIVA/(1+txIVA)));
			f.setNifCliente(999999);
			f.setNifEmissor(5111);
			f.setNomeEmissor("Cardozo");
			f.setNumSeqFatura(1);
			f.setNumSerie(1);
		    f.getItens().add(iFat);
			f.setTotal(10);
			
			port.comunicarFatura(f);
			
			
		} catch (DatatypeConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (ClienteInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (EmissorInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (FaturaInvalida_Exception e) {
			System.out.println(e.getMessage());
		} catch (TotaisIncoerentes_Exception e) {
			System.out.println(e.getMessage());
		}
		//Comunicar fatura com emissor inexistente
		try {
			System.out.println("8- Entidade com NIF 999999 (inexistente) comunica fatura correctamente do cliente 1001:");
			
			GregorianCalendar gc = new GregorianCalendar();
			XMLGregorianCalendar data = null;
			data = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		
			ItemFatura iFat = new ItemFatura();
			iFat.setDescricao("Frango a Patricio");
			iFat.setPreco(20);
			iFat.setQuantidade(1);
		
			Fatura f = new Fatura();
			f.setData(data);
			f.setIva((int)(iFat.getPreco()*txIVA/(1+txIVA)));
			f.setNifCliente(1001);
			f.setNifEmissor(999999);
			f.setNomeEmissor("Cardozo");
			f.setNumSeqFatura(1);
			f.setNumSerie(1);
		    f.getItens().add(iFat);
			f.setTotal(10);
			
			port.comunicarFatura(f);
			
			
		} catch (DatatypeConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (ClienteInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (EmissorInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (FaturaInvalida_Exception e) {
			System.out.println(e.getMessage());
		} catch (TotaisIncoerentes_Exception e) {
			System.out.println(e.getMessage());
		}	
		
		//testar listarFacturas caso normal
		try {
			System.out.println("9- Listar facturas do emissor NIF 5111 e cliente 1001:");
	
			List<Fatura> listFatura = port.listarFacturas(5111, 1001);
						
			for(int i = 0; i < listFatura.size() ; i++){
			System.out.println("Fatura " + i +  
					   " NumeroSerie: " + listFatura.get(0).getNumSerie() + " ; Data :" + 
					   listFatura.get(i).getData().getYear() + "-" + 
					   listFatura.get(i).getData().getMonth() + "-" +
					   listFatura.get(i).getData().getDay() + " ; Numero Sequencia: " +	
					   listFatura.get(i).getNumSeqFatura() + " ; Nif Emissor" +
					   listFatura.get(i).getNifEmissor() + " ; NifCliente " + 
					   listFatura.get(i).getNifCliente() + " ; Iva: " + 
					   listFatura.get(i).getIva());
			}
		
		
		} catch (ClienteInexistente_Exception e) {
			System.out.println(e.getMessage());
		} catch (EmissorInexistente_Exception e) {
			System.out.println(e.getMessage());
		}
			
		//testar listarFacturas - ClienteInexistente
		try {
			System.out.println("10- Listar facturas do emissor NIF 5111 e cliente 8888(inexistente):");
			List<Fatura> listFatura = new ArrayList<Fatura>();
			listFatura = port.listarFacturas(5111, 8888);
			
			
			for(int i = 0; i < listFatura.size() ; i++){
				System.out.println("Fatura " + i + 
						   " NumeroSerie: " + listFatura.get(0).getNumSerie() + " ; Data :" + 
						   listFatura.get(i).getData().getYear() + "-" + 
						   listFatura.get(i).getData().getMonth() + "-" +
						   listFatura.get(i).getData().getDay() + " ; Numero Sequencia: " +	
						   listFatura.get(i).getNumSeqFatura() + " ; Nif Emissor" +
						   listFatura.get(i).getNifEmissor() + " ; NifCliente " + 
						   listFatura.get(i).getNifCliente() + " ; Iva: " + 
						   listFatura.get(i).getIva());
			}			
								
			} catch (ClienteInexistente_Exception e) {
				System.out.println(e.getMessage());
			} catch (EmissorInexistente_Exception e) {
				System.out.println(e.getMessage());
			}
		
		
			//testar listarFacturas - EmissorInexistente
			try {
				System.out.println("11- Listar facturas do emissor NIF 88888 (inexistente) e cliente 1001:");

				List<Fatura> listFatura = new ArrayList<Fatura>();
				listFatura = port.listarFacturas(88888, 1001);
				
				
				for(int i = 0; i < listFatura.size() ; i++){
					System.out.println("Fatura " + i + 
							   " NumeroSerie: " + listFatura.get(0).getNumSerie() + " ; Data :" + 
							   listFatura.get(i).getData().getYear() + "-" + 
							   listFatura.get(i).getData().getMonth() + "-" +
							   listFatura.get(i).getData().getDay() + " ; Numero Sequencia: " +	
							   listFatura.get(i).getNumSeqFatura() + " ; Nif Emissor" +
							   listFatura.get(i).getNifEmissor() + " ; NifCliente " + 
							   listFatura.get(i).getNifCliente() + " ; Iva: " + 
							   listFatura.get(i).getIva());
				}
									
			} catch (ClienteInexistente_Exception e) {
				System.out.println(e.getMessage());
			} catch (EmissorInexistente_Exception e) {
				System.out.println(e.getMessage());
			}
			
			//testar listarFacturas o emissor nao e passado
			try {
				System.out.println("12- Listar facturas em que emissor não é passado (0) e cliente 1001:");
	
				List<Fatura> listFatura = new ArrayList<Fatura>();
				listFatura = port.listarFacturas(0 , 1001);
				
				for(int i = 0; i < listFatura.size() ; i++){
					System.out.println("Fatura " + i + 
							   " NumeroSerie: " + listFatura.get(0).getNumSerie() + " ; Data :" + 
							   listFatura.get(i).getData().getYear() + "-" + 
							   listFatura.get(i).getData().getMonth() + "-" +
							   listFatura.get(i).getData().getDay() + " ; Numero Sequencia: " +	
							   listFatura.get(i).getNumSeqFatura() + " ; Nif Emissor" +
							   listFatura.get(i).getNifEmissor() + " ; NifCliente " + 
							   listFatura.get(i).getNifCliente() + " ; Iva: " + 
							   listFatura.get(i).getIva());
				}
								
			} catch (ClienteInexistente_Exception e) {
				System.out.println(e.getMessage());
			} catch (EmissorInexistente_Exception e) {
				System.out.println(e.getMessage());
			}
			
			//testar listarFacturas o cliente nao e passado
			try {
				System.out.println("13- Listar facturas do emissor com o NIF 5111 em que o cliente não é passado(0):");
				
				List<Fatura> listFatura = new ArrayList<Fatura>();
				listFatura = port.listarFacturas(5111, 0);
				
				
				for(int i = 0; i < listFatura.size() ; i++){
					System.out.println("Fatura " + i +  
							   " NumeroSerie: " + listFatura.get(0).getNumSerie() + " ; Data :" + 
							   listFatura.get(i).getData().getYear() + "-" + 
							   listFatura.get(i).getData().getMonth() + "-" +
							   listFatura.get(i).getData().getDay() + " ; Numero Sequencia: " +	
							   listFatura.get(i).getNumSeqFatura() + " ; Nif Emissor" +
							   listFatura.get(i).getNifEmissor() + " ; NifCliente " + 
							   listFatura.get(i).getNifCliente() + " ; Iva: " + 
							   listFatura.get(i).getIva());
				}
								
			} catch (ClienteInexistente_Exception e) {
				System.out.println(e.getMessage());
			} catch (EmissorInexistente_Exception e) {
				System.out.println(e.getMessage());
			}
			
			//testar listarFacturas emissor e o cliente nao sao passados
			try {
				System.out.println("14- Listar facturas em que o emissor e o cliente não são passados(0):");
	
				List<Fatura> listFatura = new ArrayList<Fatura>();
				listFatura = port.listarFacturas(0 , 0);
				
				
				for(int i = 0; i < listFatura.size() ; i++){
					System.out.println("Fatura " + i + 
							   " NumeroSerie: " + listFatura.get(0).getNumSerie() + " ; Data :" + 
							   listFatura.get(i).getData().getYear() + "-" + 
							   listFatura.get(i).getData().getMonth() + "-" +
							   listFatura.get(i).getData().getDay() + " ; Numero Sequencia: " +	
							   listFatura.get(i).getNumSeqFatura() + " ; Nif Emissor" +
							   listFatura.get(i).getNifEmissor() + " ; NifCliente " + 
							   listFatura.get(i).getNifCliente() + " ; Iva: " + 
							   listFatura.get(i).getIva());
				}
								
			} catch (ClienteInexistente_Exception e) {
				System.out.println(e.getMessage());
			} catch (EmissorInexistente_Exception e) {
				System.out.println(e.getMessage());
			}
			
			//Consultar IVA devido correctamente
			try {
				System.out.println("15- Consultar IVA devido do emissor 5111 em 2014:");

				GregorianCalendar gc = new GregorianCalendar();
				gc.set(Calendar.YEAR, 2014);
				XMLGregorianCalendar ano;
				ano = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				
				int ivaDevido;
				
				ivaDevido = port.consultarIVADevido(5111, ano);
				System.out.println("IVA devido pelo emissor: 5111 no ano: " + ano.getYear()
									+ " -> " + ivaDevido); 
			} catch (EmissorInexistente_Exception e) {
				System.out.println(e.getMessage());
			} catch (DatatypeConfigurationException e) {
				System.out.println(e.getMessage());
			}
			
			//Consultar IVA devido com emissor inexistente
			try {
				System.out.println("16- Consultar IVA devido do emissor 77777 (inexistente) em 2014:");

				GregorianCalendar gc = new GregorianCalendar();
				gc.set(Calendar.YEAR, 2014);
				XMLGregorianCalendar ano;
				ano = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				
				int ivaDevido;
				
				ivaDevido = port.consultarIVADevido(77777, ano);
				System.out.println("IVA devido pelo emissor: 5111 no ano: " + ano.getYear()
									+ " -> " + ivaDevido); 
			} catch (EmissorInexistente_Exception e) {
				System.out.println(e.getMessage());
			} catch (DatatypeConfigurationException e) {
				System.out.println(e.getMessage());
			}
    }  
}
