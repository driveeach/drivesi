import java.util.Scanner;
import java.util.InputMismatchException;

class NegativeException extends Exception{
	public String toString (){
		return "Negative Exception";
	}
}
public class BankAccount{
	static double balance = 500.00;
	public static void main (String [] args){
	boolean b=false;
		while (!b){
			try{
				double amount = valor();
				withdraw (amount);
				b=true;
				System.exit(0);
			}			
			catch (Exception e){
				System.out.println ("Saldo insuficiente");
			}
			catch (NegativeException ne){
				System.out.println ("O valor informado eh negativo.");
			}
			catch (InputMismatchException entradaInvalida){
				System.out.println ("Entrada invalida.");
			}
			finally{
				System.out.println ("Tente novamente!");
			}
		}
	}
	public static void withdraw (double amount) throws NegativeException{
		if (amount>balance){
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException ("A quantia ultrapassa o saldo");
			throw illegalArgumentException;
		}
		else if (amount <0){
			NegativeException ne = new NegativeException ();
			throw ne;
		}
		balance=balance-amount;
		System.out.println ("Saldo:" +balance);
	}
	public static double valor(){
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite o valor a ser sacado:");
		double amount = sc.nextDouble();
		return amount;
	}
}