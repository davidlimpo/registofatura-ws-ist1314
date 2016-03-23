package registofatura.ws;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.registry.JAXRException;
import javax.xml.ws.BindingProvider;

import pt.registofatura.ws.ClienteInexistente;
import pt.registofatura.ws.ClienteInexistente_Exception;
import pt.registofatura.ws.EmissorInexistente;
import pt.registofatura.ws.EmissorInexistente_Exception;
import pt.registofatura.ws.Fatura;
import pt.registofatura.ws.FaturaInvalida;
import pt.registofatura.ws.FaturaInvalida_Exception;
import pt.registofatura.ws.ItemFatura;
import pt.registofatura.ws.RegistoFaturaPortType;
import pt.registofatura.ws.RegistoFaturaService;
import pt.registofatura.ws.Serie;
import pt.registofatura.ws.TotaisIncoerentes;
import pt.registofatura.ws.TotaisIncoerentes_Exception;
import registofatura.uddi.UDDINaming;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

@WebService(
	    endpointInterface="pt.registofatura.ws.RegistoFaturaPortType",
	    wsdlLocation="RegistoFatura.1_0.wsdl",
	    name="RegistoFaturaPortType",
	    portName="RegistoFaturaPort",
	    targetNamespace="urn:pt:registofatura:ws",
	    serviceName="RegistoFaturaService"
)

public class RegistoFaturaImpl implements RegistoFaturaPortType{
	private static final float txIVA = 0.23f;
	private ArrayList<RegistoFaturaPortType> listPorts;
		
	@Override
	public Serie pedirSerie(int nifEmissor) throws EmissorInexistente_Exception {
		
		Serie s = null;
		Statement stmt = null;
		PreparedStatement stmt2 = null;
	    String query = "SELECT MAX(SERIE_NUM) AS SERIE_NUM FROM SERIE;";
	    String query2 = "SELECT COUNT(*) AS CONTADOR FROM ENTIDADE WHERE NIF = " + nifEmissor;
	    String insert = "INSERT INTO SERIE (SERIE_NUM, VALID, FATURAS_NUM) VALUES(?,?,?)";
	    
	    int numero = 0;
	    
		try {
			RegistoFaturaMain.createConnection();
			Connection conn = RegistoFaturaMain.conn;
	        stmt = (Statement) conn.createStatement();
	        ResultSet rs2 = stmt.executeQuery(query2);
	        rs2.next();
	        
	        //Testar se o restaurante existe
	        if(rs2.getInt("CONTADOR") == 0){
	        	EmissorInexistente faultInfo = new EmissorInexistente();
	        	faultInfo.setMensagem("O emissor " + nifEmissor + " não existe.");
	        	faultInfo.setNif(nifEmissor);
	        	throw new EmissorInexistente_Exception(faultInfo.getMensagem(), faultInfo);
	        }
	                
	        ResultSet rs = stmt.executeQuery(query);
	        rs.next();
	        numero  = rs.getInt("SERIE_NUM");
	        
	        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	        
	        //se passar de um mes para o outro da bonus!!!!!!!!!!!!!!!!!!!!!!!!!!
	        gc.add(Calendar.MONTH, 1);
	        gc.add(Calendar.DAY_OF_MONTH, 10);
	        
	        XMLGregorianCalendar xc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	        //actualizar numero de serie
	        numero++;
	        
	        //construir serie
	        s = new Serie();
	        s.setNumSerie(numero);
	        s.setValidoAte(xc);
	        
	        
	        //actualizar bd
	        stmt2 = (PreparedStatement) conn.prepareStatement(insert);
	        stmt2.setString(1, numero + "");
	        stmt2.setString(2, 	gc.get(Calendar.YEAR) + "-" + 
	        					gc.get(Calendar.MONTH) + "-" +
	        					gc.get(Calendar.DAY_OF_MONTH) + " " +
	        					gc.get(Calendar.HOUR_OF_DAY) + ":" +
	        					gc.get(Calendar.MINUTE) + ":" +
	        					gc.get(Calendar.SECOND));
	        stmt2.setString(3, "4");
	        
	        stmt2.executeUpdate();
	        conn.close();
	        
	        listPorts = searchBackup();
	        
	        if(listPorts != null)
	        	for(RegistoFaturaPortType port: listPorts)
	        		port.pedirSerie(nifEmissor);
	       
	    } catch (SQLException e1) {
	    	System.out.println("Erro SQL " + e1.getMessage());
	    } catch (DatatypeConfigurationException e2) {
	    	System.out.println("Erro Gregorian " + e2.getMessage());
	    } catch (JAXRException e3) {
			e3.printStackTrace();
		}
		
		return s;
	}

