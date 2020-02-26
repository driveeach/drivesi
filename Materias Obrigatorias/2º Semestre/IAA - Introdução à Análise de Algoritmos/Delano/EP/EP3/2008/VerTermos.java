/********************************************************/
/**	ACH 2002 - Introducao a Ciencia da Computacao II	*/
/**	EACH-USP - Segundo Semestre de 2008					*/
/** Turma 02 Matutino - Prof. Delano Medeiros Beder		*/
/**														*/
/**	Terceiro Exercício-Programa	- Dicionário e Tesauro  */
/**	Arquivo: DicionarioTesauro6411927.java				*/
/**														*/
/**	Murilo Galvão Honório		Numero USP: 6411927		*/
/**														*/
/**	Entrega: 02/12/2008									*/
/**														*/
/********************************************************/

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

abstract class Termo implements java.io.Serializable
{	
	private String palavra;
	
	Termo(String palavra)
	{
		this.palavra = palavra;
	}
	
	abstract void insereTermos(String frase, int posicao);
	abstract void imprimeVerbete(int opcao);
	
	public String getTermo()
	{
		return this.palavra;
	}
}

class TermoDicionario extends Termo implements java.io.Serializable
{
	private String[] significados = new String[5];
	
	TermoDicionario(String palavra)
	{
		super(palavra);
	}
		
	void imprimeVerbete(int opcao) 
	{
		System.out.println(significados[opcao]);	
	}

	void insereTermos(String frase, int posicao)
	{
		this.significados[posicao] = frase;
	}
	
	String[] getSignificados()
	{
		return this.significados;
	}
}

class TermoTesauro extends Termo implements java.io.Serializable
{
	private String[] termos = new String[3];
	
	TermoTesauro(String palavra)
	{
		super(palavra);
	}
	
	void imprimeVerbete(int opcao)
	{
		System.out.println(termos[opcao]);
	}

	void insereTermos(String frase, int posicao)
	{
		this.termos[posicao] = frase;
	}
	
	String[] getTermos()
	{
		return this.termos;
	}
}

interface TabelaHash extends java.io.Serializable
{
	static final int TAMANHO = 1001; //deve ser um número primo, para garantir o funcionamento adequado do HashDuplo
	void insere(String palavra, Termo termo);
	void remove(String palavra);
	Termo busca(String palavra);
}

abstract class HashListasLigadas implements TabelaHash, java.io.Serializable
{
	
	private LinkedList<Termo>[] tabela;
	
	public HashListasLigadas()
	{
		this.tabela = new LinkedList[TAMANHO];
		for (int i = 0; i < tabela.length; i++)
			tabela[i] = new LinkedList<Termo>();
	}
	
	protected int hash(String palavra)
	{
		int somaDasLetras = 0;
		int i = 0;
		for (i = 0; i < palavra.length() && i < TAMANHO; ++i)
		{
			somaDasLetras += palavra.charAt(i);
		}
		return somaDasLetras % TAMANHO;
	}
	
	
	//String palavra aplicarei getTermo();
	public void insere(String palavra, Termo termo)
	{
		LinkedList<Termo> lista = tabela[hash(palavra)];
		
		if (!lista.contains(palavra))
			tabela[hash(palavra)].addLast(termo);
		else
		{
			int posicao = lista.indexOf(palavra);
			lista.add(posicao, termo);
		}
	}
	
	public void remove(String palavra)
	{
		LinkedList<Termo> lista = tabela[hash(palavra)];
		
		for (int i = 0; i < lista.size(); i++)
		{
			String palavraNaLista = lista.get(i).getTermo();
			if (palavraNaLista.equals(palavra))
			{
				lista.remove(lista.get(i)); //remove o objeto na posição
				break;
			}
		}
	}
	
	public Termo busca(String palavra)
	{
		LinkedList<Termo> lista = tabela[hash(palavra)];
		
		for (int i = 0; i < lista.size(); i++)
		{
			String palavraNaLista = lista.get(i).getTermo();
			if (palavraNaLista.equals(palavra))
			{
				return lista.get(i);
			}
		}
		return null;
	}
}

class Dicionario extends HashListasLigadas implements java.io.Serializable
{
	boolean insereSignificados(String palavra, String frase, int posicao)
	{
		TermoDicionario buscado = (TermoDicionario) busca(palavra);
		if (buscado != null)
		{
			buscado.insereTermos(frase, posicao);
			return true;
		}
		return false;
	}
	
	void buscaVerbete(String palavra)
	{
		TermoDicionario buscado = (TermoDicionario) busca(palavra);
		if (buscado != null)
		{
			for(int i = 0; i < buscado.getSignificados().length; i++)
				buscado.imprimeVerbete(i);
		}
		else
			System.out.println("Termo não foi encontrado.");
	}
}

