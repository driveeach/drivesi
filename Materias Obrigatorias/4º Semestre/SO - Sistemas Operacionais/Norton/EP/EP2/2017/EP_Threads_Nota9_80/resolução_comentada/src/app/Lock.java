package app;

public class Lock {

	boolean estaBloqueado = false;
	Thread bloqueadoPor = null;
	int contBloqueados = 0;

	/*enquanto a região crítica estiver bloqueada e estiver bloqueado pela thread atual, espera. Quando sai desse loop, bloqueia*/
	public synchronized void bloqueia() throws InterruptedException {
		Thread threadAtual = Thread.currentThread();
		while (estaBloqueado && bloqueadoPor != threadAtual) {
			wait();
		}
		estaBloqueado = true;
		contBloqueados++;
		bloqueadoPor = threadAtual;
	}
	
	/*tira 1 do contador de bloqueados e verifica se a região crítica ainda está bloqueada*/
	public synchronized void desbloqueia() {
		if (Thread.currentThread() == this.bloqueadoPor) {
			contBloqueados--;

			if (contBloqueados == 0) {
				estaBloqueado = false;
				notify();
			}
		}
	}
}