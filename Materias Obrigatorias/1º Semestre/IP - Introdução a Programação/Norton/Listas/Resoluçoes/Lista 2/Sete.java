class Sete {
	public static int celsiusFahrenheit (){//para um unico nro
		return 9*25/5+32;
	}
	
	public static int celsiusFahrenheit2 (int c){//um conversor para numeros aleatorios
		return 9*c/5+32;
	}
	
	public static double celsiusFahrenheit3 (double c){//usando double
		return 9.0*c/5.0+32.0;
	}

	public static double fahrenheitCelsius (double f){
		return 5.0*(f-32.0)/9.0;
	}	
	
	public static void main (String[]args){
		System.out.println (celsiusFahrenheit());
		System.out.println (celsiusFahrenheit2 (34));
		System.out.println (celsiusFahrenheit3 (43.9));
		System.out.println (fahrenheitCelsius (43.9));
	}
	
}
