package ep2_2012;


public class ExecutaTestes {
	/* mude esta variavel para 2 ou 3 para executar
	   testes aleatorios mais complexos             */
	static int CONJUNTO_DE_TESTE = 1;

	public static void main(String[] args){

		if (CONJUNTO_DE_TESTE==1){
			testaExemplo(mapa1(),0,1);
			testaExemplo(mapa1(),1,0);
			testaExemplo(mapa2(),0,2);
			testaExemplo(mapa2(),2,0);
			testaExemplo(mapa2(),1,2);
			testaExemplo(mapa2(),2,1);
			testaExemplo(mapa2(),0,1);
			testaExemplo(mapa2(),1,0);
			testaExemplo(mapa3(),0,2);
			testaExemplo(mapa3(),0,3);
			testaExemplo(mapa3(),2,0);
			testaExemplo(mapa4(),0,4);
			testaExemplo(mapa4(),0,1);
			testaExemplo(mapa4(),1,4);
			testaExemplo(mapa5(),0,4);
			testaExemplo(mapa5(),1,2);
			testaExemplo(mapa5(),0,1);
			testaExemplo(mapa6(),0,9);
			testaExemplo(mapa6(),2,6);
			testaExemplo(mapa6(),4,9);
		}else if (CONJUNTO_DE_TESTE==2){
			testaExemplo(Mapa.geradorDeMapa(10, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(11, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(12, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(13, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(14, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(15, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(16, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(17, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(18, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(19, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(20, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(50, 60),0,1);
			testaExemplo(Mapa.geradorDeMapa(50, 70),0,1);
			testaExemplo(Mapa.geradorDeMapa(50, 75),0,1);
			testaExemplo(Mapa.geradorDeMapa(50, 80),0,1);
			testaExemplo(Mapa.geradorDeMapa(100, 100),0,1);
			testaExemplo(Mapa.geradorDeMapa(100, 105),0,1);
			testaExemplo(Mapa.geradorDeMapa(100, 110),0,1);
			testaExemplo(Mapa.geradorDeMapa(100, 115),0,1);
			testaExemplo(Mapa.geradorDeMapa(100, 120),0,1);
			testaExemplo(Mapa.geradorDeMapa(100, 125),0,1);
		}else if (CONJUNTO_DE_TESTE==3){
			testaExemplo(Mapa.geradorDeMapa(15, 15),0,1);
			testaExemplo(Mapa.geradorDeMapa(15, 30),0,1);
			testaExemplo(Mapa.geradorDeMapa(15, 60),0,1);
			testaExemplo(Mapa.geradorDeMapa(15, 90),0,1);
			testaExemplo(Mapa.geradorDeMapa(15, 120),0,1);
		}
	}


	private static void testaExemplo(Mapa mapa, int indiceOrigem, int indiceDestino) {
		System.out.println("############ Exemplo: partindo da cidade " + mapa.cidadesDoMapa[indiceOrigem].nome + " para a " + mapa.cidadesDoMapa[indiceDestino].nome + " (caminho composto por "+mapa.cidadesDoMapa.length+" cidades).");
		long tempoInicial = System.currentTimeMillis();
		Cidade[] caminho1a = Caminho.encontrarMelhorCaminho(mapa,mapa.cidadesDoMapa[indiceOrigem],mapa.cidadesDoMapa[indiceDestino]);
		System.out.println("Tempo de execucao: " + (System.currentTimeMillis()-tempoInicial) + " ms.");
		Mapa.imprimirCaminho(caminho1a);
		System.out.println("Distancia do caminho: " + Mapa.calcularDistanciaDoCaminho(caminho1a));
	}


	static Mapa mapa1(){
		Cidade[] exemplo1 = new Cidade[2];
		exemplo1[0] = new Cidade("Y");
		exemplo1[1] = new Cidade("X");
		Distancia[] d1 = {new Distancia(exemplo1[1], 5.5)};
		Distancia[] d2 = {new Distancia(exemplo1[0], 5.5)};
		exemplo1[0].adicionarArranjoDeDistancias(d1);
		exemplo1[1].adicionarArranjoDeDistancias(d2);
		return new Mapa(exemplo1);		
	}

	static Mapa mapa2(){
		Cidade[] exemplo2 = new Cidade[3];
		exemplo2[0] = new Cidade("A");
		exemplo2[1] = new Cidade("B");
		exemplo2[2] = new Cidade("C");
		Distancia[] d1 = {new Distancia(exemplo2[1], 5.5),new Distancia(exemplo2[2], 3.0)};
		Distancia[] d2 = {new Distancia(exemplo2[0], 5.5),new Distancia(exemplo2[2], 6.0)};
		Distancia[] d3 = {new Distancia(exemplo2[0], 3.0),new Distancia(exemplo2[1], 6.0)};
		exemplo2[0].adicionarArranjoDeDistancias(d1);
		exemplo2[1].adicionarArranjoDeDistancias(d2);
		exemplo2[2].adicionarArranjoDeDistancias(d3);
		return new Mapa(exemplo2);		
	}


	static Mapa mapa3(){
		Cidade[] exemplo3 = new Cidade[4];
		exemplo3[0] = new Cidade("E");
		exemplo3[1] = new Cidade("F");
		exemplo3[2] = new Cidade("G");
		exemplo3[3] = new Cidade("H");
		Distancia[] d1 = {new Distancia(exemplo3[3], 5.5),new Distancia(exemplo3[1], 3.0)};
		Distancia[] d2 = {new Distancia(exemplo3[0], 3.0),new Distancia(exemplo3[2], 6.0),new Distancia(exemplo3[3], 20.0)};
		Distancia[] d3 = {new Distancia(exemplo3[1], 6.0),new Distancia(exemplo3[3], 7.0)};
		Distancia[] d4 = {new Distancia(exemplo3[2], 7.0),new Distancia(exemplo3[0], 5.5),new Distancia(exemplo3[1], 20.0)};
		exemplo3[0].adicionarArranjoDeDistancias(d1);
		exemplo3[1].adicionarArranjoDeDistancias(d2);
		exemplo3[2].adicionarArranjoDeDistancias(d3);
		exemplo3[3].adicionarArranjoDeDistancias(d4);
		return new Mapa(exemplo3);		
	}

	static Mapa mapa4(){
		Cidade[] exemplo4 = new Cidade[5];
		exemplo4[0] = new Cidade("I");
		exemplo4[1] = new Cidade("J");
		exemplo4[2] = new Cidade("K");
		exemplo4[3] = new Cidade("L");
		exemplo4[4] = new Cidade("M");
		Distancia[] d1 = {new Distancia(exemplo4[1], 2.5),new Distancia(exemplo4[2], 8.0),new Distancia(exemplo4[3], 3.5),new Distancia(exemplo4[4], 7.0)};
		Distancia[] d2 = {new Distancia(exemplo4[0], 2.5),new Distancia(exemplo4[2], 5.0),new Distancia(exemplo4[3], 6.5),new Distancia(exemplo4[4], 4.0)};
		Distancia[] d3 = {new Distancia(exemplo4[0], 8.0),new Distancia(exemplo4[1], 5.0),new Distancia(exemplo4[3], 5.5),new Distancia(exemplo4[4], 3.0)};
		Distancia[] d4 = {new Distancia(exemplo4[0], 3.5),new Distancia(exemplo4[1], 6.5),new Distancia(exemplo4[2], 5.5),new Distancia(exemplo4[4], 9.0)};
		Distancia[] d5 = {new Distancia(exemplo4[0], 7.0),new Distancia(exemplo4[1], 4.0),new Distancia(exemplo4[2], 3.0),new Distancia(exemplo4[3], 9.0)};
		exemplo4[0].adicionarArranjoDeDistancias(d1);
		exemplo4[1].adicionarArranjoDeDistancias(d2);
		exemplo4[2].adicionarArranjoDeDistancias(d3);
		exemplo4[3].adicionarArranjoDeDistancias(d4);
		exemplo4[4].adicionarArranjoDeDistancias(d5);
		return new Mapa(exemplo4);		
	}

	static Mapa mapa5(){
		Cidade[] exemplo5 = new Cidade[5];
		exemplo5[0] = new Cidade("N");
		exemplo5[1] = new Cidade("O");
		exemplo5[2] = new Cidade("P");
		exemplo5[3] = new Cidade("Q");
		exemplo5[4] = new Cidade("R");
		Distancia[] d0 = {new Distancia(exemplo5[1], 2.0),new Distancia(exemplo5[3], 4.0)};
		Distancia[] d1 = {new Distancia(exemplo5[0], 2.0),new Distancia(exemplo5[2], 7.0),new Distancia(exemplo5[3], 8.0)};
		Distancia[] d2 = {new Distancia(exemplo5[1], 7.0),new Distancia(exemplo5[3], 5.0),new Distancia(exemplo5[4], 3.0)};
		Distancia[] d3 = {new Distancia(exemplo5[0], 4.0),new Distancia(exemplo5[1], 8.0),new Distancia(exemplo5[2], 5.0),new Distancia(exemplo5[4], 3.5)};
		Distancia[] d4 = {new Distancia(exemplo5[2], 3.0),new Distancia(exemplo5[3], 3.5)};
		exemplo5[0].adicionarArranjoDeDistancias(d0);
		exemplo5[1].adicionarArranjoDeDistancias(d1);
		exemplo5[2].adicionarArranjoDeDistancias(d2);
		exemplo5[3].adicionarArranjoDeDistancias(d3);
		exemplo5[4].adicionarArranjoDeDistancias(d4);
		return new Mapa(exemplo5);		
	}

	static Mapa mapa6(){
		Cidade[] exemplo6 = new Cidade[10];
		exemplo6[0] = new Cidade("a");
		exemplo6[1] = new Cidade("b");
		exemplo6[2] = new Cidade("c");
		exemplo6[3] = new Cidade("d");
		exemplo6[4] = new Cidade("e");
		exemplo6[5] = new Cidade("f");
		exemplo6[6] = new Cidade("g");
		exemplo6[7] = new Cidade("h");
		exemplo6[8] = new Cidade("i");
		exemplo6[9] = new Cidade("j");
		Distancia[] d0 = {new Distancia(exemplo6[9], 40.0),new Distancia(exemplo6[1], 50.0)};
		Distancia[] d1 = {new Distancia(exemplo6[0], 50.0),new Distancia(exemplo6[2], 45.0),new Distancia(exemplo6[5], 27.0)};
		Distancia[] d2 = {new Distancia(exemplo6[1], 45.0),new Distancia(exemplo6[3], 43.0)};
		Distancia[] d3 = {new Distancia(exemplo6[2], 43.0),new Distancia(exemplo6[4], 51.0),new Distancia(exemplo6[8], 37.0)};
		Distancia[] d4 = {new Distancia(exemplo6[3], 51.0),new Distancia(exemplo6[5], 85.0)};
		Distancia[] d5 = {new Distancia(exemplo6[4], 85.0),new Distancia(exemplo6[6], 30.0),new Distancia(exemplo6[1], 27.0)};
		Distancia[] d6 = {new Distancia(exemplo6[5], 30.0),new Distancia(exemplo6[7], 44.0)};
		Distancia[] d7 = {new Distancia(exemplo6[6], 44.0),new Distancia(exemplo6[8], 38.0)};
		Distancia[] d8 = {new Distancia(exemplo6[7], 38.0),new Distancia(exemplo6[9], 53.0),new Distancia(exemplo6[3], 37.0)};
		Distancia[] d9 = {new Distancia(exemplo6[8], 53.0),new Distancia(exemplo6[0], 40.0)};

		exemplo6[0].adicionarArranjoDeDistancias(d0);
		exemplo6[1].adicionarArranjoDeDistancias(d1);
		exemplo6[2].adicionarArranjoDeDistancias(d2);
		exemplo6[3].adicionarArranjoDeDistancias(d3);
		exemplo6[4].adicionarArranjoDeDistancias(d4);
		exemplo6[5].adicionarArranjoDeDistancias(d5);
		exemplo6[6].adicionarArranjoDeDistancias(d6);
		exemplo6[7].adicionarArranjoDeDistancias(d7);
		exemplo6[8].adicionarArranjoDeDistancias(d8);
		exemplo6[9].adicionarArranjoDeDistancias(d9);
		return new Mapa(exemplo6);		
	}

}
