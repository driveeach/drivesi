public class Teste extends MyException{
	public static void f() throws MyException{
		System.out.println("Throwing MyException from f()");
		throw new MyException();
	}
	public static void g() throws MyException{
		System.out.println("Throwing MyException from g()");
		throw new MyException("Originated in g()");
	}
	public static void main(String [] args){
		try{
			f();
		}catch (MyException e){
			System.out.println("Exception 1");
		}
		try{
			g();
			}catch (MyException e){
				System.out.println("Exception 2");
			}
	}
}