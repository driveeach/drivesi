package app;

import java.io.FileNotFoundException;

public class Escritor implements Runnable{
	private Lock lock;
	private LeitorEscritor controlador;
	private int implementacao;
	
	public Escritor(int i, LeitorEscritor controlador, Lock lock, int implementacao) throws FileNotFoundException {
		this.controlador = controlador;
		this.implementacao = implementacao;
		this.lock = lock;
	}

	/*coloca para rodar a implementação desejada*/
	@Override
	public void run() {
		if(implementacao == 1) leitorEescritor();
		else if(implementacao == 2)
			try {
				naoLeitorEescritor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	/*implementação sem leitor/escritor*/
	private void naoLeitorEescritor() throws InterruptedException {
		lock.bloqueia();
		bdEsleep();
		lock.desbloqueia();
	}

	/*implementação com leitor/escritor*/
	private void leitorEescritor() {
		controlador.comecarEscrita();
		bdEsleep();
		controlador.pararEscrita();
	}

	/*faz acesso aleatório no bd e coloca a thread pra dormir*/
	private void bdEsleep() {
		EstruturaBD.acessosAleatoriosEscritor();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
