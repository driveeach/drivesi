class Jogo {
    //atributos
	Monte m; 
    ListaDePecas mao;
	Jogador[] lj;
	Mesa mesa;
	int tamanhoAnteriorMesa=0;
    // metodos
	void joga () {
		/* cria peças e monte */
		m=new Monte (28);
		m.cria();
		/* cria mesa */
		mesa = new Mesa ();
		
		/* cria jogadores e dá o acesso à mesa. */
		lj=new Jogador[4];
		for (int i=0;i<4;i++) {
			lj[i]=new Jogador (7);
			lj[i].verMesa (mesa);
		}
		/* distribui peças */
		for (int i=0;i<7;i++) {
			for (int j=0;j<4;j++) {
				lj[j].poe (m.tira());
			}
		}
		/* imprime monte e peças distribuidas */
		m.print ();
		for (int i=0;i<4;i++) {
			System.out.print ("Jogador[" + i + "]={");
			lj[i].print();
			System.out.println ("};");
		}
		int k=0, passou=0;
		while ((passou<4) && (venceu()==-1)) {
			if (!lj[k].joga()){ 
				if(k == 2){
					//Vó
					lj[k].gambiarraVovo();
					passou = 0;
					System.out.println("Passou");
				}
				passou++;
			}
			else passou=0;
			mesa.abertas.print();
			System.out.println();
			k=(++k)%4;
		}
		
		if(venceu() == -1){
			int v = 0;
			for (int i=0;i<4;i++) {
				lj[i].p = contarPontos(lj[i]);
				System.out.println("\nNumero de pontos do jogador "+i+": "+lj[i].p);
				for (int j = 0; j<4; j++){
					if(lj[i].p < lj[j].p){
						if(lj[i].p < lj[v].p)
							v = i;
					}
					if(lj[j].p < lj[i].p){
						if(lj[i].p < lj[v].p)
							v = j;
					}
				} 
			}
			System.out.println ("\nO vencedor é " + v);
		}
		else{
			System.out.println ("O vencedor é " + venceu() +"\n");
		}
	}
	int venceu () {
		for (int i=0;i<4;i++) {
			if (lj[i].mao.tamanho==0) {
				lj[i].p = contarPontos(lj[i]);
				return i;
			}
		}
		return -1;
	}
	int contarPontos(Jogador j){
		int p =0;
		for(int i = 0; i<j.mao.tamanho; i++)
			p += j.mao.a[i].a + j.mao.a[i].b; 
		return p;
	}
	
	public static void main (String[] args) {
		Jogo j = new Jogo();
		j.joga();
	}
}












