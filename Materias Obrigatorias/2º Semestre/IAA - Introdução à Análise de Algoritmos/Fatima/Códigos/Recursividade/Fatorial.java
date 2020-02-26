public class Fatorial {

	int fatorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * fatorial(n - 1);
		}

	}

}
