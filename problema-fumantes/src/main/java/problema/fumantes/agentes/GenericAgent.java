package problema.fumantes.agentes;

import java.util.Random;
import java.util.concurrent.Semaphore;

import problema.fumantes.Main;

public class GenericAgent extends Agent implements Runnable {
	public GenericAgent(Semaphore agentSemaphore, Semaphore tabaco, Semaphore papel, Semaphore isqueiro) {
		super(agentSemaphore, tabaco, papel, isqueiro);
	}

	private int geraRandomico(int min, int max) {
		Random randomico = new Random();
		int item = randomico.nextInt((max-min)+1) + min;
		return item;
	}

	@Override
	public void run() {
		while (Main.control) {
			try {				
				agentSemaphore.acquire();
				mutex2.acquire();

				switch (geraRandomico(1, 3)) {
				case 1:
					//AgenteA 
					try {	
						System.out.println("Agente - Tabaco & Papel");
						tabaco.release();
						papel.release();
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}										
					break;
				case 2: 
					//AgenteB
					try {
						System.out.println("Agente - isqueiro & papel");
						isqueiro.release();
						papel.release();
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}									
					break;
				case 3: 
					//AgenteC
					try {
						System.out.println("Agente - Tobaco & isqueiro");
						tabaco.release();
						isqueiro.release();
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}					
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}