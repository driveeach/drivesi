package hash;

import javax.swing.JOptionPane;

public class Teste {

	int tamanhoTabela;
	int nroColisoes;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Numero de registros:"));
        int TS = Integer.parseInt(JOptionPane.showInputDialog("Tamanho da tabela:"));
                
        Amostra amostra = new Amostra(tamanho);       
        TipoTabela t = new Tabela(TS);
        
        long tempoDecorrido = 0;
        long tempoAtomico = 0;
        final int INT_FIELD_SIZE  = 4;
        
        Hash h = new Linear();
        Rehash rh = new Duplo();
        
        for (int i = 0; i < amostra.tamanho(); i++){
            tempoAtomico = t.insere(amostra.getAmostra(i), h, rh);
            if (tempoAtomico == -1) break; //tabela cheia
            tempoDecorrido += tempoAtomico;
        }
        
        System.out.println("Tempo inserção: "+tempoDecorrido/1000000000 + "," + tempoDecorrido%1000000000 + " s");
        
        System.out.println("Tamanho tabela: "+ TS * INT_FIELD_SIZE/1000+" Kb");
        //amostra.print();
        boolean confirma = false;
        int cont = 0;
        
        tempoDecorrido = 0;
        for (int i = 0; i < amostra.tamanho(); i++){
            tempoAtomico = t.busca(amostra.getAmostra(i), h, rh, confirma);            
            if (tempoAtomico == -1) break; //tabela cheia
            tempoDecorrido += tempoAtomico;
        }
        
        System.out.println("Tempo busca bem-sucedida: "+tempoDecorrido/1000000000 + "," + tempoDecorrido%1000000000 + " s");
        
        
        //As vezes essa parte atrapalha pois consome mta memoria
        
        tempoDecorrido = 0;
        Amostra amostra_mal = new Amostra(tamanho/10);//cria uma amostra que provavelmete não esteja na tabela
        for (int i = 0; i < amostra_mal.tamanho(); i++){
            tempoAtomico = t.busca(amostra_mal.getAmostra(i), h, rh, confirma);
            if(!confirma) cont++;
            if (tempoAtomico == -1) break; //tabela cheia
            tempoDecorrido += tempoAtomico;
        }
        
        System.out.println("Tempo busca : "+tempoDecorrido/1000000000 + "," + tempoDecorrido%1000000000 + " s, com "+cont+
                " buscas mal sucedidas");
        
        
        tempoDecorrido = 0;
        for (int i = 0; i < amostra.tamanho(); i++){
            tempoAtomico = t.remove(amostra.getAmostra(i), h, rh, confirma);            
            if (tempoAtomico == -1) break; //tabela cheia
            tempoDecorrido += tempoAtomico;
        }
        
        System.out.println("Tempo remoção: "+tempoDecorrido/1000000000 + "," + tempoDecorrido%1000000000 + " s");
    }

}
