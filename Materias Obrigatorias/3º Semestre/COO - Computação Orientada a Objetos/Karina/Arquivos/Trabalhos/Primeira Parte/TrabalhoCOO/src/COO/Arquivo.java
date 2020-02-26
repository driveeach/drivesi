package COO;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
 
public class Arquivo{  
       
        HashMap<String, Integer> indice;
        File t;
        String ser;
        File a_ser;
        Date d;
        String path;
        String nome;
       
        public Arquivo(File p_arq, String p_nome, String p_path) throws IOException {          
                t = p_arq;
                nome = p_nome;
                path = p_path;
                ser = p_nome.replace(".txt", ".ser");
                a_ser = new File(ser);
               
        }
       
                @SuppressWarnings("resource")
        public int buscaSequencial(String busca) {
               
                long inicio = System.currentTimeMillis();  
                Scanner sc = null;
                busca = busca.toUpperCase();
                int cont = 0;  
 
                try {
                        sc = new Scanner (new FileReader(t));
                        do {
                                String aux = sc.next();
                                aux = aux.toUpperCase();
                                if (aux.equals(busca)) {
                                        cont++;
                                }
                                else if(aux.equals(busca +",") || aux.equals(busca +".") || aux.equals(busca +":") || aux.equals(busca +";"))
                                        	cont++;
                                
                        } while (sc.hasNext());
                }
 
                catch (FileNotFoundException fileNotFoundException) {
                        System.err.printf ("Exception: %s", fileNotFoundException);
                }
 
                long fim = System.currentTimeMillis();
                long tempo = fim - inicio;
                System.out.println("Arquivo: " + nome);
                System.out.println("Tempo Sequencial: " + tempo);
                System.out.println("Quantidade: " + cont);
                System.out.println("***********************");
               
                return cont;
               
        }
 
        public void hashLinha (String busca) {
                long inicio = System.currentTimeMillis();  
                String linha = null;
                HashMap<String, Palavra> mapPalavras = new HashMap<String, Palavra>();
                BufferedReader br = null;
                Scanner sc = null;
                try {
                        br = new BufferedReader(new FileReader(t));
                        linha = br.readLine();
                        int cont = 1;
                        while (linha != null) {
                        	System.out.println(linha);
                                linha.toUpperCase();
                                sc = new Scanner (linha);
                                String palavra =null;
                               // if (sc.hasNext())palavra = sc.next().toUpperCase();//condicao para evitar uma exceção
                                
                                while (sc.hasNext()) {   
                                	palavra = sc.next().toUpperCase();
                                	palavra = removeEspeciais(palavra);
                                	System.out.println(palavra);
                                        if (mapPalavras.containsKey(palavra.toUpperCase())) {
                                                mapPalavras.get(palavra.toUpperCase()).addQuantidade();
                                                mapPalavras.get(palavra.toUpperCase()).addLinha(linha, cont);
                                                mapPalavras.get(palavra.toUpperCase()).setArquivo(nome);
                                                
                                        }
                                        else {
                                                Palavra novo = new Palavra();
                                                novo.setPalavra(palavra.toUpperCase());
                                                novo.addQuantidade();
                                                novo.addLinha(linha, cont);
                                                mapPalavras.put(palavra.toUpperCase(), novo);
                                                
                                        }
                                        
                                       // palavra = sc.next().toUpperCase();
                                }
                                linha = br.readLine();
                                cont++;
                        }
                }
                catch (IOException e) {
                        System.err.printf ("Exception: %s\n", e);
                }
                long fim = System.currentTimeMillis();
                long tempo = fim - inicio;
                System.out.println("Tempo Hashmap alocacao: " + tempo);
                serelizacao(mapPalavras);
                leituraIndice(busca, mapPalavras);
                Scanner input = new Scanner (System.in);
                System.out.println ("Deseja visualizar esse arquivo? (s/n)");
                String visualizar = input.next();
                if (visualizar.equalsIgnoreCase("s")) {
                	mapearLinha(mapPalavras, busca);
                }
        }
       
        public static String removeEspeciais(String p_palavra)
        {
        	String v_retorno = p_palavra;
        	
        	try{
        	v_retorno =	v_retorno.replace(".", "");
        	}catch (Exception e){}
        	
        	try{
        		v_retorno =	v_retorno.replace(",", "");
        	}catch (Exception e){}
        	
        	try{
        		v_retorno =	v_retorno.replace(":", "");
        	}catch (Exception e){}
        	
        	try{
        		v_retorno =	v_retorno.replace(";", "");
        	}catch (Exception e){}
        	
        	
        	return v_retorno;
        	
        	
        }
        
        public static void leituraIndice (String busca, HashMap <String, Palavra> mapPalavras) {
                long inicio = System.currentTimeMillis();  
                String aux = busca.toUpperCase();
                Palavra p = mapPalavras.get(aux);
                int freq =0;
                if (p !=null){
                        freq = p.getQuantidade();
                }
                long fim = System.currentTimeMillis();
                long tempo = fim - inicio;
                System.out.println("Tempo de Busca no Hashmap: " + tempo);
                System.out.println("Quantidade: "+freq);
        }

        public static void mapearLinha(HashMap<String,Palavra> map, String busca){
                Set<String> s = map.keySet();
                for(String o :s){
                		if (o.equals(busca.toUpperCase())){ 
	                        Palavra aux = map.get(o);
	                        ArrayList<String> lista = aux.getLinhas();
	                        for(String a:lista){
	                                System.out.println("Linha "+a);
	                        }
                		}
                }
        }
        public void serelizacao (HashMap <String, Palavra> mapPalavras) {
                try {
                        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(ser));
                        obj.writeObject(mapPalavras);
                        obj.close();
                }
                catch (IOException iOException) {
                        iOException.printStackTrace();
                }
        }
       
        public void desserializacao (String busca) throws ClassNotFoundException {
                HashMap<String, Palavra> mapPalavras = null;
                try {
                        ObjectInputStream obj = null;
                        if (a_ser != null) {
                                obj = new ObjectInputStream(new FileInputStream(a_ser));
                        }
                        else {
                                a_ser = new File(ser);
                                obj = new ObjectInputStream(new FileInputStream(a_ser));
                        }
                        mapPalavras = (HashMap<String, Palavra>) obj.readObject();
                        obj.close();
                }
                catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                }
                catch (IOException iOException) {
                        iOException.printStackTrace();
                }
                leituraIndice (busca, mapPalavras);
                Scanner sc = new Scanner (System.in);
                System.out.println ("Deseja visualizar esse arquivo? (s/n)");
                String visualizar = sc.next();
                if (visualizar.equalsIgnoreCase("s")) {
                	mapearLinha(mapPalavras, busca);
                }
        }
}