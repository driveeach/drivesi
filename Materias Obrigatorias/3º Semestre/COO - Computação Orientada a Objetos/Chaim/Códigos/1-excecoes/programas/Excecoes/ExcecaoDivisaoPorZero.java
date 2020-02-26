
public class ExcecaoDivisaoPorZero extends Exception {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public ExcecaoDivisaoPorZero() {
		super("Tentativa de divisão por zero");
	  }

	  public ExcecaoDivisaoPorZero(String msg) {
		super(msg);
	  }
}
