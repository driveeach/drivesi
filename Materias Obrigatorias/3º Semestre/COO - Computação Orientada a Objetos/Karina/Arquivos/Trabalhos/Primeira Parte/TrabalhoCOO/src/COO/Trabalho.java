package COO;

import java.io.*;
import java.util.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class Trabalho implements ActionListener {
	JButton btBusca;
	JTextField edPalavra;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		Trabalho trabalho = new Trabalho();
		trabalho.telaInicial();	
	}
	
	public void telaInicial() {
		
		JFrame frame = new JFrame();
		JLabel lbBusca = new JLabel("Digite a palavra para busca:");
		lbBusca.setAlignmentX(0);
		lbBusca.setAlignmentY(0);
		
		btBusca = new JButton("Buscar");		
		btBusca.setSize(40,25);
		edPalavra = new JTextField();
		
		
		btBusca.addActionListener(this);
		
		frame.getContentPane().add(BorderLayout.NORTH, lbBusca);
		frame.getContentPane().add(edPalavra);
		frame.getContentPane().add(BorderLayout.SOUTH, btBusca);
		
		frame.setSize(300,150);
		frame.setVisible(true);	
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			Busca(edPalavra.getText().toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public void Busca(String p_palavra) throws Throwable
	{
		String path = new File(".").getCanonicalPath();
		path += "\\src\\COO\\";			
		ArquivoConf.carregar(path , "Arquivos.txt");
		
		Scanner input = new Scanner (System.in);
		//System.out.println("Digite a palavra que deseja buscar:");
		
		String busca = p_palavra; //input.next();
		
		Palavra v_PalavraBusca = new Palavra();
		v_PalavraBusca.setPalavra(busca);
		
		int total = 0;
		for (int i=0; i < ArquivoConf.Arquivos.size(); i++) {			
			total += ArquivoConf.Arquivos.get(i).buscaSequencial(busca);
			
			if (ArquivoConf.Arquivos.get(i).a_ser != null) {
				if (ArquivoConf.Arquivos.get(i).t.lastModified() > ArquivoConf.Arquivos.get(i).a_ser.lastModified()) {
					ArquivoConf.Arquivos.get(i).hashLinha(busca);
				}
				else {
					ArquivoConf.Arquivos.get(i).desserializacao(busca);
				}
			}
			
			else {
				ArquivoConf.Arquivos.get(i).desserializacao(busca);
			}
			System.out.println ("-----------------------------");
		}
		
		System.out.println("Total: " + total);
		
		input.close();
		
	}
}