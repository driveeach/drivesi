import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


class Dijkstra {
	static int contador=0;
	public static void main(String[] args) {
		
		int numVertices=0;
		int numArestas=0;
		String chave = "";
		FileReader f;
		BufferedReader arquivo;
		double [][] m_ = new double [numVertices][numVertices];
		int [][] m_coordenadas = new int [numVertices][3];
		for ( int a = 0; a < numVertices; a++ ){
			for ( int b = 0;b < numVertices; b++ ){
				m_[a][b] = 0;
			}
		}
		try {
			f = new FileReader( "input.txt" );
			arquivo = new BufferedReader( f );
			chave = arquivo.readLine();
			String[] array = chave.split(" ");
			numVertices = Integer.parseInt( array[0] );
			numArestas = Integer.parseInt( array[1] );
			m_ = new double [numVertices][numVertices];
			m_coordenadas = new int [numVertices][3];
			chave = arquivo.readLine();
			chave = arquivo.readLine();
			
			for ( int i = 0; i < numVertices; i++) {
				String[] coordenadas = chave.split( " " );
				coordenada (m_coordenadas, Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]), Integer.parseInt(coordenadas[2]));
				chave = arquivo.readLine();
			}
			for ( int i = 0; i < numArestas; i++) {
				String[] arestas = chave.split( " " );
				matriz (m_, Integer.parseInt(arestas[0]), Integer.parseInt(arestas[1]));
				chave = arquivo.readLine();
			}
			chave = arquivo.readLine(); 
			String[] origem_destino = chave.split( " " );
			int origem = Integer.parseInt( origem_destino[0] );
			int destino = Integer.parseInt( origem_destino[1] );
		}
		catch ( IOException e ) {
			e.printStackTrace();
		}
		/*
		System.out.println ();
		for ( int a = 0; a < numVertices; a++ ){
			for ( int b = 0;b < 3; b++ ){
				System.out.print (m_coordenadas[a][b]);
				System.out.print (" ");
			}
			System.out.println ();
		}
		
		System.out.println ();
		for ( int a = 0; a < numVertices; a++ ){
			for ( int b = 0;b < numVertices; b++ ){
				System.out.print (matriz_custo[a][b]+" ");
				System.out.print ("");
			}
			System.out.println ();
		}
		*/
		
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();
			
			for ( int a = 0; a < numVertices; a++ ){
				Vertice v = new Vertice (m_coordenadas[a][0], m_coordenadas[a][1], m_coordenadas[a][2]);
				vertices.add(v);
			
			}		
		int x_a=0;
		int x_b=0;
		int y_a=0;
		int y_b=0;
		for (int a = 0; a < numVertices; a++ ){
			for (int b = 0;b < numVertices; b++ ){
				if (m_[a][b]==1.0){
					for (int c = 0; c < numVertices; c++ ){
						if (m_coordenadas[c][0]==a){
							x_a=m_coordenadas[c][1];
							y_a=m_coordenadas[c][2];
						}
					}
					for (int c = 0; c < numVertices; c++ ){
						if (m_coordenadas[c][0]==b){
							x_b=m_coordenadas[c][1];
							y_b=m_coordenadas[c][2];
						}
					}
					m_[a][b]= Math.sqrt((x_a-x_b)*(x_a-x_b)+(y_a-y_b)*(y_a-y_b));
				}
			}
			
		}
		
		for (int a = 0; a < numVertices; a++ ){
			for (int b = 0;b < numVertices; b++ ){
				System.out.print (m_[a][b]+" ");
				System.out.print ("");
			}
			System.out.println ();
		}
		
	}
	static void matriz (double [][] m, int vertice_a, int vertice_b){
		m[vertice_a][vertice_b] = 1.0;
		m[vertice_b][vertice_a] = 1.0;
	}
	
	static void coordenada (int [][] coordenadas, int vertice, int x, int y){
		coordenadas [contador][0] = vertice;
		coordenadas [contador][1] = x;
		coordenadas [contador][2] = y;
		contador++;
	}
	static double calculaPeso(Vertice a, Vertice b){
		double peso;
		peso = Math.sqrt((b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y));
		return peso;
	}
}