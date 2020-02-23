class Jogador {
    //atributos
    ListaDePecas mao;
	Mesa m;
	int p;
    // metodos
	Jogador (int max) {
		mao = new ListaDePecas(7);
	}
	void poe (Peca p) {
		mao.poe (p);
	}
	Peca tira (int i) {
		return mao.tira(i);
	}
	void print () {
		mao.print();
	}
	/* começo a pensar se não vale a pena fazer Jogador subclasse de ListaDePecas */
	/* se tem a sena, joga, caso contrário, retorna null */

	boolean jogaPrimeira () {
		for (int i=0;i<mao.tamanho;i++) {
			if ((mao.a[i].a==6) && (mao.a[i].b==6)) {
				m.insere(mao.tira(i));
				return true;
			}
		}
		return false;
	}
	boolean jogaNaPrimeira () { // o lado aberto no inicio é o a, e o no fim é o b
		for (int i=0;i<mao.tamanho;i++) {
			if ((m.abertas.tamanho>0) && ((mao.a[i].a==m.abertas.a[0].a) || (mao.a[i].b==m.abertas.a[0].a))) {
				m.inserePrimeira(mao.tira(i));
				return true;
			}
		}
		return false;
	
	}
	boolean jogaNaUltima () { // o lado aberto no inicio é o a, e o no fim é o b
		for (int i=0;i<mao.tamanho;i++) {
			if ((m.abertas.tamanho>0) && ((mao.a[i].a==m.abertas.a[m.abertas.tamanho-1].b) || (mao.a[i].b==m.abertas.a[m.abertas.tamanho-1].b))) {
				m.insereUltima(mao.tira(i));
				return true;				
			}
		}
		return false;
	
	}
	boolean joga () {
		if (!jogaPrimeira ()) {
			if (!jogaNaPrimeira()) {
				return jogaNaUltima();
			}
		}
		return true;
	}
	void gambiarraVovo(){
		for (int i=0;i<mao.tamanho;i++) {
			try{
				Peca p = mao.tira(i);
				if ((m.abertas.tamanho>0) &&((mao.a[i].a==m.abertas.a[0].a) || (mao.a[i].b==m.abertas.a[0].a))) {
					for (int k=m.abertas.tamanho;k>0;k--) {
						m.abertas.a[k]=m.abertas.a[k-1];
					}
					m.abertas.a[0]=p;
					if (m.abertas.a[0].a==m.abertas.a[1].a) {
						int aux=m.abertas.a[0].a;
						m.abertas.a[0].a=m.abertas.a[0].b;
						m.abertas.a[0].b=aux;
					}
					m.abertas.tamanho++;
					//m.inserePrimeira(mao.tira(i));
				}
				else if ((m.abertas.tamanho>0) && ((mao.a[i].a==m.abertas.a[m.abertas.tamanho-1].b) || (mao.a[i].b==m.abertas.a[m.abertas.tamanho-1].b))) {
					if (m.abertas.a[m.abertas.tamanho-1].b==p.b) {
						int aux=p.a;
						p.a=p.b;
						p.b=aux;
					}
					m.abertas.poe(p);
					//m.insereUltima(mao.tira(i));
				}/*
				if ((m.abertas.tamanho>0) &&((mao.a[i].a==m.abertas.a[0].a) || (mao.a[i].b==m.abertas.a[0].a))) {
					m.inserePrimeira(mao.tira(i));
				}
				else if ((m.abertas.tamanho>0) && ((mao.a[i].a==m.abertas.a[m.abertas.tamanho-1].b) || (mao.a[i].b==m.abertas.a[m.abertas.tamanho-1].b))) {
					m.insereUltima(mao.tira(i));
				}*/
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
    void verMesa (Mesa m) { // referencia permanente para a lista de peças na mesa
		this.m=m;
	}
}

