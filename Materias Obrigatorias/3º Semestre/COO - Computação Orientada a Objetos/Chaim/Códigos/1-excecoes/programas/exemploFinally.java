public class MinhaMatematica {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean passeiPorAqui = true;
		MinhaMatematica m = new MinhaMatematica();
		while (passeiPorAqui)
			try {
				System.out.println(m.myDiv(8, 0));
				passeiPorAqui = true;
			} catch (ExcecaoDivisaoPorZero edpz) {
				System.out.println(edpz);
				
				continue;
			} finally {passeiPorAqui = false;
				System.out.println("Passei por aqui pelo finally:" + passeiPorAqui);
			}
		System.out.println("Passei por aqui2");
	}

	int myDiv(int a, int b) throws ExcecaoDivisaoPorZero{
			if (b == 0)
				throw new ExcecaoDivisaoPorZero("denominador eh zero!");
	
		return a/b;
	}
}