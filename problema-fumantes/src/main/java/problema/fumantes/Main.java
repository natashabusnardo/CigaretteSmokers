package problema.fumantes;

import java.util.concurrent.Semaphore;

import problema.fumantes.agentes.GenericAgent;
import problema.fumantes.colocar.ColocarTabaco;
import problema.fumantes.colocar.ColocarPapel;
import problema.fumantes.colocar.ColocarIsqueiro;
import problema.fumantes.smokers.FumanteComIsqueiro;
import problema.fumantes.smokers.FumanteComPapel;
import problema.fumantes.smokers.FumanteComTabaco;

public class Main {
	
	public static boolean control = true;
	
	public static void main(String[] args) throws InterruptedException {
		Semaphore agentSemaphore = new Semaphore(1);
		Semaphore mutex = new Semaphore(1);
		Semaphore tabaco = new Semaphore(0);
		Semaphore papel = new Semaphore(0);
		Semaphore isqueiro = new Semaphore(0);
		
		Semaphore tabacoGlobal = new Semaphore(0);
		Semaphore papelGlobal = new Semaphore(0); 
		Semaphore isqueiroGlobal = new Semaphore(0); 
	
		Boolean isIsqueiro = new Boolean(false); 
		Boolean isTabaco = new Boolean(false);
		Boolean isPapel = new Boolean(false);

		Thread agente = new Thread(new GenericAgent(agentSemaphore, tabaco, papel, isqueiro), "Agente");
		
		Thread colocarTabaco = new Thread(new ColocarTabaco(isIsqueiro, isTabaco, isPapel, tabaco, null, null, null, papelGlobal, isqueiroGlobal, mutex), "PusherA");
		
		Thread colocarPapel = new Thread(new ColocarPapel(isIsqueiro, isTabaco, isPapel, null, papel, null, tabacoGlobal, null, isqueiroGlobal, mutex), "PusherB");
		
		Thread colocarIsqueiro = new Thread(new ColocarIsqueiro(isIsqueiro, isTabaco, isPapel, null, null, isqueiro, tabacoGlobal, papelGlobal, null, mutex), "PusherC");
		
		Thread fumanteComIsqueiro = new Thread(new FumanteComIsqueiro(null, null, isqueiroGlobal, agentSemaphore),"SmokerWithIsqueiro");
		Thread fumanteComPapel = new Thread(new FumanteComPapel(null, papelGlobal, null, agentSemaphore),"SmokerWithPapel");
		Thread fumanteComTabaco = new Thread(new FumanteComTabaco(tabacoGlobal, null, null, agentSemaphore),"SmokerWithTabaco");
		
		agente.start();
		
		colocarTabaco.start();
		colocarPapel.start();
		colocarIsqueiro.start();
		
		fumanteComIsqueiro.start();
		fumanteComPapel.start();
		fumanteComTabaco.start();

	}
}