/*   
* Definir uma maneira de indicar o estado inicial dos navios dos jogadores;
    * Exibir todos os movimentos dos jogadores, informando se os ataques foram bem sucedidos ou não;
    * Informar quando um navio é atingido e quando ele é afundado;
    * Exibir ao final do jogo um mapa final do posicionamento final dos navios dos jogadores.*/
import java.util.Scanner;
class BatalhaNaval {
	static int [][] tabuleiro = new int [10][10];
	static Scanner dados = new Scanner(System.in);
	public static void atirar (){
		System.out.println ("Digite as coordenadas do alvo:");
		int x = dados.nextInt();
		int y = dados.nextInt();
		int tipoNavio = -1;
		if (tabuleiro[x][y] != 0) {
			tipoNavio = tabuleiro[x][y];
			tabuleiro[x][y] = -1;
			System.out.println ("Você acertou o navio");
		}
		if (tipoNavio != -1 ){
			for (int i=0; i<tabuleiro.length; i++){
				for (int j=0; j<tabuleiro.length; j++){
					if (tabuleiro[i][j] == tipoNavio)
						return;
				}
			}
			System.out.println ("O navio afundou!");
		}
	}	
	public static void imprimir (){
		for (int i=0; i<tabuleiro.length; i++){
			for (int j=0; j<tabuleiro.length; j++){
				System.out.print (tabuleiro[i][j]+" "); 
 			}
			System.out.println ();
		}	
	}
	public static boolean preencher () {
		System.out.println ("Digite o navio desejado:");
		
		int navio = dados.nextInt();
		System.out.println ("Digite as coordenadas:");
		int x = dados.nextInt();
		int y = dados.nextInt();
		System.out.println ("Digite a orientacao do seu navio:");
		System.out.println ("h para horizontal ou v para vertical.");
		String orientacao = dados.next();
		if(orientacao.equals ("h") && y+navio<=tabuleiro.length){
			for (int i=0;i<navio;i++){
				tabuleiro[x][y+i] = navio;
			}
		}
		else {
			if(orientacao.equals ("v") && x+navio<=tabuleiro.length){
				for (int i=0;i<navio;i++){
					tabuleiro[x+i][y] = navio;
				} 
			}	
			else 	{
				System.err.println("invalido");
				return false;
			}
		}
		return true;
	}
	public static void main (String [] args) {
		int portaAvioes = 5;
		int encouracado = 4;
		int destroyer = 3;
		int barco = 2;
		int submarino = 1;
		for (int i = 0; i<5; i++) {
			if(!preencher()) i--;
		}
		imprimir();
		for (int i = 0; i<11; i++) {
			atirar();	
			imprimir();	
		}
	}
}