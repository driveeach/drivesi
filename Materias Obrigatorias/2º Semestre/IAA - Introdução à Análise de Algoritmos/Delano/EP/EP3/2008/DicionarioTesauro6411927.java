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
/**														*
/** Testado com WinXP SP3, IDE Eclipse 3.4.1, JDK 1.6.0 */
/********************************************************/

import java.util.LinkedList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

abstract class Termo implements Serializable
{	
	private static final long serialVersionUID = 6924812553261360107L;
	private String palavra;
	
	Termo(String palavra)
	{
		this.palavra = palavra;
	}
	
	abstract void insereSignificados(String frase, int posicao);
	
	public String getTermo()
	{
		return this.palavra;
	}
	
	public void apagar()
	{
		this.palavra = "REMOVIDO";
	}
}

class TermoDicionario extends Termo implements Serializable
{
	private static final long serialVersionUID = -8133042314827106139L;
	private String[] significados = new String[5];
	
	TermoDicionario(String palavra)
	{
		super(palavra);
	}

	void insereSignificados(String frase, int posicao)
	{
		this.significados[posicao] = frase;
	}
	
	String getSignificado(int i)
	{
		return this.significados[i];
	}
}

class TermoTesauro extends Termo implements java.io.Serializable
{
	private static final long serialVersionUID = 7859546495030373834L;
	private String[] termos = new String[3];  //0-sinonimos, 1-antonimos, 2-relacionados
	
	TermoTesauro(String palavra)
	{
		super(palavra);
	}

	void insereSignificados(String frase, int posicao)
	{
		this.termos[posicao] = frase;
	}
	
	String getTermos(int i)
	{
		return this.termos[i];
	}
}



interface TabelaHash extends Serializable
{
	//deve ser um número primo, para garantir o funcionamento adequado do HashDuplo
	//preferencialmente um primo não muito próximo de uma potÊncia de dois
	static final int TAMANHO = 1777; //1024; se fosse utilizada a outra forma de obter h2
	void insere(String palavra, Termo termo);
	void remove(String palavra);
	Termo busca(String palavra);
}

abstract class HashListasLigadas implements TabelaHash, Serializable
{
	private static final long serialVersionUID = -1707112678594574347L;
	private LinkedList<Termo>[] tabela;
	
	@SuppressWarnings("unchecked")
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
		//função has pelo método da divisão
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


/*

class HashDuplo implements TabelaHash, Serializable
{
	private static final long serialVersionUID = -6814086729270476359L;
	int m = TAMANHO;
    private Termo[] tabela = new Termo[m];
	static final String REMOVIDO = "TERMO_REMOVIDO";
	

    protected int funcaoHashAux1(String palavra)
    {
    	int i = 0;
        int somaValorLetras = 0;
        for(i = 0; i < palavra.length() && i < TAMANHO; ++i)
        	somaValorLetras += palavra.charAt(i);
        return somaValorLetras % TAMANHO;
    }
    
	//faremos a opção sugerida por Cormen, ou seja escolhemos
	//TAMANHO primo e h2 retornando inteiro positivo menor que m
    protected int funcaoHashAux2(String palavra)
    {
        int somaValorLetras = 0;
        for(int i = 0; i < palavra.length(); i++)
            somaValorLetras += palavra.charAt(i);
        return 1 + (somaValorLetras % (TAMANHO-1));
        //return (somaValorLetras*2)-1; as duas implementações são válidas, dependendo do valor de TAMANHO
    }
    
    //a funcao do hash duplo propriamente dita
    protected int funcaoHash(String palavra, int i)
    {
    	return (funcaoHashAux1(palavra)+(i*funcaoHashAux2(palavra))) % TAMANHO;
    }

        
    public void insere(String palavra, Termo termo)
    {
    	int i = 0;
    	int chave = funcaoHash(palavra, i);
    	//hora da sondagem
    	while (i != m || !tabela[chave].getTermo().equals(REMOVIDO))
    	{
    		if (tabela[chave] == null)
    			tabela[chave] = termo;
    		else 
    			i++;

    		chave = funcaoHash(palavra, i);
    	}
    }
    
    public void remove(String palavra)
    {
    	int i = 0;
    	int chave = funcaoHash(palavra, i);
    	while (tabela[chave] != null && i != m)
    	{
    		if (tabela[chave].getTermo().equals(palavra))
    		{
    			tabela[chave].apagar();
    			break;
    		}
    		i++;
    		chave = funcaoHash(palavra, i);
    	}
    }

    public Termo busca(String palavra)
    {
    	int i = 0;
    	int chave = funcaoHash(palavra, i);
    	while (tabela[chave] != null && i != m)
    	{
    		if (tabela[chave].getTermo().equals(palavra))
    		{
    			return tabela[chave];
    		}
    		i++;
    		chave = funcaoHash(palavra, i);
    	}
    	return null;
    }
 }

*/

class Dicionario extends HashListasLigadas implements Serializable
{
	private static final long serialVersionUID = -5157875447972300488L;