	@Override
	public void comunicarFatura(Fatura parameters)
			throws ClienteInexistente_Exception, EmissorInexistente_Exception,
			FaturaInvalida_Exception, TotaisIncoerentes_Exception {
		
		Statement stmt = null;
		PreparedStatement stmt2 = null;
		
		String query1 = "SELECT COUNT(*) AS CONTADOR FROM ENTIDADE WHERE NIF = " + parameters.getNifEmissor();
		String query2 = "SELECT COUNT(*) AS CLIENTE_NUM FROM ENTIDADE WHERE NIF = " + parameters.getNifCliente();
		String query3 = "SELECT COUNT(*) AS CONTADOR FROM FATURA WHERE SERIE_NUM = " + parameters.getNumSerie() + " AND SEQ_NUM = " + parameters.getNumSeqFatura();
		String query4 = "SELECT COUNT(*) AS CONTADOR FROM SERIE WHERE SERIE_NUM = " + parameters.getNumSerie();
		String query5 = "SELECT VALID FROM SERIE WHERE SERIE_NUM = " + parameters.getNumSerie();
		String insert = "INSERT INTO FATURA (SERIE_NUM, DATA_EMISSAO, SEQ_NUM, NIF_EMISSOR, NIF_CLIENTE, IVA_FATURA, ANO, TOTAL) VALUES(?,?,?,?,?,?,?,?)";
		String query6 = "SELECT FATURAS_NUM FROM SERIE WHERE SERIE_NUM = " + parameters.getNumSerie();
		String insert2 = "UPDATE SERIE SET FATURAS_NUM =? WHERE SERIE_NUM = ?";
		
		try {
			RegistoFaturaMain.createConnection();
			Connection conn = RegistoFaturaMain.conn;
			stmt = (Statement) conn.createStatement();

			//Testar se o restaurante existe
	        ResultSet rs1 = stmt.executeQuery(query1);
	        rs1.next();
	        
	        if(rs1.getInt("CONTADOR") == 0){
	        	EmissorInexistente faultInfo = new EmissorInexistente();
	        	faultInfo.setMensagem("O emissor restaurante " + parameters.getNifEmissor() + " não existe.");
	        	faultInfo.setNif(parameters.getNifEmissor());
	        	throw new EmissorInexistente_Exception(faultInfo.getMensagem(), faultInfo);
	        }
	        
        
	      //Testar se o cliente existe
	        ResultSet rs2 = stmt.executeQuery(query2);
	        rs2.next();
	        
	        if(rs2.getInt("CLIENTE_NUM") == 0){
	        	ClienteInexistente faultInfo = new ClienteInexistente();
	        	faultInfo.setMensagem("O emissor cliente " + parameters.getNifCliente() + " não existe.");
	        	faultInfo.setNif(parameters.getNifCliente());
	        	throw new ClienteInexistente_Exception(faultInfo.getMensagem(), faultInfo);
	        }
	        
		   //Testar total
	       int soma = 0;
	       
	       for(ItemFatura i : parameters.getItens())
	    	   soma += i.getPreco();
	       
	       if(soma != parameters.getTotal()){
	    	   TotaisIncoerentes faultInfo = new TotaisIncoerentes();
	    	   faultInfo.setRazao("Totais incoerentes. Total real: " + soma +  " Total declarado: " + parameters.getTotal());
	    	   throw new TotaisIncoerentes_Exception(faultInfo.getRazao(), faultInfo);
	       }
	       
	       //Testar IVA
	       int totalIVA = 0;
	       
	       for(ItemFatura i : parameters.getItens())
	    	   totalIVA += i.getPreco()*txIVA/(1+txIVA);
	        
	       if(totalIVA != parameters.getIva()){
	    	   FaturaInvalida faultInfo = new FaturaInvalida();
	    	   faultInfo.setMensagem("IVA incoerente. Total real: " + totalIVA +  " Total declarado: " + parameters.getIva());
	    	   faultInfo.setNumSeqFatura(parameters.getNumSeqFatura());
	    	   faultInfo.setNumSerie(parameters.getNumSerie());
	    	   throw new FaturaInvalida_Exception(faultInfo.getMensagem(), faultInfo);
	       }
	       
		   //Testar se fatura ja foi comunicada
	       ResultSet rs3 = stmt.executeQuery(query3);
	       rs3.next();
	       if(rs3.getInt("CONTADOR") == 1){
	    	   FaturaInvalida faultInfo = new FaturaInvalida();
	    	   faultInfo.setMensagem("Fatura já comunicada.");
	    	   throw new FaturaInvalida_Exception(faultInfo.getMensagem(), faultInfo);
	       }
	       
		   //Testar se a serie não existe
	       ResultSet rs4 = stmt.executeQuery(query4);
	       rs4.next();
	       if(rs4.getInt("CONTADOR") == 0){
	    	   FaturaInvalida faultInfo = new FaturaInvalida();
	    	   faultInfo.setMensagem("Serie não existe.");
	    	   throw new FaturaInvalida_Exception(faultInfo.getMensagem(), faultInfo);
	       }
	       
		   //Testar se a validade é valida
	       
	       //VALIDADE DA SERIE
	       ResultSet rs5 = stmt.executeQuery(query5);
	       rs5.next();
	       Date d = rs5.getDate("VALID");
	       
	       //Converter a date para calendar
	       Calendar cal = Calendar.getInstance();
	       cal.setTime(d);
	       
	       FaturaInvalida faultInfo = new FaturaInvalida();
	       
	       if(dentroPrazoValidade(parameters.getData(), cal)){
	    	   faultInfo.setMensagem("Fatura fora do prazo.");
	    	   faultInfo.setNumSeqFatura(parameters.getNumSeqFatura());
	    	   faultInfo.setNumSerie(parameters.getNumSerie());
	    	   throw new FaturaInvalida_Exception(faultInfo.getMensagem(), faultInfo);
	       }
	       
	       //testar o numero de faturas comunicadas
	       ResultSet rs6 = stmt.executeQuery(query6);
	       rs6.next();
	       int numFaturas = rs6.getInt("FATURAS_NUM");
	       numFaturas--;
	       stmt2 = (PreparedStatement) conn.prepareStatement(insert2);
	       stmt2.setString(1, numFaturas + "");
	       stmt2.setString(2, parameters.getNumSerie() + "");
	       stmt2.executeUpdate();
	      
	       
	       //dentro da validade, add fatura
	       
	       stmt2 = (PreparedStatement) conn.prepareStatement(insert);
	       stmt2.setString(1, parameters.getNumSerie() + "");
	       stmt2.setString(2, parameters.getData().getYear() + "-" + 
	    		   		      parameters.getData().getMonth() + "-" +
	    		   		      parameters.getData().getDay() + " " +
	    		   		      parameters.getData().getHour() + ":" +
	    		   		      parameters.getData().getMinute() + ":" +
	    		   		      parameters.getData().getSecond());
	       
	       stmt2.setString(3, parameters.getNumSeqFatura() + "");
	       stmt2.setString(4, parameters.getNifEmissor() + "");
	       stmt2.setString(5, parameters.getNifCliente() + "");
	       stmt2.setString(6, totalIVA + "");
	       stmt2.setString(7, parameters.getData().getYear() + "");
	       stmt2.setString(8, parameters.getTotal() + "");

	       stmt2.executeUpdate();
	       conn.close();
	       
	       listPorts = searchBackup();
	        
	        if(listPorts != null)
	        	for(RegistoFaturaPortType port: listPorts)
	        		port.comunicarFatura(parameters);
	       
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (JAXRException e2) {
			e2.printStackTrace();
		}      
	}

	@Override
	public List<Fatura> listarFacturas(Integer nifEmissor, Integer nifCliente)
			throws ClienteInexistente_Exception, EmissorInexistente_Exception {
		
		List<Fatura> list = new ArrayList<Fatura>();
		Statement stmt = null;
		String query1 = "SELECT COUNT(*) AS CONTADOR FROM ENTIDADE WHERE NIF = " + nifEmissor;
		String query2 = "SELECT COUNT(*) AS CLIENTE_NUM FROM ENTIDADE WHERE NIF = " + nifCliente;
		String query3 = "SELECT * FROM FATURA";
		String query4 = "SELECT * FROM FATURA WHERE NIF_CLIENTE = " + nifCliente;
		String query5 = "SELECT * FROM FATURA WHERE NIF_EMISSOR = " + nifEmissor;
		String query6 = "SELECT * FROM FATURA WHERE NIF_EMISSOR = " + nifEmissor + " AND NIF_CLIENTE = " + nifCliente;
		
		try {
			RegistoFaturaMain.createConnection();
			Connection conn = RegistoFaturaMain.conn;
			stmt = (Statement) conn.createStatement();
	        

			//Testar se o restaurante existe
	        ResultSet rs1 = stmt.executeQuery(query1);
	        rs1.next();
	      
	        if(rs1.getInt("CONTADOR") == 0 && nifEmissor != 0){
	        	EmissorInexistente faultInfo = new EmissorInexistente();
	        	faultInfo.setMensagem("O emissor restaurante " + nifEmissor + " não existe.");
	        	faultInfo.setNif(nifEmissor);
	        	throw new EmissorInexistente_Exception(faultInfo.getMensagem(), faultInfo);
	        }
	        
        
	        //Testar se o cliente existe
	        ResultSet rs2 = stmt.executeQuery(query2);
	        rs2.next();
	        
	        if(rs2.getInt("CLIENTE_NUM") == 0 && nifCliente != 0){
	        	ClienteInexistente faultInfo = new ClienteInexistente();
	        	faultInfo.setMensagem("O emissor cliente " + nifCliente + " não existe.");
	        	faultInfo.setNif(nifCliente);
	        	throw new ClienteInexistente_Exception(faultInfo.getMensagem(), faultInfo);
	        }
	        
	        listPorts = searchBackup();
	        
	        if(listPorts != null)
	        	for(RegistoFaturaPortType port: listPorts)
	        		port.listarFacturas(nifEmissor, nifCliente);
	        
	        //parametros sao os dois null
	        if(nifEmissor == 0 && nifCliente == 0){
	        	ResultSet rs3 = stmt.executeQuery(query3);
		        while(rs3.next()){
				    int serie_num = rs3.getInt("SERIE_NUM");
				    	
				    Date data_emissao = rs3.getDate("DATA_EMISSAO");
				    GregorianCalendar gc = new GregorianCalendar();
				    gc.setTime(data_emissao);
				    XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				    	
				    int seq_num = rs3.getInt("SEQ_NUM");
				    int nif_emissor = rs3.getInt("NIF_EMISSOR");
				    int nif_cliente = rs3.getInt("NIF_CLIENTE");
				    int totalIva = rs3.getInt("IVA_FATURA");
				    int totalFatura = rs3.getInt("TOTAL");
				    
				    Fatura f = new Fatura();
				    f.setNumSerie(serie_num);
				    f.setData(calendar);
				    f.setNumSeqFatura(seq_num);
				    f.setNifEmissor(nif_emissor);
				    f.setNifCliente(nif_cliente);
				    f.setIva(totalIva);
				    f.setTotal(totalFatura);
				    
				    //adicionar fatura a lista
				    list.add(f);   	
		        }
		        return list;
	        }
	        
	        //nifEmissor n e recebido
	        if(nifEmissor == 0){
	        	ResultSet rs4 = stmt.executeQuery(query4);
		        
		        while(rs4.next()){
				    int serie_num = rs4.getInt("SERIE_NUM");
				    	
				    Date data_emissao = rs4.getDate("DATA_EMISSAO");
				    GregorianCalendar gc = new GregorianCalendar();
				    gc.setTime(data_emissao);
				    XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				    	
				    int seq_num = rs4.getInt("SEQ_NUM");
				    int nif_emissor = rs4.getInt("NIF_EMISSOR");
				    int nif_cliente = rs4.getInt("NIF_CLIENTE");
				    int totalIva = rs4.getInt("IVA_FATURA");
				    int totalFatura = rs4.getInt("TOTAL");

				    Fatura f = new Fatura();
				    f.setNumSerie(serie_num);
				    f.setData(calendar);
				    f.setNumSeqFatura(seq_num);
				    f.setNifEmissor(nif_emissor);
				    f.setNifCliente(nif_cliente);
				    f.setIva(totalIva);
				    f.setTotal(totalFatura);

				    //adicionar fatura a lista
				    list.add(f);   	
		        }
		        return list;
	        }
	        
	        //nifCliente n e recebido
	        if(nifCliente == 0){	
				ResultSet rs5 = stmt.executeQuery(query5);
		        
		        while(rs5.next()){
				    int serie_num = rs5.getInt("SERIE_NUM");
				    	
				    Date data_emissao = rs5.getDate("DATA_EMISSAO");
				    GregorianCalendar gc = new GregorianCalendar();
				    gc.setTime(data_emissao);
				    XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				    	
				    int seq_num = rs5.getInt("SEQ_NUM");
				    int nif_emissor = rs5.getInt("NIF_EMISSOR");
				    int nif_cliente = rs5.getInt("NIF_CLIENTE");
				    int totalIva = rs5.getInt("IVA_FATURA");
				    int totalFatura = rs5.getInt("TOTAL");

				    Fatura f = new Fatura();
				    f.setNumSerie(serie_num);
				    f.setData(calendar);
				    f.setNumSeqFatura(seq_num);
				    f.setNifEmissor(nif_emissor);
				    f.setNifCliente(nif_cliente);
				    f.setIva(totalIva);
				    f.setTotal(totalFatura);

				    //adicionar fatura a lista
				    list.add(f);   	
		        }
		        return list;
	        }
	        
	        //nifEmissor e nifCliente sao recebidos
	        if(nifEmissor != 0 && nifCliente != 0){
	        	ResultSet rs6 = stmt.executeQuery(query6);
		        while(rs6.next()){
				    int serie_num = rs6.getInt("SERIE_NUM");
				    	
				    Date data_emissao = rs6.getDate("DATA_EMISSAO");
				    GregorianCalendar gc = new GregorianCalendar();
				    gc.setTime(data_emissao);
				    XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				    	
				    int seq_num = rs6.getInt("SEQ_NUM");
				    int nif_emissor = rs6.getInt("NIF_EMISSOR");
				    int nif_cliente = rs6.getInt("NIF_CLIENTE");
				    int totalIva = rs6.getInt("IVA_FATURA");
				    int totalFatura = rs6.getInt("TOTAL");

				    Fatura f = new Fatura();
				    f.setNumSerie(serie_num);
				    f.setData(calendar);
				    f.setNumSeqFatura(seq_num);
				    f.setNifEmissor(nif_emissor);
				    f.setNifCliente(nif_cliente);
				    f.setIva(totalIva);
				    f.setTotal(totalFatura);

				    //adicionar fatura a lista
				    list.add(f);   	
		        }
		        return list;
	        } 
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (DatatypeConfigurationException e2) {
	    	System.out.println("Erro Gregorian " + e2.getMessage());
	    } catch (JAXRException e3) {
			e3.printStackTrace();
		}
	        
        return list;
	}

	@Override
	public int consultarIVADevido(int nifEmissor, XMLGregorianCalendar ano)
			throws EmissorInexistente_Exception {

		String query1 = "SELECT COUNT(*) AS CONTADOR FROM ENTIDADE WHERE NIF = " + nifEmissor;
		
		//como verificar so o ano na bd?
		String query2 = "SELECT IVA_FATURA FROM FATURA WHERE NIF_EMISSOR = ? AND ANO = ?";
		
		Statement stmt = null;
		PreparedStatement stmt2 = null;

		try {
			RegistoFaturaMain.createConnection();
			Connection conn = RegistoFaturaMain.conn;
			stmt = (Statement) conn.createStatement();
	        ResultSet rs1 = stmt.executeQuery(query1);
	        rs1.next();
	        
	        //Testar se a entidade existe
	        if(rs1.getInt("CONTADOR") == 0){
	        	EmissorInexistente faultInfo = new EmissorInexistente();
	        	faultInfo.setMensagem("O emissor " + nifEmissor + " não existe.");
	        	faultInfo.setNif(nifEmissor);
	        	throw new EmissorInexistente_Exception(faultInfo.getMensagem(), faultInfo);
	        }
	       
	        listPorts = searchBackup();
	        
	        if(listPorts != null)
	        	for(RegistoFaturaPortType port: listPorts)
	        		port.consultarIVADevido(nifEmissor, ano);
	        
	        //calcular iva total do emissor
	        
	        stmt2 = (PreparedStatement) conn.prepareStatement(query2);
		    stmt2.setString(1, nifEmissor + "");
		    stmt2.setString(2, ano.getYear() + "");
		    
		    //stmt2.executeQuery();
		    
		    ResultSet rs2 = stmt2.executeQuery();
	        int ivaTotal = 0;
	        
	        while(rs2.next()){
	        	ivaTotal += rs2.getInt("IVA_FATURA");
	        }
	        
	        conn.close();
	        
	        System.out.println(ivaTotal);
	        
	        return ivaTotal;
	        
	    } catch (SQLException e1) {
	    	System.out.println("Erro SQL " + e1.getMessage());
	    } catch (JAXRException e2) {
			e2.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean dentroPrazoValidade(XMLGregorianCalendar emissao, Calendar validade){
		if(validade.get(Calendar.YEAR) > emissao.getYear())
			return true;
		else if(validade.get(Calendar.YEAR) < emissao.getYear())
			return false;
		else if(validade.get(Calendar.MONTH) > emissao.getMonth())
			return true;
		else if(validade.get(Calendar.MONTH) < emissao.getMonth())
			return false;
		else if(validade.get(Calendar.DAY_OF_MONTH) > emissao.getDay())
			return true;
		else if(validade.get(Calendar.DAY_OF_MONTH) < emissao.getDay())
			return false;
		else if(validade.get(Calendar.HOUR_OF_DAY) > emissao.getHour())
			return true;
		else if(validade.get(Calendar.HOUR_OF_DAY) < emissao.getHour())
			return false;
		else if(validade.get(Calendar.MINUTE) > emissao.getMinute())
			return true;
		else if(validade.get(Calendar.MINUTE) < emissao.getMinute())
			return false;
		else if(validade.get(Calendar.SECOND) > emissao.getSecond())
			return true;
		else
			return false;
	}
	
	public ArrayList<RegistoFaturaPortType> searchBackup() throws JAXRException {
		ArrayList<RegistoFaturaPortType> lista = new ArrayList<RegistoFaturaPortType>();
		String uddiURL = "http://localhost:8081";
		
		System.out.printf("Contacting UDDI at %s%n", uddiURL);
        UDDINaming uddiNaming = new UDDINaming(uddiURL);
                
        if(RegistoFaturaMain.primary.equals("0"))
    		return null;
       
        for(int i = 1; i <= Integer.parseInt(RegistoFaturaMain.numberBackups); i++){
			String endpointAddress = uddiNaming.lookup("Backup" + i);

	        if (endpointAddress == null) {
	            System.out.println("Not found! Backup" + i);
	            continue;
	        } 
	        else {        	
        		System.out.printf("Found %s%n", endpointAddress); 

		        System.out.println("Creating stub ...");
		        
		        RegistoFaturaService service = new RegistoFaturaService();	        
		        RegistoFaturaPortType registoFaturaPort = service.getRegistoFaturaPort();
	
		        System.out.println("Setting endpoint address ...");
		        BindingProvider bindingProvider = (BindingProvider) registoFaturaPort;
		        Map<String, Object> requestContext = bindingProvider.getRequestContext();
		        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
		        
		        lista.add(registoFaturaPort);
	        }
        }
        
        return lista;
	}
}
