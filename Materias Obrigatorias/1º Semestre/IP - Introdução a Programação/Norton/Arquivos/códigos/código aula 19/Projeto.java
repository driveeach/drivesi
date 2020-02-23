import java.util.Scanner;

/*
    Classe para criação de um condomínio de casas
*/
class Projeto {
    /* Representação do condomínio */
    Residencia[] condominio;
    /* Índice da última residência alocada */
    int ultimo = -1;
    
    /*
        Adiciona, uma residência ao condomínio. Retorna true se conseguiu e false se o condomínio já está cheio
    */
    boolean adicionaRes(Residencia r) {
        if (this.ultimo < this.condominio.length-1) {
            ultimo++;
            this.condominio[ultimo] = r;
            return(true);
        }
        return(false);
    }
    
    /*
        Constrói o condomínio sem casas
    */
    Projeto(int tam) {
        condominio = new Residencia[tam];
    }
    
    /*
        Retorna a primeira posição de uma residência no condomínio com uma pisicina de determinado raio
    */
    int buscaPiscSeq(double raio) {
        for (int i=0; i<this.condominio.length; i++)
            if (this.condominio[i].piscina.raio == raio) return(i);
        return(-1);
    }
    
    static int buscaSeq(int[] arr, int el) {
        int cont = 0;
        for (int i=0; i<arr.length; i++) {
            cont++;
            if (arr[i] == el) return(i);
            else
                if (arr[i] > el) break;
        }
        return(-1);
    }
    
    static void bolha(int[] v) {
        for (int ult = v.length-1; ult>0; ult--) {
            for (int i=0; i<ult; i++) {
                if (v[i] > v[i+1]) {
                    int aux = v[i];
                    v[i] = v[i+1];
                    v[i+1] = aux;
                }
            }
        }
    }
    
    static void bolha(Residencia[] v) {
        for (int ult = v.length-1; ult>0; ult--) {
            for (int i=0; i<ult; i++) {
                if (v[i].comparaRes(v[i+1]) > 0) {
                    Residencia aux = v[i];
                    v[i] = v[i+1];
                    v[i+1] = aux;
                }
            }
        }
    }
    
    /* inicio <= i < fim */
    static int posMenorEl(int[] v, int inicio, int fim) {
        int posMenor = -1;
        if ((v!=null) && (inicio>=0) && (fim <= v.length) && (inicio < fim)) {
            posMenor = inicio;
            for (int i=inicio+1; i<fim; i++) {
                if (v[i] < v[posMenor]) posMenor = i;
            }
        }
        return(posMenor);
    }
    
    static void selecao(int[] v) {
        for (int i=0; i<v.length-1; i++) {
            int posMenor = posMenorEl(v,i,v.length);
            if (v[posMenor] < v[i]) {
                int aux = v[i];
                v[i] = v[posMenor];
                v[posMenor] = aux;
            }
        }
    }
    
    static int posMenorEl(Residencia[] v, int inicio, int fim) {
        int posMenor = -1;
        if ((v!=null) && (inicio>=0) && (fim <= v.length) && (inicio < fim)) {
            posMenor = inicio;
            for (int i=inicio+1; i<fim; i++) {
                if (v[i].comparaRes(v[posMenor]) < 0) posMenor = i;
            }
        }
        return(posMenor);
    }
    
    static void selecao(Residencia[] v) {
        for (int i=0; i<v.length-1; i++) {
            int posMenor = posMenorEl(v,i,v.length);
            if (v[posMenor].comparaRes(v[i]) < 0) {
                Residencia aux = v[i];
                v[i] = v[posMenor];
                v[posMenor] = aux;
            }
        }
    }
    
    public static void main(String[] args) {
        Projeto pr = new Projeto(5);
        
        for (int i=0; i<5; i++) {
            AreaCasa c = new AreaCasa(Math.random()*100,Math.random()*30);
            AreaPiscina p = new AreaPiscina(Math.random()*10);
            Residencia r = new Residencia(c,p);
            pr.adicionaRes(r);
        }
        
        for (Residencia r : pr.condominio)
            System.out.println(r.area());
        System.out.println();
        
        selecao(pr.condominio);
        
        for (Residencia r : pr.condominio)
            System.out.println(r.area());
        
    }
}
