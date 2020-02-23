class Mesa {
    ListaDePecas abertas;
	Mesa () {
		abertas= new ListaDePecas(28);
	}
	ListaDePecas publicarListaDePecas () {
		return abertas;
	}
	void inserePrimeira(Peca p) {
		for (int i=abertas.tamanho;i>0;i--) {
			abertas.a[i]=abertas.a[i-1];
		}
		abertas.a[0]=p;
		if (abertas.a[0].a==abertas.a[1].a) {
			int aux=abertas.a[0].a;
			abertas.a[0].a=abertas.a[0].b;
			abertas.a[0].b=aux;
		}
		abertas.tamanho++;
	}
	void insereUltima(Peca p) {
		if (abertas.a[abertas.tamanho-1].b==p.b) {
			int aux=p.a;
			p.a=p.b;
			p.b=aux;
		}
		abertas.poe(p);
	}
	void insere (Peca p) { // para a inserção da sena
		abertas.poe(p);
	}
	/*
    vencedor () {
    }
    trancou () {
    }
    mostraResultado () {
    }
	*/
}

