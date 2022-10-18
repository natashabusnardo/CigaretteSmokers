package problema.fumantes.colocar;

import java.util.concurrent.Semaphore;

public abstract class Colocar {
	protected static boolean isIsqueiro;
	protected static boolean isTabaco;
	protected static boolean isPapel;
	protected Semaphore tabaco;
	protected Semaphore papel;
	protected Semaphore isqueiro;
	protected Semaphore tabacoGlobal;
	protected Semaphore papelGlobal;
	protected Semaphore isqueiroGlobal;
	protected Semaphore mutex;
	
	public Colocar(boolean isIsqueiro, boolean isTobacco, boolean isPaper,
				   Semaphore tabaco, Semaphore papel, Semaphore isqueiro,
				   Semaphore tabacoGlobal, Semaphore papelGlobal, Semaphore isqueiroGlobal,
				   Semaphore mutex) {
		super();
		
		this.tabaco = tabaco;
		this.papel = papel;
		this.isqueiro = isqueiro;
		this.tabacoGlobal = tabacoGlobal;
		this.papelGlobal = papelGlobal;
		this.isqueiroGlobal = isqueiroGlobal;
		this.mutex = mutex;
	}

	public abstract void scheduleSmoker();

}
