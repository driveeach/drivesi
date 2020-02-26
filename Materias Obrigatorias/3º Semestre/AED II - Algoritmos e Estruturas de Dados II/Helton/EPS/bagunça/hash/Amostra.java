package hash;

import java.util.Random;

class Amostra {
	private Random r;
	private int registro[];
	
	Amostra(int tamanho){
		r = new Random(System.nanoTime());
		registro = new int[tamanho];

		//nano é da ordem de 10^-9
		long tempo = System.nanoTime();
		for (int i = 0; i < registro.length; i++)
			registro[i] = r.nextInt(100000000);
		tempo = System.nanoTime()-tempo;
		
		//mili é da ordem de 10^-3
		System.out.println("Tempo para gerar " + tamanho + " chaves é de " + tempo/1000000 + "ms.");
	}

	/**
	 * @return the valores
	 */
	public int getAmostra(int i) {
		return registro[i];
	}
	
	public int tamanho(){
		return registro.length;
	}
	
	public void print(){
		/*
		for (int i : registro){
			System.out.println(registro[i]);
		}
		*/
		for (int i = 0; i < registro.length; i++)
			System.out.println(i + "-" + registro[i]);
	}
	
}
