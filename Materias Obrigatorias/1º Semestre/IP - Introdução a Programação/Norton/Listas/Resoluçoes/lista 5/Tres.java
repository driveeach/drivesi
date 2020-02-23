class Tres {
	public static boolean permutacao (int [][] matriz) {
		int contador = 0;
		boolean [] checar = new boolean [matriz.length * 2];
		for (int i = 0; i<matriz.length; i++) {
			for (int j = 0; j<matriz.length; j++) {
				if (matriz [i][j] == 1) contador ++;
			}
			if (contador == 1) checar[i] = true;
			contador = 0;
		}
		for (int i = 0; i<matriz.length; i++) {
			for (int j = 0; j<matriz.length; j++) {
				if (matriz [j][i] == 1) contador ++;
			}
			if (contador == 1) checar[i+matriz.length] = true;
			contador = 0;
		}
		for (boolean c : checar) {
			if (c == false) return false;
		}
		return true;
}
	public static void main (String [] args) {
		int [][] matriz = {{1,0,0},
							{0,0,1},
							{0,1,0}};
		System.out.println (permutacao (matriz));					
	}
}