package problema.fumantes.agentes;

import java.util.concurrent.Semaphore;

public abstract class Agent {
	protected Semaphore agentSemaphore;
	protected Semaphore tabaco;
	protected Semaphore papel;
	protected Semaphore isqueiro;

	public static Semaphore mutex2 = new Semaphore(1);

	public Agent(Semaphore agentSemaphore, Semaphore tabaco, Semaphore papel, Semaphore isqueiro) {
		super();
		this.agentSemaphore = agentSemaphore;
		this.tabaco = tabaco;
		this.papel = papel;
		this.isqueiro = isqueiro;
	}

}
