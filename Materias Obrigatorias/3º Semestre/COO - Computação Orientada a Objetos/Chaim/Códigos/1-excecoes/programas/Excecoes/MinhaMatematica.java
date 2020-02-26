
public class MinhaMatematica {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		System.out.println(myDiv(5,0));
		}
		catch (ExcecaoDivisaoPorZero e) {
			System.err.println(e);
		}
	}

	static double myDiv(double a, double b) throws ExcecaoDivisaoPorZero
	{
		if(b == 0)
			throw new ExcecaoDivisaoPorZero();
		return a/b;
	}
}
