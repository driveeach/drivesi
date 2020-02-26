public class Main{
	public static void someMethod() throws OneException{
		System.out.println("originating the exception in someMethod()");
		throw new OneException("thrown from f()");
	}
	public static void main(String [] args) throws TwoException{
		try{
			someMethod();
		}catch(OneException e){
			System.err.println("Caught in main, e.printStackTrace()");
			e.printStackTrace();

			throw new TwoException("from main()");
		}
	}
}