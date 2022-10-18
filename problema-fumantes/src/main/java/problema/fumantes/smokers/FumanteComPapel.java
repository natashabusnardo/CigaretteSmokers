package problema.fumantes.smokers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

import problema.fumantes.Main;
import problema.fumantes.agentes.Agent;

public class FumanteComPapel extends Fumante implements Runnable {

	public FumanteComPapel(Semaphore tabacoGlobal, Semaphore papelGlobal, Semaphore isqueiroGlobal, Semaphore agentSemaphore) {
		super(tabacoGlobal, papelGlobal, isqueiroGlobal, agentSemaphore);
	}

	@Override
	public void makeCigarette() {

			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Fumante com papel faz cigarro. "+dateFormat.format(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Fumante com papel fumando...");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			try {
				papelGlobal.acquire();
				makeCigarette();
				System.out.println("Acorda Agente...\n");
				Agent.mutex2.release();
				agentSemaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
