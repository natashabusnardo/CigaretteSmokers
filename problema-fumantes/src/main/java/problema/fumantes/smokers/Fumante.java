package problema.fumantes.smokers;

import java.util.concurrent.Semaphore;

public abstract class Fumante {
	protected Semaphore tabacoGlobal;
	protected Semaphore papelGlobal;
	protected Semaphore isqueiroGlobal;
	protected Semaphore agentSemaphore;
	
	public Fumante(Semaphore tabaco, Semaphore papel, Semaphore isqueiro, Semaphore agentSemaphore) {
		super();
		this.tabacoGlobal = tabaco;
		this.papelGlobal = papel;
		this.isqueiroGlobal = isqueiro;
		this.agentSemaphore=agentSemaphore;
	}
	public abstract void makeCigarette();
}