	boolean insereSignificados(String palavra, String frase, int posicao)
	{
		TermoDicionario buscado = (TermoDicionario) busca(palavra);
		if (buscado != null)
		{
			buscado.insereSignificados(frase, posicao);
			return true;
		}
		return false;
	}
}


class Tesauro extends HashListasLigadas implements java.io.Serializable
{
	private static final long serialVersionUID = 3912488928440027660L;

	boolean insereSignificados(String palavra, String frase, int posicao)
	{
		TermoTesauro buscado = (TermoTesauro) busca(palavra);
		if (buscado != null)
		{
			buscado.insereSignificados(frase, posicao);
			return true;
		}
		return false;
	}
}


/*
class Tesauro extends HashDuplo implements Serializable
{
	private static final long serialVersionUID = 3912488928440027660L;

	boolean insereSignificados(String palavra, String frase, int posicao)
	{
		TermoTesauro buscado = (TermoTesauro) busca(palavra);
		if (buscado != null)
		{
			buscado.insereSignificados(frase, posicao);
			return true;
		}
		return false;
	}
}
*/

//código para salvar o dicionário e o tesauro em arquivo
class Serializador extends JOptionPane
{
	private static final long serialVersionUID = 1397858280663674559L;
	ObjectOutputStream out;
	ObjectInputStream in;
	Dicionario d;
	Tesauro t;
	
	public void salvar(Dicionario d, Tesauro t, String nome)
	{
		try
		{
			out = new ObjectOutputStream(new FileOutputStream(nome));
			out.writeObject(d);
			out.writeObject(t);
			out.flush();
			out.close();
		}
		catch (IOException ioe) 
		{
			System.err.println("Erro ao gravar o arquivo.");
		}
	}
	
