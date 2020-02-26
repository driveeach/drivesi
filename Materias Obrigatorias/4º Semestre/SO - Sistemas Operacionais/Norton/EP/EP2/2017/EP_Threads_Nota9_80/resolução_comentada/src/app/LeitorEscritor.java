package app;

class LeitorEscritor {
	int leitoresAtivos = 0;
	boolean escritorPresente = false;

	/*verifica se pode escrever*/
	boolean condicaoEscrita() {
		return leitoresAtivos == 0 && !escritorPresente;
	}

	/*verifica se pode ler*/
	boolean condicaoLeitura() {
		return !escritorPresente;
	}

	/*enquanto não puder ler, espera. Depois aumenta o número de leitores ativos*/
	synchronized void comecarLeitura() {
		while (!condicaoLeitura()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		leitoresAtivos++;
	}
	
	/*tira um leitor ativo*/
	synchronized void pararLeitura() {
		leitoresAtivos--;
		notifyAll();
	}

	/*enquanto não puder escrever, espera. Depois coloca um escritor como presente*/
	synchronized void comecarEscrita() {
		while (!condicaoEscrita()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		escritorPresente = true;
	}

	/*tira o escritor presente*/
	synchronized void pararEscrita() {
		escritorPresente = false;
		notifyAll();
	}
}