class Oito {

	public static void main(String[] args) {
		int dia1 = 15;
		int mes1 = 04;
		int ano1 = 1994;
		int dia2 = 20;
		int mes2 = 05;
		int ano2 = 1991;

			if (ano1 > ano2) {System.out.println("A data 1 (" +dia1+"/" +mes1+ "/" +ano1+ ") é cronologicamente maior que a data 2 (" +dia2+"/" +mes2+ "/" +ano2+ ")."); return; }
			if (ano1 < ano2) {System.out.println("A data 2 (" +dia2+"/" +mes2+ "/" +ano2+ ") é cronologicamente maior que a data 1 (" +dia1+"/" +mes1+ "/" +ano1+ ")."); return;} 
			if (ano1 == ano2 && mes1 > mes2) {System.out.println ("A data 1 (" +dia1+"/" +mes1+ "/" +ano1+ ") é cronologicamente maior que a data 2 (" +dia2+"/" +mes2+ "/" +ano2+ ")."); return;} 
			if (ano1 == ano2 && mes1 < mes2) {System.out.println ("A data 2 (" +dia2+"/" +mes2+ "/" +ano2+ ") é cronologicamente maior que a data 1 (" +dia1+"/" +mes1+ "/" +ano1+ ")."); return; }
			if (ano1 == ano2 && mes1 == mes2 && dia1 > dia2){ System.out.println ("A data 1 (" +dia1+"/" +mes1+ "/" +ano1+ ") é cronologicamente maior que a data 2 (" +dia2+"/" +mes2+ "/" +ano2+ ")."); return; }
			if (ano1 == ano2 && mes1 == mes2 && dia1 < dia2) System.out.println ("A data 2 (" +dia2+"/" +mes2+ "/" +ano2+ ") é cronologicamente maior que a data 1 (" +dia1+"/" +mes1+ "/" +ano1+ ")."); 	
		
	
    }  	 
}
