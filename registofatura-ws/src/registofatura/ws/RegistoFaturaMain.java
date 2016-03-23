package registofatura.ws;

import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.registry.JAXRException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;

import pt.registofatura.ws.RegistoFaturaPortType;
import pt.registofatura.ws.RegistoFaturaService;
import registofatura.uddi.UDDINaming;

import com.mysql.jdbc.Connection;

public class RegistoFaturaMain {
	public static Connection conn;
	
	public class SendAliveThread extends Thread {
		ArrayList<IamALivePortType> backupAlivePort = new ArrayList<IamALivePortType>();
		ArrayList<RegistoFaturaPortType> backupRegistoFaturaPort = new ArrayList<RegistoFaturaPortType>();
		ArrayList<String> backupRegistoFaturaWsUrl = new ArrayList<String>();
		
		public void run(){
    		try {

    			IamALivePortType alivePort;
    			RegistoFaturaPortType registoFaturaPort;
		        UDDINaming uddiNaming = new UDDINaming(uddiURL);
		        
			    for(int i = 1; i <= Integer.parseInt(numberBackups); i++){
					String endpointAddress = uddiNaming.lookup("Alive" + i);
				
			        if (endpointAddress == null) {
			            System.out.println("Not found! Alive"+ i);
			            continue;
			        } else {
			            System.out.printf("Found %s%n", endpointAddress);
			        }
	
			        System.out.println("Creating stub ...");
			        
			        IamALiveImplService service = new IamALiveImplService();	        
			        alivePort = service.getIamALivePortTypePort();
	
			        System.out.println("Setting endpoint address ...");
			        BindingProvider bindingProvider = (BindingProvider) alivePort;
			        Map<String, Object> requestContext = bindingProvider.getRequestContext();
			        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
			        
			        backupAlivePort.add(alivePort);
			    }
			    
			    for(int i = 1; i <= Integer.parseInt(numberBackups); i++){
					String endpointAddress = uddiNaming.lookup("Backup" + i);

					
			        if (endpointAddress == null) {
			            System.out.println("Not found! Backup" + i);
			            continue;
			        } else {
			            System.out.printf("Found %s%n", endpointAddress);
			        }
	
			        System.out.println("Creating stub ...");
			        
			        RegistoFaturaService service = new RegistoFaturaService();	        
			        registoFaturaPort = service.getRegistoFaturaPort();
	
			        System.out.println("Setting endpoint address ...");
			        BindingProvider bindingProvider = (BindingProvider) registoFaturaPort;
			        Map<String, Object> requestContext = bindingProvider.getRequestContext();
			        requestContext.put(ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
			        
			        backupRegistoFaturaPort.add(registoFaturaPort);
					backupRegistoFaturaWsUrl.add(endpointAddress);

			    }

			} catch (JAXRException e2) {
				System.out.printf("Caught exception:  %s%n", e2);
			}

    		for(IamALivePortType port : backupAlivePort)
			    port.setBackupList(backupRegistoFaturaWsUrl);

        	while(true){
        		
    			try {
    				for(IamALivePortType port : backupAlivePort){
    					port.iamALive();
    				}
    				
					Thread.sleep(SLEEPSECONDS * 1000);
					
				} catch (InterruptedException e) {				
					System.out.printf("Caught exception:  %s%n", e);

				}
        	}
		}
	}
	
	
	private static final int SLEEPSECONDS = 7;
	
	private static String uddiURL;
    private static String wsName;
    public static String wsAliveName;
    public static String registoFaturaWsUrl;
    public static String aliveWsUrl;
    public static String primary;
    private static String database;
    private static String databaseuser;
    public static String numberBackups;
    public static SendAliveThread thread;
    
    public static void main(String[] args) {

    	RegistoFaturaMain rf = new RegistoFaturaMain();
    	
    	thread = rf.new SendAliveThread();
    	
        // Check arguments
        if (args.length < 3) {
            System.err.println("Argument(s) missing!");
            System.err.printf("Usage: java %s uddiURL wsName wsURL%n", RegistoFaturaMain.class.getName());
            return;
        }

        uddiURL = "http://localhost:8081";
        wsName = args[0];  		//RegistoFatura ou Backup*
        wsAliveName = args[1];
        registoFaturaWsUrl = "http://localhost:" + args[2] + "/registofatura-ws/endpoint/" + args[2];	
        aliveWsUrl = "http://localhost:" + args[2] + "/alive-ws/endpoint/" + args[2];	
        primary = args[3];  	//1 ou 0
        database = args[4]; 
        databaseuser = args[5];
        numberBackups = args[6];
        
        createConnection();
        
        if(primary.equals("1")){
        	thread.start();
        	registaServico(wsName, registoFaturaWsUrl);
        }
        
        else {
       		registaAlive(wsAliveName, aliveWsUrl);
       		registaServico(wsName, registoFaturaWsUrl);
       		
        }
    }    
    
    public static void registaServico(String wsName, String wsUrl){
        Endpoint endpoint = null;
        UDDINaming uddiNaming = null;
        
    	try {
    		
            endpoint = Endpoint.create(new RegistoFaturaImpl());
           
            // publish endpoint
            System.out.printf("Starting %s%n", wsUrl);
            endpoint.publish(wsUrl);

            // publish to UDDI
            System.out.printf("Publishing '%s' to UDDI at %s%n", wsName, uddiURL);
            uddiNaming = new UDDINaming(uddiURL);
            uddiNaming.rebind(wsName, wsUrl);

            // wait
            System.out.println("Awaiting connections");
            System.out.println("Press enter to shutdown");
            System.in.read();

        } catch(Exception e) {
            System.out.printf("Caught exception: %s%n", e);
            e.printStackTrace();

        } finally {
            try {
                if (endpoint != null) {
                    // stop endpoint
                    endpoint.stop();
                    System.out.printf("Stopped %s%n", wsUrl);
                }

            } catch(Exception e) {
                System.out.printf("Caught exception when stopping: %s%n", e);
            }
            try {
                if (uddiNaming != null) {
                    // delete from UDDI
                    uddiNaming.unbind(wsName);
                    System.out.printf("Deleted '%s' from UDDI%n", wsName);
                }
            } catch(Exception e) {
                System.out.printf("Caught exception when deleting: %s%n", e);
            }
        }
    }
    
    public static void registaAlive(String wsName, String wsUrl){
        Endpoint endpoint = null;
        UDDINaming uddiNaming = null;
        
    	try {
    		
    		endpoint = Endpoint.create(new IamALiveImpl());
           
            // publish endpoint
            System.out.printf("Starting %s%n", wsUrl);
            endpoint.publish(wsUrl);

            // publish to UDDI
            System.out.printf("Publishing '%s' to UDDI at %s%n", wsName, uddiURL);
            uddiNaming = new UDDINaming(uddiURL);
            uddiNaming.rebind(wsName, wsUrl);

            
        } catch(Exception e) {
            System.out.printf("Caught exception: %s%n", e);
            e.printStackTrace();
        }
    }
    
    public static void removeRegistoAlive(){
		try {
			UDDINaming uddiNaming = new UDDINaming(uddiURL);
			uddiNaming.unbind(wsAliveName);
			uddiNaming.unbind(wsName);
		} catch (JAXRException e) {
			e.printStackTrace();
		}
    }
    
    public static void createConnection(){
    
		try {
			conn =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, databaseuser, "regfatdbpass");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}

