package especificadores1;

public class Alpha {
	public static void alphaPublic(){
		System.out.println("Alpha public!");
	}
	static void alphaNada(){
		System.out.println("Alpha nada!");
	}
	protected static void alphaProtected(){
		System.out.println("Alpha protected!");
	}
	private static void alphaPrivate(){
		System.out.println("Alpha private!");
	}
	public static void main(String[]args) {
		alphaPublic();
		alphaProtected();
		alphaNada();
		alphaPrivate();
	}
}