import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

class GPS{ 

	public static Vertice[] v;		//Cria um array de objetos do tipo Verice, para armazenar os vertices do grafo, carregados do arquivo de entrada
	public static Aresta[] a;		//Cria um array de objetos do tipo Aresta, para armazenar as arestas do grafo, carregadas do arquivo de entrada
	public static int origem;		//Cria uma variavel para armazenar a origem do trajeto
	public static int destino;		//Cria uma variavel para armazenar o destino do trajeto
	public static Pilha<Integer> p = new Pilha<Integer>();		//Cria um objeto do tipo Pilha, para armazenar os vertices do caminho de custo minimo 
	public static double custo;		//Cria uma variavel para armazenar o custo do caminho minimo entre a origem e o destino

	public static void main(String[] args){		//Metodo principal do programa, executa todas as acoes para encontrar o caminho minimo e salva-lo em um arquivo de saida
		carrega();		//executa o metodo que carrega as requisicoes do arquivo de entrada e as armazena nas variaveis globais

		Grafo gr = new Grafo(v.length);			//Cria um objeto do tipo Grafo, para criar um grafo com os vertices e arestas carregados
		gr.insereTodasArestas(a);				//insere todos as arestas no grafo representado por gr

		Dijkstra d = new Dijkstra(gr);			//Cria um objeto do tipo Dijkstra, para calcular o caminho de custo minimo do grafo gr
		d.calculaRota(origem);					//executa o metodo que realmente calcula a melhor rota para todos os vertices, partindo da origem do trajeto requerido

		for(int i = destino; i != -1; i = d.ant[i]){	//loop para inserir os vertices por onde passa o trajeto(do destino para a origem)
			p.push(i);										//insere o vertice i na pilha de antecessores
		}

		custo = d.custoMin[destino];			//armazena na variavel global o custo da melhor rota para o problema

		imprimeArquivo();						//executa o metodo que imprime no arquivo o padrao de saida
	}

	public static void carrega(){		//Metodo que carrega as requisicoes do arquivo de entrada e as armazena nas variaveis globais
		BufferedReader leitor = null;			//cria uma variavel para receber o fluxo de caracteres
		try{
			leitor = new BufferedReader(new FileReader("input.txt"));	//cria um fluxo de caracteres para ler o conteudo do arquivo "input.txt"
			String texto = leitor.readLine();	//cria uma variavel do tipo String para armazenar uma linha do arquivo de entrada
			String[] separado = texto.split(" ");	//cria um array de Strings que recebe o resultado da separacao, no caracter " ", da linha lida acima

			int numVert = Integer.parseInt(separado[0]);		//variavel recebe o numero de vertices contido na entrada
			int numAresta = Integer.parseInt(separado[1]);		//variavel recebe o numero de aretas contido na entrada

			v = new Vertice[numVert];		//aloca dinamicamente o tamanho do vetor de objetos do tipo Vertice com o tamanho numVert
			a = new Aresta[numAresta];		//aloca dinamicamente o tamanho do vetor de objetos do tipo Aresta com o tamanho numAresta

			texto = leitor.readLine();		//le a proxima linha(linha vazia)
			for(int i = 0; i < numVert; i++){	//cria o loop que sera executado enquanto nao forem lidos todos os vertices
				texto = leitor.readLine();			//le a proxima linha
				separado = texto.split(" ");		//atribui ao array o resultado da separacao, no caracter " ", da linha lida acima
				v[Integer.parseInt(separado[0])] = new Vertice(Integer.parseInt(separado[0]), Integer.parseInt(separado[1]), Integer.parseInt(separado[2])); //cria um objeto na posição i do vetor de Vertices
			}

			texto = leitor.readLine();		//le a proxima linha(linha vazia)
			for(int i = 0; i < numAresta; i++){		//cria o loop que sera executado enquanto nao forem lidas todas as arestas
				texto = leitor.readLine();			//le a proxima linha
				separado = texto.split(" ");		//atribui ao array o resultado da separacao, no caracter " ", da linha lida acima
				a[i] = new Aresta(Integer.parseInt(separado[0]), Integer.parseInt(separado[1])); //cria um objeto na posição i do vetor de Arestas
				a[i].calculaPeso(v[a[i].vInicio], v[a[i].vFim]); //executa o metodo para calcular o peso da aresta inserida
			}

			texto = leitor.readLine();		//le a proxima linha(linha vazia)
			
			texto = leitor.readLine();		//le a proxima linha
			separado = texto.split(" ");	//atribui ao array o resultado da separacao, no caracter " ", da linha lida acima
			
			origem = Integer.parseInt(separado[0]);		//atribui o valor do vertice de origem
			destino = Integer.parseInt(separado[1]);	//atribui o valor do vertice de destino
		} catch (Exception e){		//caso seja lançada alguma excecao, trata 
			System.out.println(e);	//imprimindo na tela o erro de execucao
		} finally {				//por fim, tendo lancado excecao ou nao, 
			try{
				leitor.close();		//fecha o arquivo
			} catch(Exception e){		//caso seja lançada alguma excecao,
				System.out.println(e);	//imprime na tela o erro de execucao
			}
		}
	}

	public static void imprimeArquivo(){		//Metodo que imprime, o resultado da requisicao do arquivo de entrada, no arquivo de saida
		Formatter form = null;			//cria uma nova variavel do tipo Formatter para imprimir no arquivo em um certo formarto
		try {
			form = new Formatter(new FileWriter("saida.txt"));	//efetivamente cria a instancia da classe Formatter

			int vert = p.pop();		//retira da pilha de vertices o ultimo inserido, ou seja a origem
			while(!p.isEmpty()){		//enquanto a pilha nao estiver vazia:
				form.format("%d ", vert);	//imprime o vertice atual

				vert = (int) p.pop();		//retira da pilha o proximo vertice

				form.format("%d\r\n", vert);	//imprime o novo vertice e quebra a linha
			}

			form.format("\r\n%f", custo);		//ao fim, imprime o custo do caminho
		} catch (IOException ex) {		//caso o código lance alguma excessao, trata
			System.err.println("Nao foi possivel abrir o arquivo. " + ex);	//imprimindo na tela o erro
		} finally{			//por fim,
			form.close();	//fecha a instancia do Formatter junto com o arquivo
		}
	}
}