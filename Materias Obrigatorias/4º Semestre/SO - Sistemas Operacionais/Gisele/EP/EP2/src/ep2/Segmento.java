package ep2;

class Segmento implements Comparable<Segmento>{
	boolean alocado;
	int base, tamanho, pid, secao; //secao: 0=texto, 1=dados, 2=pilha
	
	public Segmento(boolean alocado, int base, int pid, int secao, int tamanho) {
		this.alocado = alocado;
		this.base = base;
		this.pid = pid;
		this.secao = secao;
		this.tamanho = tamanho;
	}
	
	String detalhes(){
		if (alocado)
			return "[" + pid + "," + letraSecao(secao) + "][" + base + ":" + (base+tamanho) + "]";
		else
			return "[HOLE][" + base + ":" + (base+tamanho) + "]";
	}
	
	char letraSecao(int secao){
		if (secao == 0) return 'T';
		if (secao == 1) return 'D';
		if (secao == 2) return 'P';
		return 'x';
	}

	public int compareTo(Segmento s) {
		return this.base-s.base;
	}
}
