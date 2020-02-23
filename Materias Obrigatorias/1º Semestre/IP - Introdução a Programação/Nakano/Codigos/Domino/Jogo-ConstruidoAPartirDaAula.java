class Jogo {
    //atributos
    static Jogador[] lj;
    static ListaDePecas lp;
    static ListaDePecas m;  // mesa
	static int npecas;
	static int iPrimeiro;
	static int iVencedor;
    //metodos
    public static void main (String[] args) {
        registraJogadores();
        criaPecas();
        criaMesa();
        //embaralhaPecas();
        distribuiPecas();
        //verMao();
		verMesa();   // este não tinha
        chamaPrimeira();
		m.store(lj[iPrimeiro].jogaPrimeira());
        while (!vencedor()) {
        //while (!vencedor() && !trancou()) {
            jogar();
            proximo();
        }
        mostraResultado();
    }
	static void criaMesa () {
		m=new ListaDePecas (nPecas);
	}
	static void registraJogadores () {
		lj=new Jogador[4];
		for (int i=0;i<4;i++) {
			lj[i]=new Jogador(npecas);
		}
	}
	static void criaPecas() {
		lp=new ListaDePecas();
		npecas=lp.cria(6);
	}
	static void distribuiPecas() {
		for (int i=0;i<nPecas;i++) {
			lj[i%4].recebePeca(lp.oferece());
		}
	}
	static void chamaPrimeira () {
		for (int i=0;i<4;i++) {
			if ((lj[i].chamaPrimeira().a==6) &&
			        (lj[i].chamaPrimeira().b==6)) {
				iPrimeiro=i;
			}
		}
	}
	static void verMesa () {
		for (int i=0;i<4;i++) {
			lj[i].verMesa(m);
		}
	}
	static boolean vencedor () {
		for (int i=0;i<4;i++) {
			if (!lj[i].mao.haPecas()) {
				iVencedor=i;
			}
		}
		return true;
	}

}


