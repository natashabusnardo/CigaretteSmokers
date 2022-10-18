package problema.fumantes.colocar;

import java.util.concurrent.Semaphore;

public class ColocarPapel extends Colocar implements Runnable {

	public ColocarPapel(boolean isIsqueiro, boolean istabaco, boolean isPapel,
						Semaphore tobacco, Semaphore papel, Semaphore isqueiro,
						Semaphore tobaccoGlobal, Semaphore papelGlobal, Semaphore isqueiroGlobal,
						Semaphore mutex) {
		super(isIsqueiro, istabaco, isPapel, tobacco, papel, isqueiro, tobaccoGlobal, papelGlobal, isqueiroGlobal, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			papel.acquire();
			mutex.acquire();			
			if (isTabaco) {
				isTabaco = false;
				isqueiroGlobal.release(); //sinaliza o Smoker with isqueiro
			} else if (isIsqueiro) {
				isIsqueiro = false;
				tabacoGlobal.release(); //sinalizar o Smoker with tobacco
			} else {
				isPapel = true;
			}
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			scheduleSmoker();
		}
	}
}