	public Dicionario abrirDicionario(String nome)
	{
		try 
		{
			in = new ObjectInputStream(new FileInputStream(nome));
			d = (Dicionario) in.readObject();
			t = (Tesauro) in.readObject();
			in.close();
		}
		catch (FileNotFoundException fnfe)
		{
			JOptionPane.showMessageDialog(null, "Arquivo não localizado.", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch (ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null, "Classes nao encontradas.", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch (ClassCastException ref)
		{
			 JOptionPane.showMessageDialog(null, "Falha. O arquivo contém um tesauro.\nEscolha um arquivo apropriado que contenha dicionário.", "Erro", JOptionPane.ERROR_MESSAGE);
			 return null;
		}
		return d;
	}
	public Tesauro abrirTesauro(String nome)
	{
		try 
		{
			in = new ObjectInputStream(new FileInputStream(nome));
			d = (Dicionario) in.readObject();
			t = (Tesauro) in.readObject();
			in.close();
		}
		catch (FileNotFoundException fnfe)
		{
			JOptionPane.showMessageDialog(null, "Arquivo não localizado.", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch (ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null, "Classes nao encontradas.", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch (ClassCastException ref)
		{
			 JOptionPane.showMessageDialog(null, "Falha. O arquivo contém um dicionário.\nEscolha um arquivo apropriado que contenha dicionário.", "Erro", JOptionPane.ERROR_MESSAGE);
			 return null;
		}
		return t;
	}

}




public class DicionarioTesauro6411927 extends JFrame implements ActionListener, java.io.Serializable
{
	private static final long serialVersionUID = -5973219035249636057L;
	
	private int moduloSelecionado = 1; //define o comportamento do programa: 1-dicionário, 2-tesauro
	//elementos da janela principal
	private JTextField termoBuscado;
	private JTextArea instrucoes, atalhos;
	private JLabel titulo;
	private JRadioButton inserir, remover, buscar, inserirSignificados;
	private ButtonGroup grupoDeOpcoes, grupoModulos;
	private JMenuBar barraDeMenus = new JMenuBar();
	private JRadioButtonMenuItem rDicionario, rTesauro;
	
	private int opcaoEscolhida;  //determina qual funcao será utilizada em conjunto com o radioButton
	
	//elementos utilizados na insersão e visualização de significados
	JTextField significado0 = new JTextField();
	JTextField significado1 = new JTextField();
	JTextField significado2 = new JTextField();
	JTextField significado3 = new JTextField();
	JTextField significado4 = new JTextField();
	JTextField sinonimos = new JTextField();
	JTextField antonimos = new JTextField();
	JTextField relacionados = new JTextField();
	
	
	//inicialização das variáveis
	//private Tesauro w = new Tesauro();
	private Dicionario d = new Dicionario(); 
	private Tesauro t = new Tesauro();
	private TermoDicionario termoDic = null;
	private TermoTesauro termoTes = null;
	private String palavra;
	
	//inicialização do ActionHandler e do ItemHandler
	TrataAcoes trataAcoes = new TrataAcoes();
	TrataOpcoes trataOpcoes = new TrataOpcoes();
	
	
	//rotinas para a leitura e gravação de arquivo
	//SeriadorDicionario sd = new SeriadorDicionario();
	//SeriadorTesauro st = new SeriadorTesauro();
	Serializador s = new Serializador();
	JFileChooser fc;



	//método principal
	public DicionarioTesauro6411927()
	{
		//super("Terceiro Exercício-Programa: 6411927");
		
		//códigos da barra de menus e seus sub-itens
		//criação da barra de menus e atribuição de atalhos de teclado para os itens
		setJMenuBar(barraDeMenus);
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic(KeyEvent.VK_A);
		JMenu menuModulos = new JMenu("Módulos");
		menuModulos.setMnemonic(KeyEvent.VK_M);
		JMenu menuSobre = new JMenu("Sobre");
		menuSobre.setMnemonic(KeyEvent.VK_S);
		
		
		//sub-menus com suas funções abrir, salvar e sair
		JMenuItem abrir = new JMenuItem("Abrir");
		JMenuItem salvar = new JMenuItem("Salvar");
		
		//cria selecionador de arquivo e define a maneira de mostrar o diálogo
		//cria internamente objetos para lidar com as ações abrir e salvar em arquivo
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			abrir.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent e)
			{
					int returnVal = fc.showOpenDialog(DicionarioTesauro6411927.this);
					
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						File file = fc.getSelectedFile();
						d = s.abrirDicionario(file.getAbsolutePath());
						t = s.abrirTesauro(file.getAbsolutePath());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Cancelado pelo usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
					}

				}
			});

			salvar.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent e)
			{

					int returnVal = fc.showSaveDialog(DicionarioTesauro6411927.this);
					
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						File file = fc.getSelectedFile();
						//executa o serializador.

							s.salvar(d, t, file.getAbsolutePath());
							JOptionPane.showMessageDialog(null, "Dicionário gravado em\n"+ file.getAbsolutePath() +".", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

					}

				}
			});
			
