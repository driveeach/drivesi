public class BubbleSort {
	
  public static void main(String[] args) {
	meuVetor entrada = new meuVetor();
	entrada.elemento[0] = 2;
	entrada.elemento[1] = 4;
	entrada.elemento[2] = 1;
	entrada.elemento[3] = 5;
	entrada.elemento[4] = 3;
	entrada.numeroDeElementos = 5;
	
	meuVetor resultado = ordenar(entrada);
	for (int i=0;i<entrada.numeroDeElementos;i++){
	  System.out.print(resultado.elemento[i] + " ");
	}
  }

	
  public static meuVetor ordenar(meuVetor pEntrada){
	int n = pEntrada.numeroDeElementos;
	for (int i=1;i<n;i++){
	  for (int j=1;j<n-i+1;j++){
		if (pEntrada.elemento[j-1] > pEntrada.elemento[j]){
		  int temp = pEntrada.elemento[j];
		  pEntrada.elemento[j]=pEntrada.elemento[j-1];
		  pEntrada.elemento[j-1]=temp;
		}
	  }
    }
  return pEntrada;
  }	
}


