public class FibonacciLinear {

	FiboNum fibonacci(int n) {
		if (n <= 1) {
			return new FiboNum(n, 0);
		} else {
			FiboNum num = fibonacci(n - 1);
			System.out.println("Fib(" + (n-1) + ") = " + num.getNum());
			return new FiboNum(num.getNum() + num.getPrev(), num.getNum());
		}
	}
}

class FiboNum {

	private int num;
	private int prev;

	public FiboNum(int num, int prev) {
		super();
		this.num = num;
		this.prev = prev;
	}

	public int getNum() {
		return num;
	}

	public int getPrev() {
		return prev;
	}
}
