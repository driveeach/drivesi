class Jogo {
    //atributos
    ListaDeJogadores lj;
    ListaDePecas lp;
    Mesa m;
    //metodos
    public static void main (String[] args) {
        criaMesa();
        registraJogadores();
        criaPecas();
        embaralhaPecas();
        distribuiPecas();
        verMao();
        chamaPrimeira();
        while (!vencedor() && !trancou()) {
            jogar();
            proximo();
        }
        mostraResultado();
    }
}

class Jogador {
    //atributos
    ListaDePecas mao;
    // metodos
    jogar () {
    }
    verMao () {
    }
    chamaPrimeira () {
    }
    verMesa () {
    }

}

class ListaDeJogadores {
    //atributos
    Jogador[] a; // array contendo referencias para cada jogador
    int primeiroDaRodada;    //indice do primeiro da rodada (chamaPrimeira)
    int primeiroLivre;       //indice da primeira posicao livre do array
    int corrente;            //indice do jogador corrente
    //metodos
    registraJogadores () {
    }
    next () {
    }
    chamaPrimeira() {
    }
}

class Peca {
    int a, b; //numero de face em dada peca
}

class ListaDePecas {
    Peca[] a;
    embaralha (){
    }
    distribui (){
    }
    oferece (){
    }
}

class Mesa {
    ListaDePecas abertas;
    ListaDePecas fechadas;
    vencedor () {
    }
    trancou () {
    }
    mostraResultado () {
    }
}

