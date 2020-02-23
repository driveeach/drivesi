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
    
    static void insercao(int[] v) {
        for (int i=1; i<v.length; i++) {
            int aux = v[i];
            // busco o lugar de v[i] dentre os ordenados, deslocando se necessário
            int j = i;
            while ((j > 0) && (aux < v[j-1])) {
                v[j] = v[j-1];
                j--;
            }
            // coloco o elemento lá, se achou o lugar
            v[j] = aux;
        }
    }
    
    static void insercao(Residencia[] v) {
        for (int i=1; i<v.length; i++) {
            Residencia aux = v[i];
            // busco o lugar de v[i] dentre os ordenados, deslocando se necessário
            int j = i;
            while ((j > 0) && (aux.comparaRes(v[j-1]) < 0)) {
                v[j] = v[j-1];
                j--;
            }
            // coloco o elemento lá, se achou o lugar
            v[j] = aux;
        }
    }
    
    static int buscaBin(int[] arr, int el) {
        int fim = arr.length-1;
        int ini = 0;
        while (ini <= fim) {
            int meio = (fim + ini)/2;
            if (arr[meio] < el) ini = meio + 1;
            else
                if (arr[meio] > el) fim = meio - 1;
                else return(meio);
        }
        return(-1);
    }
    
    static int buscaBin(Residencia[] arr, double area) {
        int fim = arr.length-1;
        int ini = 0;
        while (ini <= fim) {
            int meio = (fim + ini)/2;
            if (arr[meio].area() < area) ini = meio + 1;
            else
                if (arr[meio].area() > area) fim = meio - 1;
                else return(meio);
        }
        return(-1);
    }

    
    public static void main(String[] args) {
        /*int[] v = {9,8,4,10,6};
        
        insercao(v);
        
        for (int el : v)
            System.out.print(el+" ");
        System.out.println(); */
        
        Projeto pr = new Projeto(5);
        
        AreaCasa c = new AreaCasa(10,3);
        AreaPiscina p = new AreaPiscina(3);
        Residencia r = new Residencia(c,p);
        pr.adicionaRes(r);
        
        c = new AreaCasa(11,3);
        p = new AreaPiscina(3);
        r = new Residencia(c,p);
        pr.adicionaRes(r);
        
        c = new AreaCasa(11,5);
        p = new AreaPiscina(3);
        r = new Residencia(c,p);
        pr.adicionaRes(r);
        
        c = new AreaCasa(11,5);
        p = new AreaPiscina(8);
        r = new Residencia(c,p);
        pr.adicionaRes(r);
        
        c = new AreaCasa(20,5);
        p = new AreaPiscina(8);
        r = new Residencia(c,p);
        pr.adicionaRes(r);
        
        c = new AreaCasa(20,15);
        p = new AreaPiscina(8);
        r = new Residencia(c,p);
        pr.adicionaRes(r);
        
        
        
        for (Residencia res : pr.condominio)
            System.out.println(res.area());
        System.out.println();
        
        System.out.println(buscaBin(pr.condominio,701.06192928297468));
    }
}
