package problema.fumantes.colocar;

import java.util.concurrent.Semaphore;

public class ColocarTabaco extends Colocar implements Runnable{
	public ColocarTabaco(boolean isIsqueiro, boolean isTabaco, boolean isPapel,
						 Semaphore tabaco, Semaphore papel, Semaphore isqueiro,
						 Semaphore tabacoGlobal, Semaphore papelGlobal, Semaphore isqueiroGlobal,
						 Semaphore mutex) {
		super(isIsqueiro, isTabaco, isPapel, tabaco, papel, isqueiro, tabacoGlobal, papelGlobal, isqueiroGlobal, mutex);
	}

	@Override
	public void scheduleSmoker() {
		try {
			tabaco.acquire();
			mutex.acquire();			
			if(isPapel){
				Colocar.isPapel=false;
				isqueiroGlobal.release(); //sinaliza o Smoker with isqueiro
			}
			else if(isIsqueiro){
				Colocar.isIsqueiro=false;
				papelGlobal.release(); //sinaliza o Smoker with papel
			}
			else {
				Colocar.isTabaco=true;
			}
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			scheduleSmoker();	
		}
	}
}
