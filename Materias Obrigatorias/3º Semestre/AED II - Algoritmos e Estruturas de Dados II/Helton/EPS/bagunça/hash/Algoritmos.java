package hash;

class Linear implements Hash{

	public int hash(int registro, int tamanhoTabela) {
		return registro % tamanhoTabela;
	}

	public String nome() {
		return "Linear";
	}
}


class Mult implements Hash{
    
    public int hash(int registro, int tamanhoTabela) {
        double valor = 0.6180339887*registro;        
        double inteira = Math.floor(0.6180339887*registro);        
        double frac = valor - inteira;        
        return (int) Math.floor(tamanhoTabela*frac);
    }
    
    public String nome() {
        return "Multiplicativo";
    }    
}



class Duplo implements Rehash{
    
    public String nome() {
        return "Rehasinhg duplo";
    }
    
    public int rehash(int i, int tamanhoTabela, int j) {
        int c = (i + j) % tamanhoTabela;
        if(c<0) return c*-1;
        else return c;
    }
    
}

class Linear_probing implements Rehash{

    public String nome() {
        return "Rehashing linear";
    }

    public int rehash(int i, int tamanhoTabela, int j) {
        return (i+1) % tamanhoTabela;
    }
}

class Quadratico implements Rehash{
    
    public String nome() {
        return "Rehashing Quadratico";
    }
    
    public int rehash(int i, int tamanhoTabela, int j) {
        int c = (i + (j*j)) % tamanhoTabela;
        if(c<0) return c*-1;
        else return c;
    }
    
}