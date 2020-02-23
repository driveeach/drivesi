class Tres {
	static int real;
	static int imaginario;
	public static void Soma (int [] real, int [] imaginario) {
		real = real[0] + real [1];
		imaginario = imaginario [0] + imaginario [1];
		System.out.println (real + " + " + imaginario + "i");
	}
	public static void Subtracao (int [] real, int [] imaginario) {
		real = real[0] - real [1];
		imaginario = imaginario [0] - imaginario [1];
		System.out.println (real + " - " + imaginario + "i");
	}
	public static void Multiplicacao (int [] real, int [] imaginario) {
		real = (real[0]*real[1]) - (imaginario[0]*imaginario[1]);
		imaginario = (real[0] * imaginario [0] + real [1] * imaginario [1]);
		System.out.println (real + " + " + imaginario + "i");
	}
	public static void main (String [] args) {
		int [] numreal = {1, 2};
		int [] numimaginario = {3, 4};
		Soma (numreal, numimaginario);
		Subtracao (numreal, numimaginario);
		
	}
}