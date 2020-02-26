public class KnightsTour {
	final int[] dx = { 2, 1, -1, -2, -2, -1, 1, 2 };
	final int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	final int num;
	final int numSqr;
	int[][] table;

	public KnightsTour(int num) {
		this.num = num;
		this.numSqr = num * num;
		this.table = new int[num][num];
	}

	boolean isAcceptable(int x, int y) {
		boolean result = (x >= 0 && x <= num - 1);
		result = result && (y >= 0 && y <= num - 1);
		result = result && (table[x][y] == 0);
		return result;
	}

	boolean tryMove(int i, int x, int y) {
		// Verifica a quantidade de movimentos
		boolean done = (i > numSqr);
		int k = 0;
		int u, v;
		while (!done && k < 8) {
			u = x + dx[k];
			v = y + dy[k];
			if (isAcceptable(u, v)) {
				table[u][v] = i;
				done = tryMove(i + 1, u, v); // tenta outro movimento
				if (!done) {
					table[u][v] = 0; // não sucedido. Descarta movimento
				}
			}
			k = k + 1;
		}
		return done;
	}

	void showTour(int x, int y) {
		table[x][y] = 1;
		boolean done = tryMove(2, x, y);
		if (done) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					System.out.print(table[i][j] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("No possible tour");
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int x = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		new KnightsTour(n).showTour(x, y);
	}

}
