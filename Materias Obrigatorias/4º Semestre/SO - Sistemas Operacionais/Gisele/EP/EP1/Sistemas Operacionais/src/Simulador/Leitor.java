package Simulador;

import java.util.*;
import java.io.*;

public class Leitor {

	public Leitor(String nome) {
		
		DataInputStream entrada = new DataInputStream(new FileInputStream (nome));
		
		String processo;
		int tempo, prioridade;
		try{			
			while(true){
				
				processo = "";
				tempo = 0;
				prioridade = 0;
				
				for(int i=0; i<2; i++){//Laço para ler String
					char c = entrada.readChar();
					nome += c;
				}				
				tempo = entrada.readInt();
				prioridade = entrada.readInt(); 
				
				Processo mp = new Processo(processo, tempo, prioridade);
				list.add(mp);
			}
		}
		catch(EOFException e){
			entrada.close();
			System.out.println("Arquivo carregado");			
		}
	}
	
}
