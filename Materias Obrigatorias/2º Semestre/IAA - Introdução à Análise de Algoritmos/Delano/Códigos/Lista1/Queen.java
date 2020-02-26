/** Queen.java
 * basedao no codigo de Timothy Budd
 */
import java.awt.Graphics;

public class Queen {
	private short lin;
	private short col;
	private Queen viz;

	public Queen(short c, Queen q) {
		col = c;
		lin = 1;
		viz = q;
	}

	public boolean achaSolucao() {
		while (viz != null && viz.podeAtacar(lin, col))
			if (!avanca())
				return false;
		return true;
	}

	public boolean avanca() {
		if (lin < 8) {
			lin++;
			return achaSolucao();
		}
		if (viz != null) {
			if (!viz.avanca())
				return false;
			if (!viz.achaSolucao())
				return false;
		} else
			return false;
		lin = 1;
		return achaSolucao();
	}

	private boolean podeAtacar(int tlin, int tcol) {
		int colDif = tcol - col;
		if ((lin == tlin) || (lin + colDif == tlin) || //
				(lin - colDif == tlin))
			return true;
		if (viz != null)
			return viz.podeAtacar(tlin, tcol);
		return false;
	}

	public String toString() {
		return ("(" + col + ", " + lin + ")");
	}

	public void imprime() {
		if (viz != null)
			viz.imprime();
		System.out.println(this);
	}

	// usa se applet
	public void paint(Graphics g) {
		if (viz != null)
			viz.paint(g);
		int x = (lin - 1) * 50;
		int y = (col - 1) * 50;
		g.drawOval(x + 20, y + 20, 10, 10);
	}

	public static void main(String[] xyz) {
		Queen ultima;
		ultima = null;
		for (short i = 1; i <= 8; i++) {
			ultima = new Queen(i, ultima);
			ultima.achaSolucao();
		}
		ultima.imprime();
	}

}// Queen

