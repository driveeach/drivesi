/**
 * 
 */
package myTestFiles;
import java.io.*;
/**
 * @author chaim
 *
 */
public class ReadCaracteres {

	/**
	 * 
	 */
	public ReadCaracteres() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static void exemplo(String origem, String destino) throws IOException {
	      File arq_entrada = new File(origem);
	      File arq_saida = new File(destino);

	      FileReader entrada = new FileReader(arq_entrada);
	      FileWriter saida = new FileWriter(arq_saida);

	      int c;
	      // -1 indica final de arquivo de caracteres
	      while ((c = entrada.read()) != -1)
		           saida.write(c);
	      entrada.close();
	      saida.close();
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
	    try{
		FileReader entrada = new FileReader("readme.txt");

	   	   while (true){
	        		i = entrada.read();
	        		if ( i == -1 ) break;
	                char c = (char) i; 
	                System.out.print( c );  
					}
		   System.out.println("*** Acabou ***");  
		    entrada.close();
		    exemplo("readme.txt","readme1.txt");
		    criaTabela();
		    leTabela();
		}
	    catch (IOException e){
	      System.err.println(e); 
	    }
	}
	
	static void leTabela() throws IOException
	{
	double preco; 
	int quantidade;
	String descricao="";
	char c;
		DataInputStream entrada =	new DataInputStream(new FileInputStream("precos.bin"));
		try {   
			System.out.println("Desc.\tQtd\tpreco:");
			while (true) {
		        	preco = entrada.readDouble();
		        	entrada.readChar();       // despreza o tab
		        	quantidade = entrada.readInt();
		        	entrada.readChar();       // despreza o tab
		        	while ((c=entrada.readChar()) != '\n')
		        		descricao+=c;
		        	System.out.printf("%s\t%d\t%s\n",descricao, quantidade, preco);
		        	descricao = "";
			}    
		} catch (EOFException e) { // fim de arquivo }
		entrada.close();
		}
	}
		
	static void criaTabela() throws IOException {
		double precos[]= {0.69, 1.54, 6.99};
		int quantidades[] = {12, 2, 1};
		String descricoes[] = {"sabao", "musli", "cafe"};
		File arquivo = new File("precos.bin");
			DataOutputStream saida =	
				new DataOutputStream(new FileOutputStream(arquivo));
			for (int i = 0; i < precos.length; i ++) {
			    saida.writeDouble(precos[i]);
				saida.writeChar('\t');
				saida.writeInt(quantidades[i]);
				saida.writeChar('\t');
				saida.writeChars(descricoes[i]);
				saida.writeChar('\n');
			}
			saida.close();
	}
}