		//Sair...
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		
		//Nesta parte fica o seletor de módulo
		rDicionario = new JRadioButtonMenuItem("Dicionário", true);
		rTesauro = new JRadioButtonMenuItem("Tesauro", false);
		
		grupoModulos = new ButtonGroup();
		grupoModulos.add(rDicionario);
		grupoModulos.add(rTesauro);
		//tratador dos radio buttons que selecionam os módulos
		rDicionario.addItemListener(trataOpcoes);
		rTesauro.addItemListener(trataOpcoes);
		
		
		//Códigos do menu "sobre"
		JMenuItem sobre = new JMenuItem("Aluno...");
		sobre.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String disciplina = "ACH 2002 - Introdução a Ciência da Computação II\nEACH-USP - Segundo Semestre de 2008";
			    String turma = "Turma 02 - Matutino";
			    String professor = "Prof. Delano Medeiros Beder";
				String programa = "Terceiro Exercício-Programa: Dicionário e Tesauro";
				String autor = "Murilo Galvão Honório";
				String nUSP = "nº USP 6411927";
				String data = "Entrega: 05/12/2008";
				String extra = "\nTestado com Windows XP SP3, IDE Elipse 3.4.1, JDK 1.6.0_07 ";

				JOptionPane.showMessageDialog(null, disciplina+"\n\n"+turma+"\n"+professor+"\n"+programa+"\n\n"+autor+" - "+nUSP+"\n"+data+extra, "Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		//completa a construção da barra de menus, adicionando os itens a seus menus e à barra
		menuArquivo.add(abrir);
		menuArquivo.add(salvar);
		menuArquivo.addSeparator();
		menuArquivo.add(sair);
		menuModulos.add(rDicionario);
		menuModulos.add(rTesauro);
		menuSobre.add(sobre);
		barraDeMenus.add(menuArquivo);
		barraDeMenus.add(menuModulos);
		barraDeMenus.add(menuSobre);
		
		
		
		//Início do trecho da janela principal
		//Mostra o título do módulo, ao abrir arquivo mostra o nome
		titulo = new JLabel(moduloSelecionado == 1 ? "Dicionário" : "Tesauro", SwingConstants.CENTER);
		titulo.setFont(new Font("SERIF", Font.BOLD, 20));
		
		//Campos de texto com instruções e atalhos
		JPanel painelAjuda = new JPanel();
		painelAjuda.setLayout(new GridLayout(0,2,0,0));

		instrucoes = new JTextArea(5, 16);
		instrucoes.append("Clique na a opção desejada\nou use as teclas indicadas,\ndigite a palavra e tecle ENTER.\n\nAlterne entre Dicionário e\nTesauro no menu módulos.");
		instrucoes.setEditable(false);
		instrucoes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Instruções de uso:"));

		atalhos = new JTextArea(5, 16);
		atalhos.append("Inserir: Alt+I\nRemover: Alt+R\nBuscar: Alt+B\nModificar, adicionar: Alt+P\nAbrir ou salvar arquivo: Alt+A\nAlternar entre módulos: Alt+M");
		atalhos.setEditable(false);
		atalhos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Teclas de atalho:"));
		

		//constrói campo com o termo a ser buscado
		termoBuscado = new JTextField("Selecione uma opção e digite o termo desejado.");
		//ativação do tratador de eventos da interação do usuário via teclado, na janela principal
		termoBuscado.addActionListener(trataAcoes);
		
		
		//contrói os campos com as opções
		inserir = new JRadioButton("Inserir palavra", false);
		remover = new JRadioButton("Remover palavra", false);
		buscar = new JRadioButton("Palavra: buscar e exibir significados", false);
		if (moduloSelecionado == 1)
			inserirSignificados = new JRadioButton("Palavra: adicionar ou modificar os significados", false);
		else
			inserirSignificados = new JRadioButton("Termo: adicionar ou modificar sinônimos, contrastantes ou relacionados, ", false);


		inserir.addItemListener(trataOpcoes);
		remover.addItemListener(trataOpcoes);
		buscar.addItemListener(trataOpcoes);
		inserirSignificados.addItemListener(trataOpcoes);

		//define atalhos de teclado das opções
		inserir.setMnemonic(KeyEvent.VK_I);
		remover.setMnemonic(KeyEvent.VK_R);
		buscar.setMnemonic(KeyEvent.VK_B);
		inserirSignificados.setMnemonic(KeyEvent.VK_P);
		
		//cria relacionamento entre o grupo de opções
		grupoDeOpcoes = new ButtonGroup();
		grupoDeOpcoes.add(inserir);
		grupoDeOpcoes.add(remover);
		grupoDeOpcoes.add(buscar);
		grupoDeOpcoes.add(inserirSignificados);
		
		//cria um painel próprio para adicionar as opções e o campo de texto digitável
		JPanel menuDeOpcoes = new JPanel(new GridLayout(0, 1));
		menuDeOpcoes.add(inserir);
		menuDeOpcoes.add(remover);
		menuDeOpcoes.add(buscar);
		menuDeOpcoes.add(inserirSignificados);
		menuDeOpcoes.add(termoBuscado, BorderLayout.CENTER);
		menuDeOpcoes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Ações:"));

		

		
		//comandos finais para definir o foco, deixando a interface gráfica mais prática
		titulo.setFocusable(false);
		instrucoes.setFocusable(false);
		atalhos.setFocusable(false);
		inserir.setFocusable(false);
		remover.setFocusable(false);
		buscar.setFocusable(false);
		inserirSignificados.setFocusable(false);
		//o único elemento que vai requerer foco e ficar sempre selecionado é a caixa de texto 'termoBuscado'
		termoBuscado.requestFocus();
		termoBuscado.selectAll();
		
		
		//comandos para a criação da interface gráfica principal
    	JFrame janela = new JFrame("Terceiro Exercício-Programa: 6411927");
    	
		painelAjuda.add(instrucoes);
		painelAjuda.add(atalhos);
    	
    	JPanel main = new JPanel(new BorderLayout());
		main.add(painelAjuda, BorderLayout.CENTER);
		main.add(titulo, BorderLayout.NORTH);
		main.add(menuDeOpcoes, BorderLayout.SOUTH);

        janela.add(main, BorderLayout.SOUTH);
        janela.add(barraDeMenus, BorderLayout.NORTH);

        //redimensiona a janela para o tamanho adequado
        janela.pack();
        janela.setVisible(true);
		

	}
    
    
	//classe interna para o tratamento das ações
	private class TrataAcoes implements ActionListener, java.io.Serializable
	{
		private static final long serialVersionUID = 7179822213437096842L;

