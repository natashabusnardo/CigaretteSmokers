package problema.fumantes.colocar;

import java.util.concurrent.Semaphore;

public class ColocarIsqueiro extends Colocar implements Runnable {

	public ColocarIsqueiro(boolean isIsqueiro, boolean isTabaco, boolean isPapel,
						   Semaphore tabaco, Semaphore papel, Semaphore isqueiro,
						   Semaphore tabacoGlobal, Semaphore papelGlobal, Semaphore isqueiroGlobal,
						   Semaphore mutex) {
		super(isIsqueiro, isTabaco, isPapel, tabaco, papel, isqueiro, tabacoGlobal,papelGlobal, isqueiroGlobal, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			isqueiro.acquire();
			mutex.acquire();
			if (isPapel) {
				Colocar.isPapel = false;
				tabacoGlobal.release(); //sinalizar o Smoker with tabaco
			} else if (isTabaco) {
				Colocar.isTabaco = false;
				papelGlobal.release(); //sinalizar o Smoker with papel
			} else {
				Colocar.isIsqueiro = true;
			}
			mutex.release();
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void run() {
		while(true) {
			scheduleSmoker();			
		}
	}
}
