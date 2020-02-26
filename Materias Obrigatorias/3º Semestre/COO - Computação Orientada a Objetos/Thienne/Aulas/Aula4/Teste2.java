public class Teste2{
	public static void main(String[] args) {
		try{
			throw new MyChildException();
		}catch(MyParentException a){
			System.err.println("Caught MyParentException");
		}catch(MyChildException s){
			System.err.println("Caught MyChildException");
		}

	}
}