		public void actionPerformed(ActionEvent e)
		{

			//lida com as interações do dicionário e do tesauro
			switch (moduloSelecionado)
			{
			case 1:
				if (e.getSource() == termoBuscado)
				{
					termoBuscado.requestFocus();
					termoBuscado.selectAll();
					palavra = e.getActionCommand();
					termoDic = (TermoDicionario) d.busca(palavra);
					if (opcaoEscolhida == 1)
					{
						if (termoDic == null)
						{
							TermoDicionario i = new TermoDicionario(palavra);
							d.insere(i.getTermo(), i);
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' foi inserido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' já existe. Se deseja inserir significados, por favor selecione a opção apropriada.", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
							
					}
					else if (opcaoEscolhida == 2)
					{
						if (termoDic == null)
						{
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' não consta no dicionário", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
						else
						{
							d.remove(palavra);
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' foi removido do dicionário.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if (opcaoEscolhida == 3)
					{
						if (termoDic != null)
						{
							moduloSignificados(false);
							break;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' não consta no dicionário.", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}	
					}
					else if (opcaoEscolhida == 4)
					{
						if (termoDic != null)
						{
							moduloSignificados(true);
							break;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' não consta no dicionário.", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Selecione uma opção antes de confirmar.", "Erro", JOptionPane.ERROR_MESSAGE);
						break;
					}

				}
				break;
			case 2:
				if (e.getSource() == termoBuscado)
				{
					termoBuscado.requestFocus();
					termoBuscado.selectAll();
					palavra = e.getActionCommand();
					termoTes = (TermoTesauro) t.busca(palavra);
					if (opcaoEscolhida == 1)
					{
						if (termoTes == null)
						{
							TermoTesauro i = new TermoTesauro(palavra);
							t.insere(palavra, i);
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' foi inserido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "A palavra '" + palavra + "' já existe. Se deseja inserir termos, por favor selecione a opção apropriada.", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					else if (opcaoEscolhida == 2)
					{
						if (termoTes == null)
						{
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' não consta no tesauro", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
							else
						{
							t.remove(palavra);
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' foi removido do tesauro.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else if (opcaoEscolhida == 3)
					{
						if (termoTes != null)
						{
							moduloTermos(false);
							break;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' não consta no tesauro.", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					else if (opcaoEscolhida == 4)
					{
						if (termoTes != null)
						{
							moduloTermos(true);
							break;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "O termo '" + palavra + "' não consta no tesauro.", "Erro", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Selecione uma opção antes de confirmar.", "Erro", JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
			}
			
			if (e.getSource() == significado0)
			{   
				d.insereSignificados(termoDic.getTermo(), e.getActionCommand(), 0);
				JOptionPane.showMessageDialog(null, "Significado (1) foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				significado0.selectAll();
			}
			if (e.getSource() == significado1)
			{   
				d.insereSignificados(termoDic.getTermo(), e.getActionCommand(), 1);
				JOptionPane.showMessageDialog(null, "Significado (2) foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				significado1.selectAll();
			}
			if (e.getSource() == significado2)
			{   
				d.insereSignificados(termoDic.getTermo(), e.getActionCommand(), 2);
				JOptionPane.showMessageDialog(null, "Significado (3) foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				significado2.selectAll();
			}
			if (e.getSource() == significado3)
			{   
				d.insereSignificados(termoDic.getTermo(), e.getActionCommand(), 3);
				JOptionPane.showMessageDialog(null, "Significado (4) foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				significado3.selectAll();
			}
			if (e.getSource() == significado4)
			{   
				d.insereSignificados(termoDic.getTermo(), e.getActionCommand(), 4);
				JOptionPane.showMessageDialog(null, "Significado (5) foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				significado4.selectAll();
			}
			if (e.getSource() == sinonimos)
			{
				t.insereSignificados(termoTes.getTermo(), e.getActionCommand(), 0);
				JOptionPane.showMessageDialog(null, "O campo de termos sinônimos foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				sinonimos.selectAll();
			}
			if (e.getSource() == antonimos)
			{
				t.insereSignificados(termoTes.getTermo(), e.getActionCommand(), 1);
				JOptionPane.showMessageDialog(null, "O campo de termos contrastantes foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				antonimos.selectAll();
			}
			if (e.getSource() == relacionados)
			{
				t.insereSignificados(termoTes.getTermo(), e.getActionCommand(), 2);
				JOptionPane.showMessageDialog(null, "O campo de termos relacionados foi modificado.", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
				relacionados.selectAll();
			}
		}
	}


	//classe interna para o tratamento das opcoes
	private class TrataOpcoes implements ItemListener, java.io.Serializable
	{
		private static final long serialVersionUID = -3072316669409924933L;

		public void itemStateChanged(ItemEvent e)
		{
			//define quais as funcoes ativas
			if (e.getSource() == rDicionario)
			{
				moduloSelecionado = 1;
				titulo.setText("Dicionário");
				remover.doClick(); //maneira de fazer com qua a alternância não prejudique a expreiência do usuário :[ me perdoa, são 4 da manhã...
				termoBuscado.selectAll();
			}
			if (e.getSource() == rTesauro)
			{
				moduloSelecionado = 2;
				titulo.setText("Tesauro");
				remover.doClick();
				termoBuscado.requestFocus();
				termoBuscado.selectAll();
			}
			
			
			if (e.getSource() == inserir)
			{	
				opcaoEscolhida = 1;
				termoBuscado.setText("Digite a palavra a inserida.");
				termoBuscado.requestFocus();
				termoBuscado.selectAll();
			}
			else if (e.getSource() == remover)
			{	
				opcaoEscolhida = 2;
				termoBuscado.setText("Digite a palavra a ser removida.");
				termoBuscado.requestFocus();
				termoBuscado.selectAll();
			}
			else if (e.getSource() == buscar)
			{	
				opcaoEscolhida = 3;
				termoBuscado.setText("Digite a palavra que deseja procurar.");
				termoBuscado.requestFocus();
				termoBuscado.selectAll();
			}
			else if (e.getSource() == inserirSignificados)
			{	
				opcaoEscolhida = 4;
				if (moduloSelecionado == 1)
					termoBuscado.setText("Digite a palavra cujos significados deseja modificar.");
				else
					termoBuscado.setText("Digite a palavra cujos termos deseja modificar.");
				termoBuscado.requestFocus();
				termoBuscado.selectAll();
			}
			else
			{
				opcaoEscolhida = -1;
			}
		}
		
	}

    void moduloSignificados(boolean editavel)
    {
    	boolean edita = editavel;
    	

    	
    	JFrame frame = new JFrame(edita ? "Modo de edição" : "Modo de consulta");
    	
    	JPanel main = new JPanel(new BorderLayout());

		JPanel num = new JPanel();
		JPanel signi = new JPanel();
		JPanel juntos = new JPanel();
		
		
		num.setLayout(new GridLayout(5,0,0,0));
		signi.setLayout(new GridLayout(5,0,0,0));
		juntos.setLayout(new BorderLayout());
		
        JLabel[] numeros = new JLabel[ 5 ];
        
        
        //cria 5 campos de texto, exibe em branco caso nao tenha nenhuma string armazenada
        significado0 = new JTextField(termoDic.getSignificado(0) == null ? "" : termoDic.getSignificado(0), 30);
        significado1 = new JTextField(termoDic.getSignificado(1) == null ? "" : termoDic.getSignificado(1), 30);
        significado2 = new JTextField(termoDic.getSignificado(2) == null ? "" : termoDic.getSignificado(2), 30);
        significado3 = new JTextField(termoDic.getSignificado(3) == null ? "" : termoDic.getSignificado(3), 30);
        significado4 = new JTextField(termoDic.getSignificado(4) == null ? "" : termoDic.getSignificado(4), 30);
        signi.add(significado0);
        signi.add(significado1);
        signi.add(significado2);
        signi.add(significado3);
        signi.add(significado4);
        significado0.addActionListener(trataAcoes);
        significado1.addActionListener(trataAcoes);
        significado2.addActionListener(trataAcoes);
        significado3.addActionListener(trataAcoes);
        significado4.addActionListener(trataAcoes);
        
        JLabel palavra = new JLabel(termoDic.getTermo(), SwingConstants.CENTER);
		palavra.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 24));
        
        for(int i = 0; i < numeros.length; i++)
        {
        	numeros[i] = new JLabel("(" + (i+1) + ")");
        	numeros[i].setPreferredSize(numeros[i].getMinimumSize());
        	num.add(numeros[i]);
        }
    	//para reutilizar na visualização e edição
    	if(!edita)
    	{
    		significado0.setFocusable(false);
    		significado1.setFocusable(false);
    		significado2.setFocusable(false);
    		significado3.setFocusable(false);
    		significado4.setFocusable(false);
    		significado0.setEditable(false);
    		significado1.setEditable(false);
    		significado2.setEditable(false);
    		significado3.setEditable(false);
    		significado4.setEditable(false);
    		palavra.requestFocus();
    	}

		juntos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Significados:"));
        JLabel ajuda = new JLabel("Digite ou apague a frase desejada e tecle ENTER em seguida para alterar.", SwingConstants.CENTER);
        juntos.add(num, BorderLayout.WEST);
        juntos.add(signi, BorderLayout.EAST);
        if (edita)
        	juntos.add(ajuda, BorderLayout.SOUTH);
        main.add(palavra, BorderLayout.NORTH);
        main.add(juntos, BorderLayout.SOUTH);
        frame.add(main);

        //redimensiona a janela para o tamanho adequado
        frame.pack();
        frame.setVisible(true);
    }
    
    
    
    
    //Método para a edição dos termos
    void moduloTermos(boolean editavel)
    {
    	boolean edita = editavel;
    	

    	//define o título da janela de acordo com a opção utilizada
    	JFrame frame = new JFrame(edita ? "Modo de edição" : "Modo de consulta");
    	
    	JPanel main = new JPanel(new BorderLayout());

		JPanel nomeT = new JPanel(); //painel para organizar os nomes dos termos
		JPanel term = new JPanel();
		JPanel juntos = new JPanel();
		
		
		nomeT.setLayout(new GridLayout(3,0,0,0));
		term.setLayout(new GridLayout(3,0,0,0));
		juntos.setLayout(new BorderLayout());
		
        JLabel[] nomesT = new JLabel[3];
    	nomesT[0] = new JLabel("Sinônimos:");
    	nomesT[1] = new JLabel("Contrastantes:");
    	nomesT[2] = new JLabel("Relacionados:");
        
        
        //cria 3 campos de texto, exibe em branco caso nao tenha nenhuma string armazenada
        sinonimos = new JTextField(termoTes.getTermos(0) == null ? "" : termoTes.getTermos(0), 30);
        antonimos = new JTextField(termoTes.getTermos(1) == null ? "" : termoTes.getTermos(1), 30);
        relacionados = new JTextField(termoTes.getTermos(2) == null ? "" : termoTes.getTermos(2), 30);
        term.add(sinonimos);
        term.add(antonimos);
        term.add(relacionados);
        sinonimos.addActionListener(trataAcoes);
        antonimos.addActionListener(trataAcoes);
        relacionados.addActionListener(trataAcoes);

        //mostra a palavra com letra diferente
        JLabel palavra = new JLabel(termoTes.getTermo(), SwingConstants.CENTER);
		palavra.setFont(new Font("SERIF", Font.BOLD+Font.ITALIC, 24));
        
        for(int i = 0; i < nomesT.length; i++)
        {
        	nomesT[i].setPreferredSize(nomesT[i].getMinimumSize());
        	nomeT.add(nomesT[i]);
        }
        
    	if(!edita)
    	{
    		sinonimos.setFocusable(false);
    		antonimos.setFocusable(false);
    		relacionados.setFocusable(false);
    		sinonimos.setEditable(false);
    		antonimos.setEditable(false);
    		relacionados.setEditable(false);
    		palavra.requestFocus();
    	}

		juntos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Termos:"));
        JLabel ajuda = new JLabel("Digite ou apague a frase desejada e tecle ENTER em seguida para alterar.", SwingConstants.CENTER);
        juntos.add(nomeT, BorderLayout.WEST);
        juntos.add(term, BorderLayout.EAST);
        if (edita)
        	juntos.add(ajuda, BorderLayout.SOUTH);
        
        main.add(palavra, BorderLayout.NORTH);
        main.add(juntos, BorderLayout.SOUTH);
        frame.add(main);

        //redimensiona a janela para o tamanho adequado
        frame.pack();
        frame.setVisible(true);
    }
	
	//método principal do programa
	public static void main(String[] args)
	{
		DicionarioTesauro6411927 app = new DicionarioTesauro6411927();
		
		app.addWindowListener(
				new WindowAdapter() 
				{
					public void windowClosing( WindowEvent e)
					{
						System.exit(0);
					}
				}
		);
	}
	public void actionPerformed(ActionEvent e) {}	
}