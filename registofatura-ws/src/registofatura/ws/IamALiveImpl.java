package registofatura.ws;

import java.util.ArrayList;

import javax.jws.Oneway;
import javax.jws.WebService;

@WebService(name = "IamALivePortType")
public class IamALiveImpl {
	private class ReceiveAliveThread extends Thread {
		public void run(){

        	while(true){
        		try {
					Thread.sleep(SLEEPSECONDS * 1000);
					contador--;
					if(contador <= 0 && primaryUp){
						System.out.println("He is dead :S");
						
						if((RegistoFaturaMain.registoFaturaWsUrl).equals(list.get(0))){
							System.out.println("I will take the lead!");
							newPrimaryUrl = list.get(0);
							list.remove(0);
							rebind(newPrimaryUrl);
							break;

						}
						else{
							System.out.println("This guy will take the lead: " + list.get(0));
							primaryUp = false;
						}
					}
        		} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
		}
	}
	
	private static final int SLEEPSECONDS = 1;
	private int contador = 10;
	private ReceiveAliveThread thread = new ReceiveAliveThread();
	private ArrayList<String> list = new ArrayList<String>();
	private boolean primaryUp = false;
	private String newPrimaryUrl;
	
	public IamALiveImpl(){
		thread.start();
	}
	
	@Oneway 
	public void iamALive(){
		System.out.println("Its alive.");
		contador = 10;
	}
	
	@Oneway 
	public void setBackupList(ArrayList<String> list){
		this.list = list;
		primaryUp = true;
	}
	 
	private void rebind(String url){
		this.thread.interrupt();
		RegistoFaturaMain.removeRegistoAlive();
		RegistoFaturaMain.primary = "1";
		RegistoFaturaMain.thread.start();
		RegistoFaturaMain.registaServico("RegistoFatura", url);
	}
}
