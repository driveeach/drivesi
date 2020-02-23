class GuardaProduto {
	double codigoProduto;
	double codigoFabricante;
	void guardaCodigoProduto (double codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	void guardaCodigoFabricante (double codigoFabricante) {
		this.codigoFabricante = codigoFabricante;
	}
}
class Um {
	static void compara (GuardaProduto [] codigos){
		GuardaProduto aux = null;
		for (int ult = codigos.length-1; ult>0; ult--){
			for (int i = 0; i<ult; i++) {
				if (codigos[i].codigoFabricante > codigos[i+1].codigoFabricante) {
					aux = codigos[i];
					codigos[i] = codigos[i+1];
					codigos[i+1] = aux;
				}
				else {
					if (codigos[i].codigoFabricante == codigos[i+1].codigoFabricante){
						if (codigos[i].codigoProduto > codigos[i+1].codigoProduto) {
							aux = codigos[i];
							codigos[i] = codigos[i+1];
							codigos[i+1] = aux;
						}
					}
				}
			}
		}
		for (int i = 0; i<codigos.length; i++){
			System.out.println ("   "+codigos[i].codigoFabricante);
			System.out.println (codigos[i].codigoProduto);
		}
	}
	public static void main (String [] args) {
		GuardaProduto [] codigos = new GuardaProduto[5];
		for (int i = 0; i<codigos.length; i++) {
			GuardaProduto a = new GuardaProduto();
			codigos[i] = a;
			codigos[i].codigoProduto =Math.random();
			codigos[i].codigoFabricante =Math.random();
		}
		for (int i = 0; i<codigos.length; i++){
			System.out.println ("   "+codigos[i].codigoFabricante);
			System.out.println (codigos[i].codigoProduto);
		}
		System.out.println ();
		compara (codigos);
	}
}