class SeriadorDicionario
{
	 public void gravarDicionario(Dicionario dicionario, String nome)
	 {
		 try 
		 {
			 ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(nome));
			 saida.writeObject(dicionario);
			 saida.close();
		 } 
		 catch (IOException ioe) 
		 {
			 System.err.println("Erro ao gravar '" + nome + "'.");
		 }
	 }

	 public Dicionario carregarDicionario(String nome)
	 {
		 Dicionario dicionario = null;
		 try
		 {
			 ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nome));
			 dicionario = (Dicionario) entrada.readObject();
			 entrada.close();
		 }
		 catch (FileNotFoundException fnfe)
		 {
			 System.err.println("Arquivo nao encontrado (" + nome + ").");
		 }
		 catch (ClassNotFoundException cnfe)
		 {
			 System.err.println("Classe 'Dicionario' nao encontrada.");
		 }
		 catch (IOException ioe)
		 {
			 System.err.println("Erro ao ler '" + nome + "'.");
		 }
		 return dicionario;
	 }
}

class SeriadorTesauro
{

}


/*

class Tesauro implements TabelaHash
{
	TermoTesauro[] tesauro = new TermoTesauro[TAMANHO];
	
	private int hash(String palavra, int i)
	{
		int somaValCar = 0;

		// Soma os valores dos caracteres
		for (i = 0; i < palavra.length(); i++)
			// nome contem somente letras e nome.length
			somaValCar += palavra.charAt(i);

		int h1 = (somaValCar % TAMANHO);
		int h2 = 1+(somaValCar % (TAMANHO-1));
		
		return (h1 + i*h2) % TAMANHO;
	}
	
	// inserir os termos relacionados às palavras (ver classe TermoTesauro)
	// contidas no tesauro
@Override
public void insere(String arg0, Termo arg1) {
	// TODO Auto-generated method stub
	
}
@Override
public void remove(String arg0) {
	// TODO Auto-generated method stub
	
}
void insereTermoRelacionado(String palavra)
	{
		busca(palavra);
	}
	
	realizar buscas de palavras no tesauro e imprimir o resultado encontrado, por exemplo, ao buscar
	a palavra cancelar deve-se imprimir:
	Cancelar (Verbo):
	(1) Sin^onimos: abortar, suprimir.
	(2) Termos contrastantes: n~ao ha.
	(3) Termos relacionados: terminar, rescindir, invalidar.

	Termo busca(String palavra)
	{
		int i = 0;
		int pos = hash(palavra, i);
		if (palavra.equals(tesauro[pos]))
		{
			System.out.println("Exemplo.");
			return tesauro[pos];
		}
		i++;
		pos = hash(palavra, i);
	}



}



*/

/* Crie, utilizando um método main, uma interface textual que permite ao usuário realizar as funções
descritas acima para o dicionário e para o tesauro. */

/*
class InterfaceTextual
{
	public static void main(String[] args)
	{
		String nome = "bla.dat";
		try
		{
			File file = new File(nome);
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				System.out.println("Arquivo já existe.");
		}
		finally{}
		
		SeriadorDicionario sd = new SeriadorDicionario(); //inicializa as operações de gravação e leitura
		
		TermoDicionario t = new TermoDicionario("água");
		t.insereTermos("Ex.: mole em pedra dura", 0);
		t.imprimeVerbete(0);
		TermoDicionario x = t;
		System.out.println("x->" + x.getTermo());
		t.imprimeVerbete(0);
		t.insereTermos("substantivo",1);
		t.insereTermos("Fluido",2);
		t.insereTermos("Azul",3);
		t.insereTermos("Inodor",4);
		t.imprimeVerbete(3);
		Dicionario d = new Dicionario();
		d.insere("água", t);
		Dicionario e = new Dicionario();
		
		sd.gravarDicionario(d, "bla.dat"); //salvando...
		
		e = sd.carregarDicionario("bla.dat");
		
		
		Termo a = e.busca("água");
		System.out.println("arquivo->" + a.getTermo());
		a.imprimeVerbete(2);
		d.busca("água");
		TermoDicionario t2 = new TermoDicionario("fogo");
		t2.insereTermos("queima", 0);
		d.insere("fogo", t2);
		d.busca("fogo");
	}

*/

public class VerTermos extends JPanel implements ActionListener
{
	protected JTextField textField;
	protected JTextArea textArea;
	private final static String newline = "\n";
	
	public VerTermos()
	{
		super(new GridBagLayout());
		
		textField = new JTextField(20);
		textField.addActionListener(this);
		
		textArea = new JTextArea(5, 20);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);

		//Add Components to this panel.
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		c.fill = GridBagConstraints.HORIZONTAL;
		add(textField, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);
	}
	
	SeriadorDicionario sd = new SeriadorDicionario();
	Dicionario e = sd.carregarDicionario("bla.dat");
	
	
	
	public void actionPerformed(ActionEvent evt)
	{
		String text = textField.getText();
	    Termo a = e.busca(text);
	    if (a != null)
	    {
	    	textArea.append(a.getTermo() + newline + a.getTermo());
	    	textField.selectAll();
	    }
	    else
	    	e.insere(text, a);

		//Make sure the new text is visible, even if there
		//was a selection in the text area.
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private static void createAndShowGUI()
	{
		//Create and set up the window.
		JFrame frame = new JFrame("Dicionário");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add contents to the window.
		frame.add(new VerTermos());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}
}