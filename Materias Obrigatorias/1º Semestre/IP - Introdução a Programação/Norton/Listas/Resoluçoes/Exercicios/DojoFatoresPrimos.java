/*Todo número inteiro positivo pode ser representado pelo produto de potências de números primos (os chamados fatores primos).
Por exemplo o número 6 pode ser representado pelo produto do números primos 2 x 3.
Outros exemplos:
5 = 5 (números primos só tem um fator primo - ele mesmo)
100 = 2 x 2 x 5 x 5
198 = 2 x 3 x 3 x 11
276 = 2 x 2 x 3 x 23
Desenvolva um programa que dado um número inteiro positivo, retorne os seus fatores primos
*/

class DojoFatoresPrimos{
	public static void main(String[] args){
		fatoresPrimos(5);
		fatoresPrimos(100);
		fatoresPrimos(198);
		fatoresPrimos(276);
	}
	public static void fatoresPrimos(int num){
		int primo = 2;
		String resp = "1"; // só um detalhe para a linha da resposta ficar mais apresentável
		while(num!=1){
			while(num%primo==0){
				resp+= "x"+primo;
				num = num/primo;
			}
			primo = proxPrimo(primo);
		}
		System.out.println(resp);
	}
	
	public static int proxPrimo(int prox){
		boolean primo;
		boolean fim = false;
		while(!fim){
			prox++;
			primo = true;
			for(int i=prox-1; i>=2; i--){
				if(prox%i==0) primo = false;
			}
			if(primo) fim = true;
		}
		return prox;
	}
}